# Kafka Test

아파치 카프카를 java/python 으로 이용하는 예제를 만들고 테스트 해보기 위함

## 카프카 설치

- 카프카 설치를 위해 docker/docker-compose 를 사용
- conf/ 폴더 아레에 있는 템플릿 파일을 이용해서 zookeeper,kafka 서버의 세팅을 진행
- [Dockerfile](./Dockerfile) 과 [build.sh](./build.sh) 을 이용해서 이미지 생성
- `docker-compose up` 을 이용해서 카프카 클러스터 구성

## Java

- [MyConsumer](./mykafka-java/src/main/java/pe/fwani/MyConsumer.java) 를 실행하여 Consumer 연결
- [MyProducer](./mykafka-java/src/main/java/pe/fwani/MyProducer.java) 를 실행하여 Producer 연결
- Producer 를 통해 메세지 전송
- Consumer 를 통해 메세지 확인

## Python

- [myconsumer](./mykafka-python/myconsumer.py) 를 실행하여 Consumer 연결
- [myproducer](./mykafka-python/myproducer.py) 를 실행하여 Producer 연결
- Producer 를 통해 메세지 전송
- Consumer 를 통해 메세지 확인
