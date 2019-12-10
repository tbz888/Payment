#!/bin/sh

# 指定当前目录
currentDir=$(pwd)
export JAVA_ARGS="-Droot.dir=$currentDir"
echo "Current Directory is "$currentDir

# 动态监听端口
BEGIN_PORT=9527
END_PORT=10000
initPort=$BEGIN_PORT
isAvailable=`lsof -i :$initPort | wc -l`
while [ $initPort -le $END_PORT -a $isAvailable -gt 0 ]
do #
    initPort=$[$initPort+1]
    isAvailable=`lsof -i :$initPort | wc -l`
done
if [ $isAvailable -gt 0 ]; then
    echo "Has no available port, exit."
    exit;
else
    export JAVA_ARGS="$JAVA_ARGS -Droot.port=$initPort"
    echo "Init port is "$initPort
fi

# 服务器模式
export JAVA_ARGS="$JAVA_ARGS -Dserver"
# 内存大小
export JAVA_ARGS="$JAVA_ARGS -Xms32m -Xmx256m"

# 启动JVM
java -jar $JAVA_ARGS target/nucc-0.0.1-SNAPSHOT.jar