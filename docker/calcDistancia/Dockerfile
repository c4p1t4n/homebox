FROM openjdk:11

COPY distance-calculation-0.0.1-SNAPSHOT.jar /src/server/

ENTRYPOINT ["java", "-jar", "/src/server/distance-calculation-0.0.1-SNAPSHOT.jar"]
EXPOSE 3001

# docker build -t calc-dist -f Dockerfile .
# docker run -p 3001:3001 -d --name calc-dist --network network-homebox calc-dist
