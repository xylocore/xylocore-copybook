Xylocore Copybook Library
=========================

Libraries for parsing copybooks and generating java classes that
manipulate buffers according to the copybook layout.

License
-------
* [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)

How To Use
----------

First, add copybook-generator-1.0-SNAPSHOT/bin to your path.

Next, run the generation command on the copybook.

cbgen --class com.example.MyCopybook mycopybook.cpy

This will parse the copybook contained in mycopybook.cpy, generate a Java class that
represents the copybook, and place the generated class in com/example/MyCopybook.java.
If you would like to have the generated file placed somewhere else, you can specify a
generation root directory by uing the --genrootdir parameter. Here is an example:

cbgen --genrootdir /home/dev/gen --class com.example.MyCopybook mycopybook.cpy

This place the generated Java file in /home/dev/gen/com/example/MyCopybook.java.

The copybook file can have any extension or, if you would like the copybook to be
read from standard in, you can specify -.

Things To Do
------------

* Get the current set of unit tests to run successfully.
* Lots of documentation on how to use the generator and how to use the generated
class in Java code.
* Improve error handling in the copybook parser.
* Document the copybook features that are supported and not supported.
* Add support for IBM COMP-1 and COMP-2 usage types (native IBM 370 floating point
representations).
* Add proper handling of SYNC.
* Complete the Maven plugin for generation.

Copybook Features Not Supported
-------------------------------

* Variable length tables (OCCURS DEPENDING ON).
* SYNC keyword and alignment.

Copybook Testing
----------------

If you have a copybook that the generator does not parse or process correctly, feel
free to send an email to <xylocore-copybook-help@xylocore.com>. Describe the problem(s)
encountered and please attach a copybook that reproduces the problem. Please do not
include any proprietary copybooks or code.
