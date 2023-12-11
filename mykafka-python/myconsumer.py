from kafka import KafkaConsumer

if __name__ == '__main__':
    topic_name = 'fwani'
    group_name = 'group2'
    consumer = KafkaConsumer(
        bootstrap_servers='0.0.0.0:9092,0.0.0.0:9093',
        # auto_offset_reset='earliest',
        value_deserializer=bytes.decode,
    )
    consumer.subscribe([topic_name])
    for record in consumer:
        msg = record.value
        print(f"read msg: {record.value}")
        if msg == 'exit':
            break
    consumer.close()
