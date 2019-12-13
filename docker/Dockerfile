FROM 10.10.30.166/public/centos:7-sr-1.0.4
MAINTAINER 企业级分布式应用服务EDAS研发团队
# 安装打包必备软件
RUN yum install -y wget unzip telnet lsof net-tools bind-utils
# 准备 JDK/Tomcat 系统变量
ENV JAVA_HOME /usr/java/latest
ENV PATH $PATH:$JAVA_HOME/bin
ENV ADMIN_HOME /home/admin
# 下载安装 OpenJDK
RUN yum -y install java-1.8.0-openjdk-devel
# 定义时区参数
ENV TZ=Asia/Shanghai
# 设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo '$TZ' > /etc/timezone
ENV LANG="en_US.UTF-8"
# 增强 Webshell 使⽤体验
ENV TERM=xterm
# 设置启动jvm内存
ENV XMS=2048m
ENV XMX=2048m
# 日志审计地址
#ENV logstashUri=10.2.40.237:5044
# 服务名 - oss 存储路径 需要根据自己服务变更
#ENV EDAS_AHAS_APPNAME=WATER_MSERV_MAP
ENV EDAS_AHAS_APPNAME=anstest
# oss 配置信息
ENV ACCESSKEYID=XvogCCx30uOuDvVC
ENV ACCESSKEYSECRET=jkpWUQoQxQrmS0fLjxuOywL2IXazKq
ENV ENDPOINT=oss-cn-nanchang-jxwater-d01-a.ops.jxwatercloud.com/jxsw-sh-product-log-backup
ENV BUCKETNAME=jxsw-sh-product-log-backup
# 下载部署 EDAS 演示 JAR 包
RUN mkdir -p /home/admin/app
#此处jar包名称需要修改为自己微服务的jar包名称（fatjar）
ADD anstest-1.0-SNAPSHOT-exec.jar /home/admin/app
# 将启动命令写入启动脚本 start.sh
RUN mkdir -p /home/admin && wget http://arms.console.res.jxwatercloud.com/download/ArmsAgent.zip -O ArmsAgent.zip && unzip ArmsAgent.zip -d /home/admin \
    && echo 'str=$"\n"'> /home/admin/start.sh \
    && echo 'cd /root && nohup python3 -u dockerlogsuploadoss.py >> dockerlogsuploadoss.log &' >> /home/admin/start.sh \
    && echo 'sstr=$(echo -e $str)' >> /home/admin/start.sh &&  echo 'echo $sstr'>> /home/admin/start.sh \
    && echo 'echo $sstr'>> /home/admin/start.sh \
    && echo 'java -javaagent:/home/admin/ArmsAgent/arms-bootstrap-1.7.0-SNAPSHOT.jar -Darms.licenseKey=1822871714493338@5b27937b491cff2 -Darms.appName=anstest -jar -Xms$XMS -Xmx$XMX $CATALINA_OPTS -DPOD=$POD_IP /home/admin/app/anstest-1.0-SNAPSHOT-exec.jar' >> /home/admin/start.sh && chmod +x /home/admin/start.sh
WORKDIR $ADMIN_HOME
CMD ["/bin/bash", "/home/admin/start.sh"]
