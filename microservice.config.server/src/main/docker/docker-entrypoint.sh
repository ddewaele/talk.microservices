#!/bin/sh

# Exit on non-zero return values
set -e

echo "Starting Spring boot app..."
java $JAVA_OPTS -Dlogging.file=/var/log/microservice.config.server.log -Djava.security.egd=file:/dev/./urandom -jar /app.jar