from pyspark.sql import SparkSession

if __name__ == '__main__':
    spark = SparkSession.builder \
        .master("local") \
        .config("spark.jars.packages", "org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.0") \
        .getOrCreate()
    kafka_bootstrap_servers = '0.0.0.0:9092,0.0.0.0:9093'
    topic = 'spark1'
    df = spark \
        .readStream \
        .format("kafka") \
        .option("kafka.bootstrap.servers", kafka_bootstrap_servers) \
        .option("subscribe", topic) \
        .load()

    ds = df \
        .writeStream \
        .format("console") \
        .option("kafka.bootstrap.servers", kafka_bootstrap_servers) \
        .option("subscribe", topic) \
        .start()

    ds.awaitTermination()
