FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD microservice.simple1-*.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
VOLUME ["/var/log"]
ADD docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]
HEALTHCHECK CMD wget -q http://localhost:9001/microservice.simple1/health -O /dev/null