
@echo off
REM Change the following line to set your JDK path
set JDK_DIR=%JAVA_HOME%

@echo Create the classpath
set CP=;
for %%i in (..\lib\*.jar) do call cp.bat %%i
set CP=%CP%;"%JDK_DIR%\lib\tools.jar"
echo %CP%

@echo Codetabellen import
@echo.
@echo Generating classes...

@rem Java 2 style collection types
"%JDK_DIR%\bin\java" -cp %CP% org.exolab.castor.builder.SourceGenerator -i ./xsds/simple-response.xsd -f -types j2 -package nl.b3p.csw.client.castor.simpleResponse
"%JDK_DIR%\bin\java" -cp %CP% org.exolab.castor.builder.SourceGenerator -i ./xsds/csw-response.xsd -f -types j2 -package nl.b3p.csw.client.castor.cswResponse
"%JDK_DIR%\bin\java" -cp %CP% org.exolab.castor.builder.SourceGenerator -i ./xsds/csw-filter.xsd -f -types j2 -package nl.b3p.csw.client.castor.cswFilter
"%JDK_DIR%\bin\java" -cp %CP% org.exolab.castor.builder.SourceGenerator -i ./xsds/csw-request.xsd -f -types j2 -package nl.b3p.csw.client.castor.cswRequest

@echo.
@echo About to compile generated source code...
@pause

"%JDK_DIR%\bin\javac" -classpath %CP% nl\b3p\csw\client\castor\simpleResponse\*.java
"%JDK_DIR%\bin\javac" -classpath %CP% nl\b3p\csw\client\castor\cswResponse\*.java
"%JDK_DIR%\bin\javac" -classpath %CP% nl\b3p\csw\client\castor\cswFilter\*.java
"%JDK_DIR%\bin\javac" -classpath %CP% nl\b3p\csw\client\castor\cswRequest\*.java


@echo.
@echo Clean the directory
@pause
@rem del *.class
@rem deltree nl
