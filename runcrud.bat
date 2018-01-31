call mvn clean package
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo Maven BUILD has errors - breaking work
goto fail

:rename
del build\libs\crud.war
ren build\libs\tasks-0.0.1-SNAPSHOT.war crud.war
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo Cannot rename file
goto fail

:stoptomcat
call %CATALINA_HOME%\bin\shutdown.bat

:copyfile
copy build\libs\crud.war %CATALINA_HOME%\webapps
if "%ERRORLEVEL%" == "0" goto runtomcat
echo Cannot copy file
goto fail

:runtomcat
call %CATALINA_HOME%\bin\startup.bat
goto end

:startfirefox
START "firefox" /HIGH /MIN  "C:\Program Files (x86)\Mozilla Firefox\firefox.exe http://localhost:8080/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo Cannot open firefox
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.

