#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
#  aosp.py
#  
#  Copyright 2014 Jerome Pilliet <pilliet@univ-mlv.fr>
#  

from zipfile import ZipFile as zip_open
from tarfile import open as tar_open
from sys import stdin, stdout, exit
from subprocess import Popen, PIPE
from optparse import OptionParser
from struct import pack, unpack
from os import remove, listdir
from urllib2 import urlopen
from shutil import rmtree
from os.path import isdir

url = 'http://igm.univ-mlv.fr/~pilliet/contents/thesis/'

aosps = [map(str.strip, x.split(' - ')) for x in urlopen(url + '00.aosp_list.txt').readlines()]
versions = sorted(x[0] for x in aosps)
sdks = urlopen(url + '00.sdk_list.txt').readlines()
factories = [map(str.strip, x.split(' - ')) for x in urlopen(url + '00.factories_list.txt').readlines()]
factories_versions = dict((x, sorted([(y[2], tuple(y[:2])) for y in factories if y[3]==x], key=lambda w: int(w[0].split()[1]))) for x in set((z[3] for z in factories)))

repos = [('https://jpilliet@bitbucket.org/jpilliet/art-292', 'art'),
         ('https://jpilliet@bitbucket.org/jpilliet/dalvik-292', 'dalvik'),
         ('https://jpilliet@bitbucket.org/jpilliet/libcore-292', 'libcore')]

##########################################
# tools
# (from http://stackoverflow.com/a/377028)
#
def which(program):
  import os
  def is_exe(fpath):
    return os.path.isfile(fpath) and os.access(fpath, os.X_OK)
  fpath, fname = os.path.split(program)
  if fpath:
    if is_exe(program):
      return program
  else:
    for path in os.environ['PATH'].split(os.pathsep):
      path = path.strip('"')
      exe_file = os.path.join(path, program)
      if is_exe(exe_file):
        return exe_file
  return None

##########################################
# download progress functions
# (based on http://stackoverflow.com/a/2030027)
#
def chunk_report(bytes_so_far, chunk_size, total_size):
  percent = float(bytes_so_far) / total_size
  percent = round(percent*100, 2)
  stdout.write('  downloaded %d of %d Mo (%d%%)\r' % (bytes_so_far/1048576, total_size/1048576, percent))
  if bytes_so_far >= total_size:
    stdout.write('\n')

def chunk_read(package, response, chunk_size=524288, report_hook=None):
  total_size = response.info().getheader('Content-Length').strip()
  total_size = int(total_size)
  bytes_so_far = 0
  f = open(package, 'wb')

  while 1:
    chunk = response.read(chunk_size)
    bytes_so_far += len(chunk)
    if not chunk:
      break
    f.write(chunk)
    if report_hook:
      report_hook(bytes_so_far, chunk_size, total_size)
  return bytes_so_far

##########################################
# mercurial server functions
# (based on http://mercurial.selenic.com/wiki/CommandServer#Example_client)
#
def readchannel(server):
    channel, length = unpack('>cI', server.stdout.read(5))
    if channel in 'IL': # input
        return channel, length
    return channel, server.stdout.read(length)

def writeblock(server, data):
    server.stdin.write(pack('>I', len(data)))
    server.stdin.write(data)
    server.stdin.flush()

def write_command(server, command):
    # write the command
    server.stdin.write('runcommand\n')
    writeblock(server, command)

    # receive the response
    while True:
        channel, val = readchannel(server)
        if channel == 'o':
            print val.rstrip()
        elif channel == 'e':
            print 'error: %s' % val.rstrip()
        elif channel == 'r':
            r = unpack(">l", val)[0]
            if r is not 0:
                print 'ERROR %s' % r
            break
        elif channel == 'L':
            print '(line read request)'
            writeblock(server, stdin.readline(val))
        elif channel == 'I':
            print '(block read request)'
            writeblock(server, stdin.read(val))
        else:
            print 'unexpected channel: %s %s' % (channel, val)
            if channel.isupper(): # required?
                break

##########################################
# program functions
#
def download_and_extract_aosp(version, force=False):
  # get package name
  package = None
  for v,p in aosps:
    if v == version:
      package = p
      break
  if package is None:
    print 'version "%s" does not exist' % (version)
    exit(1)
  # check directory content
  if force:
    for d in (d for d in listdir('.') if isdir(d) and not (d.startswith('.') or d == 'tests')):
      rmtree(d)
  else:
    for d in (d for d in listdir('.') if isdir(d) and not (d.startswith('.') or d == 'tests')):
      print 'directory not empty'
      exit(2)
  print 'downloading Android package:'
  chunk_read(package, urlopen(url + package), report_hook=chunk_report)
  print 'extracting content'
  tar_open(package).extractall()
  # clean
  remove(package)

def download_and_extract_sdk(version, os, force=False):
  # get package name
  package = None
  for v,o,p in (x.rstrip().split(' - ') for x in sdks):
    if v == version and o == os:
      package = p
      break
  if package is None:
    print 'version "%s" does not exist for "%s" OS' % (version, os)
    exit(1)
  # check directory content
  if force:
    for d in (d for d in listdir('.') if isdir(d) and d.startswith('android-sdk_')):
      rmtree(d)
  else:
    for d in (d for d in listdir('.') if isdir(d) and d.startswith('android-sdk_')):
      print 'sdk exists'
      exit(2)
  print 'downloading Android SDK:'
  chunk_read(package, urlopen(url + package), report_hook=chunk_report)
  print 'extracting content'
  zip_open(package).extractall()
  # clean
  remove(package)

##########################################
# program options
#
oss = {'lin': 'GNU/Linux', 'win': 'Windows', 'mac': 'Mac OSX'}

def _list(factory=None):
  if factory is None:
    print 'Available AOSP versions:'
    for v in versions:
      print '    - %s' % v
  else:
    print 'Available factory versions:'
    #~ for v, ls in sorted(factories_versions.items(), key=lambda x: int(x.split()[1])):
    for v, ls in factories_versions.items():
      print '    - %s:' % v
      for d, n in ls:
        print '        - %s %s' % (d, n)
  exit(0)

def _sdk(os, version, force):
  # download Android package
  if os in oss:
    download_and_extract_sdk(version, os, force)
  else:
    print '"%s" not a valide OS:' % (os)
    for i in oss.items():
      print '  "%s" for %s' % (i)

def _aosp(version, force):
  # check mercurial installation
  if which('hg') is None:
    print 'Mercurial not found'

  # download Android package
  #download_and_extract_aosp(version, force)

  # clone repositories
  #   check mercurial project
  for d in (d for d in listdir('.') if isdir(d) and d == '.hg'):
    break
  else:
    Popen(['hg', 'init'])

  #   connect to the server
  server = Popen(['hg', '--config', 'ui.interactive=True', 'serve', '--cmdserver', 'pipe'], stdin=PIPE, stdout=PIPE)

  #   read the hello block
  hello = readchannel(server)

  for u,d in repos:
    print 'cloning %s repository' % d
    write_command(server, ('clone %s -b %s %s' % (u, version, d)).replace(' ', '\0'))

  #   shut down the server
  server.stdin.close()

def _factory(version, factory):
  for nick1, nick2, name, vers, package in factories:
    if version == vers and (factory == nick1 or factory == nick2):
      print 'downloading factory image:'
      chunk_read(package, urlopen('%sarchives/%s' % (url, package)), report_hook=chunk_report)
      img_name = 'image-%s-%s.zip' % (nick1, version)
      img = zip_open(img_name, 'w')
      for f in ('system.img', 'userdata.img', 'android-info.txt', 'cache.img', 'recovery.img', 'boot.img'):
        try:
          img.write('out/target/product/%s/%s' % (nick2, f), f)
        except OSError:
          pass
      img.close()
      pkg = zip_open(package, 'a')
      pkg.write(img_name, '%s-%s/%s' % (nick1, version, img_name))
      pkg.close()
      remove(img_name)
      print 'factory image: %s' % package
      break
  else:
    print 'no factory available for the version "%s" and the device "%s"' % (version, factory)
    exit(1)
  exit(0)

def aosp_helper():
  parser = OptionParser()
  parser.add_option('-l', action='store_true', dest='ls', help='list all available versions (can be used with -i)')
  parser.add_option('-v', dest='version', default=versions[-1], help='use a specific version (aosp/sdk) (can be used with -i)')
  parser.add_option('-s', dest='os', help='get the sdk %s' % str(oss.keys()))
  parser.add_option('-f', action='store_true', dest='force', help='delete all directories')
  parser.add_option('-i', dest='factory', default=None, help='create factory image')

  (options, args) = parser.parse_args()

  if options.ls:
    _list(options.factory)
  elif options.factory is not None:
    _factory(options.version, options.factory)
  elif options.os is not None:
    _sdk(options.os, options.version, options.force)
  else:
    _aosp(options.version, options.force)

if __name__ == '__main__':
  aosp_helper()
