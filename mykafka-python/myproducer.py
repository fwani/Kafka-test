import sys

from kafka import KafkaProducer

if __name__ == '__main__':
    finish_msg = 'exit'
    topic_name = 'fwani'
    producer = KafkaProducer(
        bootstrap_servers='0.0.0.0:9092,0.0.0.0:9093',
        value_serializer=str.encode,
    )

    while True:
        print("Input > ", end=' ')
        msg = sys.stdin.readline().strip()
        producer.send(topic_name, value=msg)
        if msg == finish_msg:
            producer.close()
            break
