FROM openjdk:11

COPY server-0.0.1-SNAPSHOT.jar /src/server/

ENTRYPOINT ["java", "-jar", "/src/server/server-0.0.1-SNAPSHOT.jar"]


# docker build -t java-img .
# sudo docker run -it -p 8080:8080 --name java-homebox --network network-homebox java-img
