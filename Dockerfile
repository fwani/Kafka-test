FROM openjdk:22-jdk-slim

EXPOSE 2181
EXPOSE 9092
WORKDIR /app

RUN apt update && \
    apt install -y curl && \
    curl -O https://dlcdn.apache.org/kafka/3.6.0/kafka_2.13-3.6.0.tgz && \
    tar -xzf kafka_2.13-3.6.0.tgz && \
    ln -s kafka_2.13-3.6.0 kafka && \
    rm -rf /var/lib/apt/lists/*
