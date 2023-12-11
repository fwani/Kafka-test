from pyspark.sql import SparkSession
import pyspark.sql.functions as F

if __name__ == '__main__':
    spark = SparkSession.builder \
        .master("local") \
        .config("spark.jars.packages", "org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.0") \
        .getOrCreate()
    kafka_bootstrap_servers = '0.0.0.0:9092,0.0.0.0:9093'
    topic = 'fwani,spark1'

    df = spark \
        .readStream \
        .format("kafka") \
        .option("kafka.bootstrap.servers", kafka_bootstrap_servers) \
        .option("subscribe", topic) \
        .option("startingOffsets", "earliest") \
        .load()

    df.groupby('topic').agg(F.count('value').alias('value')) \
        .writeStream \
        .outputMode("update") \
        .format("console") \
        .start() \
        .awaitTermination()

    # df.groupby('topic').agg(F.count('value').cast('string').alias('value')) \
    #     .writeStream \
    #     .outputMode("update") \
    #     .format("kafka") \
    #     .option("kafka.bootstrap.servers", kafka_bootstrap_servers) \
    #     .option("checkpointLocation", "/tmp/vaquarkhan/checkpoint") \
    #     .start() \
    #     .awaitTermination()
