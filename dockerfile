FROM openjdk:8u92-jdk-alpine

MAINTAINER  minxiang  <294315568@qq.com>

# 项目名
ARG Name=beerate-project

# 版本号
ARG Version=1.0.0

# 工作目录
ENV WORKDIR /usr/local/beerate-project

# 定义工作目录
WORKDIR ${WORKDIR}

# 复制项目文件
COPY target/${Name}-${Version}.jar ./

# 创建日志文件
RUN mkdir ./logs

# 创建上传目录
RUN mkdir ./attachment

# 创建配置文件夹
RUN mkdir ./config

# 重命名jar包
RUN mv ${Name}-${Version}.jar app.jar

# 数据卷挂载
VOLUME	${WORKDIR}/logs	 ${WORKDIR}/attachment

#启动用户
USER root

RUN ls -l .

EXPOSE 8080

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "app.jar" ]