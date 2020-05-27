# nginx_ssh_centos 为自己私有仓库构建好的的docker镜像，如果使用请使用公网上的docker jdk8镜像
FROM java:8
#FROM nginx_ssh_centos
MAINTAINER chenying<604693031@qq.com>

LABEL Description="This image is api order service images."  Version="1.0"

RUN mkdir /home/data

ADD biz-order-service.jar /home/data

RUN chmod -R +x /home/data

#开放7070端口
EXPOSE 7070

#在启动后执行jar的启动脚本
ENTRYPOINT ["java","-jar","/home/data/biz-order-service.jar"]