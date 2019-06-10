#!/bin/bash

res="resources"
DOCKERIZE_VERSION=v0.6.1

moduls=("config" "eureka" "admin" "zipkin" "zuul" "service-admin" "web-admin-feign" "web-admin-ribbon")
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
    if [ -n "$1" ];then
        jar=spring-cloud-demo-$1/target/spring-cloud-demo-$1-0.0.1-SNAPSHOT.jar
        pid=$(ps -aux | grep $jar | grep -v grep | awk '{print $2}')
    #    echo $pid $jar
        if [ -n "$pid" ];then
            kill -9 $pid
            echo -e "\033[32mkill \t $pid \t $1\033[0m"
        else
            echo -e "\033[36mDown \t\t $1\033[0m"
        fi
    else
        echo -e "\033[31minput modul to stop\033[0m"
    fi
}

startone(){
    if [ -n "$1" ];then
        _profilr=""
        if [ -n "$2" ]; then
            _profile="--spring.profiles.active=$2"
        else
            _profile="--spring.profiles.active=dev"
        fi
#        echo "profile is $_profile"
        
        jar=spring-cloud-demo-$1/target/spring-cloud-demo-$1-0.0.1-SNAPSHOT.jar
#            echo $jar
        if [ -e $jar ];then
            pid=$(ps -aux | grep $jar | grep -v grep | awk '{print $2}')
            if [ -n "$pid" ];then
                echo -e "\033[32m$1 Up $pid\033[0m" 
            else
                nohup java -Xmx128m -Xss256k -Xms64m -jar $jar $_profile >> nohup-$1.out 2>&1 &
                echo -e "\033[33mstarting $1 $_profile\033[0m"
            fi
        else
            echo not exits $jar
        fi

    else
        echo -e "\033[31minput modul to start\033[0m"
    fi
}

list(){
#    echo -e "\033[37mstatus\tpid\tmodul\t\t\tprofile\tport\033[0m" 
    printf "\033[32m%-6s\t%-5s\t%-20s\t\t\t%-5s\t%-5s\n\033[0m" status pid modul profile port
    for i in ${moduls[*]}; do
        jar=spring-cloud-demo-$i/target/spring-cloud-demo-$i-0.0.1-SNAPSHOT.jar
        pid=$(ps -aux | grep $jar | grep -v grep | awk '{print $2}')
        _profile=$(ps -aux | grep $jar | grep spring.profiles.active | grep -v grep | awk '{print $(NF)}' | awk -F'=' '{print $NF}')
#        echo $pid $jar
        if [ -n "$pid" ];then
            port=$(lsof -p $pid | grep LISTEN | awk '{print $(NF-1)}' | awk -F':' '{print $NF}')
#            echo -e "\033[32mUp\t$pid\t$i\t\t\t$_profile\t$port\033[0m" 
            printf "\033[32mUp\t%-5s\t%-20s\t\t\t%-5s\t%-5s\n\033[0m" $pid $i $_profile $port
        else
            echo -e "\033[36mDown\t\t$i\033[0m"
        fi
    done
}

buildone(){
    if [ -n "$1" ];then
#        sudo docker build -t spring-cloud-config spring-cloud-config -f spring-cloud-config/Dockerfile
        dir="spring-cloud-demo-$1"
        if [ -d $dir ];then
            echo -e "\033[32m build $1\033[0m"
            sudo docker build --no-cache -t "spring-cloud-$1:v1" $dir -f "$dir/Dockerfile"
            dl=$(sudo docker images -q -f dangling=true)
            if [ -n "$dl" ];then
                echo -e "\033[32m delete dangling image \033[0m"
                sudo docker rmi $dl
            fi
        else
            echo -e "\033[31mdir $dir not exits\033[0m"
        fi
    else
        echo -e "\033[31minput modul to start\033[0m"
    fi
}

build(){
    for i in ${moduls[*]}; do
        buildone $i
    done
}

deployone(){
    if [ -n "$1" ];then
#        sudo docker-compose -f spring-cloud-demo-config/docker-compose.yml up -d
        dir="spring-cloud-demo-$1"
        if [ -d $dir ];then
            echo -e "\033[32m deploy $1\033[0m"
            sudo docker-compose -f "$dir/docker-compose.yml" up -d
        else
            echo -e "\033[31mdir $dir not exits\033[0m"
        fi
    else
        echo -e "\033[31minput modul to deploy\033[0m"
    fi
}

deploy(){
    for i in ${moduls[*]}; do
        deploy $i
    done
}

undeployone(){
    if [ -n "$1" ];then
#        sudo docker-compose -f spring-cloud-demo-config/docker-compose.yml down
        dir="spring-cloud-demo-$1"
        container="spring-cloud-$1"
        if [ -d $dir ];then
            echo -e "\033[32m undeploy $1 ...\033[0m"
            stat=$(sudo docker ps | grep $container)
            if [ -n "$stat" ];then
                sudo docker-compose -f "$dir/docker-compose.yml" down
            else
                echo -e "\033[31m$container dowm \033[0m"
            fi
        else
            echo -e "\033[31mdir $dir not exits\033[0m"
        fi
    else
        echo -e "\033[31minput modul to undeploy\033[0m"
    fi
}

undeploy(){
    for i in ${moduls[*]}; do
        undeployone $i
    done
}

package(){
    mvn clean package -Dmaven.test.skip=true
    [ ! -d "$res" ] && mkdir $res
#    cp spring-cloud-demo*/target/*.jar $res
    if [ ! -f "$res/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz" ];then
        wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz -P $res
    fi
    ls -F | grep "^spring-cloud-.*/$" | xargs -n 1 cp $res/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz
}

case $1 in
    start)
        start $2
    ;;
    stop)
        stop
    ;;
    startone)
        startone $2 $3
    ;;
    list)
        list
    ;;
    stopone)
        stopone $2
    ;;
    package)
        package
    ;;
    buildone)
        buildone $2
    ;;
    build)
        build
    ;;
    deployone)
        deployone $2
    ;;
    deploy)
        deploy
    ;;
    undeployone)
        undeployone $2
    ;;
    undeploy)
        undeploy
    ;;
    *)
        echo -e "\033[33mstart.sh start|stop|startone|list|stopone|package|buildone|build|deployone|deploy|undeployone|undeploy\033[0m"
        exit 1
    ;;
esac

# java -jar spring-cloud-demo-eureka/target/spring-cloud-demo-eureka-0.0.1-SNAPSHOT.jar &

# java -jar spring-cloud-demo-eureka/target/spring-cloud-demo-eureka-0.0.1-SNAPSHOT.jar &

# java -jar spring-cloud-demo-eureka/target/spring-cloud-demo-eureka-0.0.1-SNAPSHOT.jar &

# java -jar spring-cloud-demo-eureka/target/spring-cloud-demo-eureka-0.0.1-SNAPSHOT.jar &

# java -jar spring-cloud-demo-eureka/target/spring-cloud-demo-eureka-0.0.1-SNAPSHOT.jar &

