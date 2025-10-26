# Use official Tomcat 10.1 image
FROM tomcat:10.1.48-jdk17-temurin

# Remove default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy WAR file into Tomcat webapps folder
COPY target/StudentBackend.war /usr/local/tomcat/webapps/StudentBackend.war

# Expose the port Tomcat is running on
EXPOSE 8080

# Add entrypoint script to configure frontend.url dynamically
COPY docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["catalina.sh", "run"]
