Hue-Emulator
============

The Hue Emulator is a Bridge Emulator for the Philips Hue - Personal Wireless System.  

It allows developers who don't have access to a Philips Hue system (i.e.  A Bridge + Hue Light Bulbs) to write hue apps.


For more information visit the original project page: http://steveyo.github.io/Hue-Emulator

<img src="screenshot.png" />

## Building project
The project has now been transformed into a normal Maven project so it can be build normally.

    mvn clean source:jar install

Or

    mvn clean source:jar jar:test-jar install

if you want to include the test sources.
*TestEmulator* can be used in test cases to start an isolated bridge instance.

## Running Emulator

    mvn exec:java

