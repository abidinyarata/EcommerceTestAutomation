# Selenium Java End To End Web Application Test Automation Architecture

![Static Badge](https://img.shields.io/badge/Java-green)
![Static Badge](https://img.shields.io/badge/Selenium-red)
![Static Badge](https://img.shields.io/badge/TestNG-yellow)
![Static Badge](https://img.shields.io/badge/Extent%20Reports-pink)
![Static Badge](https://img.shields.io/badge/POM-blue)
![Static Badge](https://img.shields.io/badge/DataDrivenTest-aqua)
![Static Badge](https://img.shields.io/badge/Maven-red)


## Overview

Web test automation example project using Java, Selenium 4, Maven, TestNG, Extent Reports, DDT and Page Object Model (POM)

### Languages and Frameworks

- Java 21 as the programming language.
- Selenium WebDriver as the web browser automation framework using the Java binding.
- TestNG as the testing framework.
- Selenium TakesScreenshot Package for capturing screenshots.
- Extent Reports as report framework

### Project Structure

The project is structured as follows:
```
|   .classpath
|   .project
|   cross-browser-tests.xml
|   grouping.xml
|   master.xml
|   pom.xml
|       
+---reports
|       
+---screenshots
|       
+---src
|   +---main
|   \---test
|       +---java
|       |   +---pageObjects
|       |   |       AccountRegistrationPage.java
|       |   |       BasePage.java
|       |   |       HomePage.java
|       |   |       LoginPage.java
|       |   |       MyAccountPage.java
|       |   |       
|       |   +---testBase
|       |   |       BaseClass.java
|       |   |       
|       |   +---testCases
|       |   |       AccountRegistrationTest.java
|       |   |       LoginDataDrivenTest.java
|       |   |       LoginTest.java
|       |   |       
|       |   \---utilities
|       |           DataProviders.java
|       |           ExcelUtility.java
|       |           ExtentReportManager.java
|       |           
|       \---resources
|               config.properties
|               
+---target
|               
+---test-output
|           
\---testData
        
```

### Clone the repository using

```
git clone https://github.com/abidinyarata/EcommerceTestAutomation.git
```
