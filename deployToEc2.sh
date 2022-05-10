#!/usr/bin/zsh

echo "Starting StockApp Automated Deployment to AWS EC2 instance with jenkins Build and Deploy."

echo "I am  at  " | pwd
mkdir artifacts

echo "Copying artifacts" | cp serverStart.sh  artifacts/serverStart.sh | cp target/StockApp-0.0.1.jar  artifacts/StockApp-0.0.1.jar




ssh -i /Users/rizal/aws/awsEc2keyPair.pem ubuntu@ec2-18-212-213-133.compute-1.amazonaws.com 'echo | rm -rf /home/ubuntu/artifacts'


echo "Copying Artifacts to AWS EC2 instance"

scp -r -i /Users/rizal/aws/awsEc2keyPair.pem artifacts  ubuntu@ec2-18-208-190-252.compute-1.amazonaws.com:/home/ubuntu/artifacts

ssh -i /Users/rizal/aws/awsEc2keyPair.pem  ubuntu@ec2-18-208-190-252.compute-1.amazonaws.com 'chmod 777 /home/ubuntu/artifacts/**'

echo "SHH to ec2 host to run server-Start-Script"
ssh  -i /Users/rizal/aws/awsEc2keyPair.pem ubuntu@ec2-18-208-190-252.compute-1.amazonaws.com 'echo | . /home/ubuntu/artifacts/serverStart.sh'

