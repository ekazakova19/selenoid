#!/bin/bash
 docker ps --filter "name=^selenoid$" --format '{{.Ports}}'
 if [ $? -eq 0 ]
  then
  echo "Selenoid is already running"
  exit 127
  else
    open --background -a Docker
    curl -s https://aerokube.com/cm/bash | bash \
    && ./cm selenoid start --vnc
    echo "Start selenoid failed" >&2
    if [ $? -eq 0 ]
    then
      echo "Selenoid started successfully" >&2
      exit 0
    else
     echo "Start selenoid failed" >&2
      exit 1
    fi
  fi
