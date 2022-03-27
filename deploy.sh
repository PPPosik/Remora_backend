cd /home/server

APP_NAME=remora

JAR_PATH=$(ls | grep jar | head -n 1)

CURRENT_PID=$(pgrep -f $APP_NAME)
if [ -z $CURRENT_PID ]
then
  echo ">>>> java process not found."
else
  echo ">>>> PID: $CURRENT_PID kill."
  kill -15 $CURRENT_PID
  sleep 15
fi

echo ">>>> $JAR_PATH java execute."
nohup java -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null &
sleep 5
CURRENT_PID=$(pgrep -f $APP_NAME)
echo ">>>> New PID: $CURRENT_PID"