package com.raymond.kafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
	
	@KafkaListener(topics = {"raymond-test1111"})
	public void receiveMessage(ConsumerRecord<?, ?> record) {
		System.out.println("消费者1"+record.value().toString());
		
	}
	@KafkaListener(topics = {"user-test"})
	public void receiveUserMessage(ConsumerRecord<?, ?> record) {
		System.out.println("消费者2"+ record.value());
	}
}
