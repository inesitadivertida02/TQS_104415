# Use official PostgreSQL image
FROM postgres:12

# Set environment variables (change these if needed)
ENV POSTGRES_USER=test_user
ENV POSTGRES_PASSWORD=securePass123
ENV POSTGRES_DB=test_db

# Copy your migration SQL script to be executed at container startup
COPY src/test/resources/db/migration/V001__INIT.sql /docker-entrypoint-initdb.d/

# Expose PostgreSQL port
EXPOSE 5432