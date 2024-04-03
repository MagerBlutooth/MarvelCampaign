echo off
call mvn -f SnapMain\pom.xml install
call mvn -f SnapAdventure\pom.xml install
