
# The script deploy selenoid only for MAC OSs
# The script does not install docker. It is supposed that docker is installed on the machine

#!/bin/bash
 docker ps --filter "name=^selenoid$" --format '{{.Ports}}'
 if [ $? -eq 0 ]
  then
  echo "Selenoid is already running"
  exit 127
  else
    open --background -a Docker
    sleep 5
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
