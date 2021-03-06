# Decathlon-POJO

This homework assignment is still Work in Progress.
The task is about Decathlon competition.

## 1. How to run this project
- At the moment this is Eclipse project. In order to run it, just import this as Java project into Eclipse or Spring STS.
- You can specify your arguments where:
  * arg[0] - inputFile
  * arg[1] - outputFile
- If you don't want to use arguments, program will ask you to enter via command line, example:
  * *Input File:*
  * test-data/Decathlon_input.txt
  * *Output File:*
  * test-data/Decathlon_output.txt
- The complete paths will work as well (e.g. C:/temp/input.txt)
- Testing file is provided: Decathlon-POJO/**test-data/Decathlon_input.txt**
- The program will generate 2 additional files based on the output file name, so for "output.txt" there will be:
  * output.txt_ouput.xml
  * output.txt_output.html
- These 2 files have the format as per file extension: so .html file can be opened in browser


## 2. TASK REQUIREMENTS:

Progress: 16 / 16

- [x] 1. The input of the Java program is a CSV-like text file (see the attachment).
- [x] 2. The task is to output an XML file with all athletes in ascending order (1, 2, 3...) of their places, 
- [x] 3. containing all the input data plus **total score** 
- [x] 4. and the **place** in the competition (in case of equal scores, share the places, e.g. 3-4 and 3-4 instead of 3 and 4).
- [x] 5. The **Input** and **output** file names should be provided as parameters to the Java application at the startup.
- [x] 6. Application should ask for those parameters itself.  
.  
- [x] 7. It would be great if an XSL file for viewing the produced XML nicely using a web browser is also provided. *Browsers failed to convert XML using my XSL, so created HTMLWriter*
- [x] 8. Be sure to keep the code design simple, but allowing to easily change or add more **input sources** and/or **output file formats**.
- [x] 9. Unit tests for the code are MANDATORY. *(12/12 Test Case classes Done)*
- [x] 10. No external libraries are allowed in addition to the Java standard API except JUnit.
- [x] 11. Gradle should be used as building tool.
- [x] 12. Recommended java version is 7, but not mandatory.
- [x] 13. Please zip your code together with the project file. Keep in mind that we are going to run both your program and the tests.  
  .  
- [x] 14. It is preferred that IntelliJ is used as IDEA. *I used Spring STS to write all code, then migrated source to InteliJ and it worked. See ziped project Decathlon-Gradle.zip*
- [x] 15. Try to keep your code as readable as possible. We value code simplicity.
- [x] 16. Use object-oriented approach with design patterns where applicable. *(Strategy, ...)*

## 3. Resource links:
+ http://en.wikipedia.org/wiki/Decathlon (see formulas are at the end of the page)
+ http://www.junit.org/ - JUnit
+ http://www.jetbrains.com/ - IntelliJ IDEA download

## 4. Images
![Example of HTML output](/img/2017-08-24_22_51_41-HTML-example.png)
![Works on IntelliJ](/img/2017-08-27_17_12_11-Decathlon-Gradle.png)