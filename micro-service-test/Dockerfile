# 基础镜像 java:8
FROM java:8
# 作者信息
MAINTAINER liukunup <liukunup@outlook.com>
# 临时文件目录
VOLUME /tmp
# 暴露端口
EXPOSE 18002
# 将jar包添加到容器中并更名为app.jar
ADD demo-1.0.0-SNAPSHOT.jar app.jar
# 运行jar包
RUN bash -c 'touch /app.jar'
# 运行命令
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dev", "-jar", "/app.jar"]
