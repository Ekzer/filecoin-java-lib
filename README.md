# filecoin-java-lib
Filecoin Java Library based on BLST library for Curve Implementation

Requires Gradle

# Install
Before using this lib, you must compile the BLST library.

On MacOS : 

1. Install SWIG - `brew install swig`
2. Clone BLST lib - `git clone https://github.com/supranational/blst.git`
3. Go to `cd blst/bindings/java`
4. Run `./build.sh`
5. You now have compiled the blst library `supranational.blst.jar`.
6. Copy the jar to `extensions` folder: `cp supranational.blst.jar ../../../extensions/`
You are all set !

Linux / Windows : 
I provide a Dockerfile which is prepared for linux right now. Adapt it for windows if required.