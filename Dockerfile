#设置本镜像需要使用的基础镜像
FROM java:8

#把jar包添加到镜像中
COPY adminClient-0.0.1-SNAPSHOT.jar /app.jar

#镜像暴露的端口
EXPOSE 8700
#RUN bash -c 'touch /app.jar'
WORKDIR /

#容器启动命令
#ENTRYPOINT ["java","-jar","/app.jar"]
CMD ["java","-jar","/app.jar"]
