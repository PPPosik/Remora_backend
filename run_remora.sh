#!/bin/bash
cd
cd server
git pull origin master
cd remora
./gradlew clean build --no-daemon
java -jar build/libs/remora-0.0.1-SNAPSHOT.war &
