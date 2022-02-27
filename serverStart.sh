#!/usr/bin/zsh

echo "At Step : 1"
echo "Stopping Springboot Application"
echo "The pid of running app at port :8181"
lsof -ti :8181
kill -9 $(lsof -ti :8181)


echo "Step 2"

rm -rf /home/ubuntu/stockApp/server/deploy.old
mv  /home/ubuntu/stockApp/server/deploy.new /home/ubuntu/stockApp/server/deploy.old
cp -r /home/ubuntu/artifacts  /home/ubuntu/stockApp/server/deploy.new

echo "Step : 3"
echo "Starting new instance of StockApp"
echo "Check logs at /home/ubuntu/stockApp/log/stockApp.log"
nohup java -jar -Dspring.profiles.active=prod /home/ubuntu/stockApp/server/deploy.new/StockApp-0.0.1.jar >> /home/ubuntu/stockApp/log/stockApp.log 2>&1 &
sleep 40
if [ $? -eq 0 ]
then
  echo "Started Successfully"
  cat /home/ubuntu/stockApp/log/stockApp.log
  exit 0
else
  echo "Could not start" >&2
  exit 1
fi



