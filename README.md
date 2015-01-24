## Presentation

The purpose of this project is to experiment my research for my thesis.  
My topic is : [the efficient implementation of dynamic languages
in constrained environments] [1].

My studies are based on the [Java] [2] and the [JSR 292] [3].  
I use _Dalvik_, the virtual machine of [Android] [4], to support my research.

You can download SDK releases and factory images for Nexus [here] [1]

### Dependencies

  - AOSP [package] [1]
  - (new) android runtime: ['ART 292'] [11]
  - virtual machine: ['Dalvik 292'] [6]
  - core API: ['Libcore 292'] [7]

## Usage

To use this work, you need to compile with Javac 7
and run with the portable interpreter.

**/!\ WARNING /!\\**  
ART compiles but can not run yet !

### Cloning

This project contains three sub-projects ([art] [11], [dalvik] [6] and [libcore] [7]).  
To get the whole project (including 'art', 'dalvik' and 'libcore'),  
you can download our modified AOSP package from our [website] [1] and clone each project
or use the script 'aosp.py':

``` bash
python aosp.py
```

If no argument given, this script download the last version of our work.  
To know the arguments list, type the command:

``` bash
python aosp.py -h
```

### Create SDK 292

When the project is compiled,
you can create an Android SDK to create/compile/install/... your applications.  
To do this, initialize the project and make the SDK [(google website)] [8]:

``` bash
lunch sdk-eng
make [-j<NCPU>] sdk
```

### Create factory image

The 'aosp.py' can create a factory image for a Nexus device using the compiled source:

``` bash
python aosp.py -i <device> [-v <version>] [-l]
```

Examples:

``` bash
python aosp.py -l -i . # list all factory versions availables
python aosp.py -i manta # create the factory image for 'Nexus 10' with the last version
python aosp.py -i manta -v 4.4.3.292 # create the factory image for 'Nexus 10' with the version '4.4.3.292'
```

### How to flash a Nexus ?

Download the [Google's binaries] [9] for your device,  
extract them in the root of this project and then execute them.  
Initialize the project for your device.
For example, to initialize with a Nexus 10 :

``` bash
lunch full_manta-eng
```

When the project is compiled,
connect your device and flash it (with root rights or with [(linux rules)] [10]).

``` bash
fastboot -w flashall
```

Or you can use our [factory images] [1].

### Some useful commands to compile and use Android:

  - To initialize :
``` bash
export EXPERIMENTAL_USE_JAVA7=true
export USE_CCACHE=1
source build/envsetup.sh
lunch full-eng
```
  - To compile :
``` bash
make [-j<NCPU>] [showcommands]
mmm <directory>
```
  - To run :
``` bash
emulator
```
  - To use the portable interpreter (wrote in C)
``` bash
adb shell stop
adb shell setprop dalvik.vm.execution-mode int:portable
adb shell start
```
  - To use Dalvik (ART doesn't work yet !)
``` bash
adb shell setprop persist.sys.dalvik.vm.lib libdvm.so
```
  - To clean :
``` bash
make clobber
```

  [1]: http://igm.univ-mlv.fr/~pilliet/en/#thesis
  [2]: http://www.oracle.com/us/technologies/java/overview/index.html
  [3]: http://jcp.org/en/jsr/detail?id=292
  [4]: http://source.android.com/
  [6]: https://bitbucket.org/jpilliet/dalvik-292
  [7]: https://bitbucket.org/jpilliet/libcore-292
  [8]: http://tools.android.com/build
  [9]: https://developers.google.com/android/nexus/drivers
  [10]: http://source.android.com/source/initializing.html#configuring-usb-access
  [11]: https://bitbucket.org/jpilliet/art-292
