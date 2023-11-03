# Use an official MySQL runtime as a parent image
FROM mysql:latest

# Set the root password
ENV MYSQL_ROOT_PASSWORD=root

# Create a database and user
ENV MYSQL_DATABASE=rest_with_spring_boot
ENV MYSQL_USER=admin
ENV MYSQL_PASSWORD=1234567

# Specify the MySQL port
EXPOSE 3306

