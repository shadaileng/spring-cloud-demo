#!/bin/bash

moduls=("config" "eureka" "service-admin" "web-admin-feign" "web-admin-ribbon" "admin" "zipkin" "zuul")
start(){
    for i in ${moduls[*]}; do
        startone $i
    done
}

stop(){
    for i in ${moduls[*]}; do
        stopone $i
    done
}

stopone(){
    jar=spring-cloud-demo-$1/target/spring-cloud-demo-$1-0.0.1-SNAPSHOT.jar
    pid=$(ps -aux | grep $jar | grep -v grep | awk '{print $2}')
#    echo $pid $jar
    if [ -n "$pid" ];then
        kill 9 $pid
        echo -e "\033[32mkill \t $pid \t $1\033[0m"
    else
        echo -e "\033[36mDown \t\t $1\033[0m"
    fi
}

startone(){
    if [ -n "$1" ];then
        if [ "all" = "$1" ]; then
            echo all
        else
            jar=spring-cloud-demo-$1/target/spring-cloud-demo-$1-0.0.1-SNAPSHOT.jar
#            echo $jar
            if [ -e $jar ];then
                pid=$(ps -aux | grep $jar | grep -v grep | awk '{print $2}')
                if [ -n "$pid" ];then
                    echo -e "\033[32m$1 Up $pid\033[0m" 
                else
                    nohup java -Xmx128m -Xss256k -jar $jar >> nohup.out 2>&1 &
                    echo -e "\033[33mstarting $1\033[0m"
                fi
            else
                echo not exits $jar
            fi

        fi
    else
        echo -e "input modul to start"
    fi
}

list(){
    echo -e "\033[37mstatus \t pid \t modul\033[0m" 
    for i in ${moduls[*]}; do
        jar=spring-cloud-demo-$i/target/spring-cloud-demo-$i-0.0.1-SNAPSHOT.jar
        pid=$(ps -aux | grep $jar | grep -v grep | awk '{print $2}')
#        echo $pid $jar
        if [ -n "$pid" ];then
            echo -e "\033[32mUp \t $pid \t $i\033[0m" 
        else
            echo -e "\033[36mDown \t\t $i\033[0m"
        fi
    done
}

case $1 in
    start)
        start $2
    ;;
    stop)
        stop
    ;;
    startone)
        startone $2
    ;;
    list)
        list
    ;;
    stopone)
        stopone $2
    ;;
    *)
        echo -e "\033[33mstart.sh start|stop|startone|list|stopone\033[0m"
        exit 1
    ;;
esac

# java -jar spring-cloud-demo-eureka/target/spring-cloud-demo-eureka-0.0.1-SNAPSHOT.jar &

# java -jar spring-cloud-demo-eureka/target/spring-cloud-demo-eureka-0.0.1-SNAPSHOT.jar &

# java -jar spring-cloud-demo-eureka/target/spring-cloud-demo-eureka-0.0.1-SNAPSHOT.jar &

# java -jar spring-cloud-demo-eureka/target/spring-cloud-demo-eureka-0.0.1-SNAPSHOT.jar &

# java -jar spring-cloud-demo-eureka/target/spring-cloud-demo-eureka-0.0.1-SNAPSHOT.jar &

