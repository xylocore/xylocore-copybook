@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright 2013 The Palantir Corporation
@REM
@REM   Licensed under the Apache License, Version 2.0 (the "License");
@REM   you may not use this file except in compliance with the License.
@REM   You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM   Unless required by applicable law or agreed to in writing, software
@REM   distributed under the License is distributed on an "AS IS" BASIS,
@REM   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM   See the License for the specific language governing permissions and
@REM   limitations under the License.
@REM
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Start Up Batch script
@REM
@REM Required ENV vars:
@REM JAVA_HOME - location of a JDK home dir
@REM
@REM Optional ENV vars
@REM CBGEN_HOME - location of the copybook generator's installed home dir
@REM CBGEN_OPTS - parameters passed to the Java VM when running cbgen
@REM CBGEN_SKIP_RC - flag to disable loading of cbgenrc files
@REM ----------------------------------------------------------------------------

@echo off

@REM set %HOME% to equivalent of $HOME
if "%HOME%" == "" (set "HOME=%HOMEDRIVE%%HOMEPATH%")

@REM Execute a user defined script before this one
if not "%CBGEN_SKIP_RC%" == "" goto skipRcPre
if exist "%HOME%\cbgenrc_pre.bat" call "%HOME%\cbgenrc_pre.bat"
:skipRcPre

set ERROR_CODE=0

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal
if "%OS%"=="WINNT" @setlocal

@REM ==== START VALIDATION ====
if not "%JAVA_HOME%" == "" goto OkJHome

echo.
echo ERROR: JAVA_HOME not found in your environment.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation
echo.
goto error

:OkJHome
if exist "%JAVA_HOME%\bin\java.exe" goto chkCbgenHome

echo.
echo ERROR: JAVA_HOME is set to an invalid directory.
echo JAVA_HOME = "%JAVA_HOME%"
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation
echo.
goto error

:chkCbgenHome
if not "%CBGEN_HOME%"=="" goto valCbgenHome

if "%OS%"=="Windows_NT" SET "CBGEN_HOME=%~dp0.."
if "%OS%"=="WINNT" SET "CBGEN_HOME=%~dp0.."
if not "%CBGEN_HOME%"=="" goto valCbgenHome

echo.
echo ERROR: CBGEN_HOME not found in your environment.
echo Please set the CBGEN_HOME variable in your environment to match the
echo location of the CBGEN installation
echo.
goto error

:valCbgenHome

:stripCbgenHome
if not "_%CBGEN_HOME:~-1%"=="_\" goto checkCbgenBat
set "CBGEN_HOME=%CBGEN_HOME:~0,-1%"
goto stripCbgenHome

:checkCbgenBat
if exist "%CBGEN_HOME%\bin\cbgen.bat" goto init

echo.
echo ERROR: CBGEN_HOME is set to an invalid directory.
echo CBGEN_HOME = "%CBGEN_HOME%"
echo Please set the CBGEN_HOME variable in your environment to match the
echo location of the CBGEN installation
echo.
goto error
@REM ==== END VALIDATION ====

:init
@REM Decide how to startup depending on the version of windows

@REM -- Windows NT with Novell Login
if "%OS%"=="WINNT" goto WinNTNovell

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

:WinNTNovell

@REM -- 4NT shell
if "%@eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CBGEN_CMD_LINE_ARGS=%*
goto endInit

@REM The 4NT Shell from jp software
:4NTArgs
set CBGEN_CMD_LINE_ARGS=%$
goto endInit

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of agruments (up to the command line limit, anyway).
set CBGEN_CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto endInit
set CBGEN_CMD_LINE_ARGS=%CBGEN_CMD_LINE_ARGS% %1
shift
goto Win9xApp

@REM Reaching here means variables are defined and arguments have been captured
:endInit
SET CBGEN_JAVA_EXE="%JAVA_HOME%\bin\java.exe"

@REM -- 4NT shell
if "%@eval[2+2]" == "4" goto 4NTCWJars

@REM -- Regular WinNT shell
for %%i in ("%CBGEN_HOME%"\boot\plexus-classworlds-*) do set CLASSWORLDS_JAR="%%i"
goto runCbgen

@REM The 4NT Shell from jp software
:4NTCWJars
for %%i in ("%CBGEN_HOME%\boot\plexus-classworlds-*") do set CLASSWORLDS_JAR="%%i"
goto runCbgen

@REM Start CBGEN
:runCbgen
set CLASSWORLDS_LAUNCHER=org.codehaus.plexus.classworlds.launcher.Launcher
%CBGEN_JAVA_EXE% %CBGEN_OPTS% -classpath %CLASSWORLDS_JAR% "-Dclassworlds.conf=%CBGEN_HOME%\bin\cbgen.conf" "-Dcbgen.home=%CBGEN_HOME%" %CLASSWORLDS_LAUNCHER% %CBGEN_CMD_LINE_ARGS%
if ERRORLEVEL 1 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
if "%OS%"=="WINNT" @endlocal
set ERROR_CODE=1

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT
if "%OS%"=="WINNT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CBGEN_JAVA_EXE=
set CBGEN_CMD_LINE_ARGS=
goto postExec

:endNT
@endlocal & set ERROR_CODE=%ERROR_CODE%

:postExec

if not "%CBGEN_SKIP_RC%" == "" goto skipRcPost
if exist "%HOME%\cbgenrc_post.bat" call "%HOME%\cbgenrc_post.bat"
:skipRcPost

if "%CBGEN_TERMINATE_CMD%" == "on" exit %ERROR_CODE%

cmd /C exit /B %ERROR_CODE%

