#!/bin/bash
set -e

# Ensure FRONTEND_URL is set
if [ -z "$FRONTEND_URL" ]; then
  echo "ERROR: FRONTEND_URL environment variable is not set!"
  exit 1
fi

# Path to Spring Boot properties inside your WAR
PROPERTIES_FILE=/usr/local/tomcat/webapps/StudentBackend/WEB-INF/classes/application.properties

# Replace frontend.url in application.properties
if [ -f "$PROPERTIES_FILE" ]; then
    sed -i "s|^frontend.url=.*|frontend.url=${FRONTEND_URL}|g" "$PROPERTIES_FILE"
fi

# Start Tomcat
exec catalina.sh run
