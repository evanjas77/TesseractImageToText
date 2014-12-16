This is a simple RESTFul web service which converts an Image to Text using the Tesseract OCR.
A sample standalone client is also included for testing the webservice (OCRRestFulStandaloneClient.java).

Tested on Windows 7.

Dependencies:
------------
All dependent jars are available at the following locations

1. Tess4J - A java based tesseract library
  http://sourceforge.net/projects/tess4j/

  Tess4J is dependent on the following native DLLs. These will function properly only on 32-bit JDK. 
   1. liblept168.dll
   2. libtesseract302.dll

2. Jersey - RESTFul webservices in Java
  https://jersey.java.net/download.html
