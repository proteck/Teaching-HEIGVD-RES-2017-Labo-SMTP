#!/bin/sh
docker kill $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker build -t="mock" .
docker run -p 25:25 -p 8282:8282 mock
