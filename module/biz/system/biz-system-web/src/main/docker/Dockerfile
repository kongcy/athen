# nginx_ssh_centos 为自己私有仓库构建好的的docker镜像，如果使用请使用公网上的docker jdk8镜像
FROM java:8
#FROM nginx_ssh_centos
MAINTAINER chenying<604693031@qq.com>

LABEL Description="This image is system test service images."  Version="1.0"

RUN mkdir /home/data

ADD biz-system-web.jar /home/data

RUN chmod -R +x /home/data

#开放7080端口
EXPOSE 20772

#在启动后执行web项目的启动脚本 
ENTRYPOINT ["java","-jar","/home/data/biz-system-web.jar"]
#docker run -d -e DUBBO_IP_TO_REGISTRY=10.10.15.140 -e  DUBBO_PORT_TO_REGISTRY=20772  -p 7071:7071 -it