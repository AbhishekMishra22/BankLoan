package com.terralogic.loan.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
	
	@Value("${spring.kafka.topic.name}")
	private String topicName;

	@Value("${spring.kafka.topic1.name}")
	private String topic1Name;

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "group_id")
	public void consume(String message) {

		logger.info(String.format("Message received -> %s", message));

	}
	@KafkaListener(topics = "${spring.kafka.topic1.name}",groupId = "group_id")
	public void consumeResponse(String message) {
		logger.info(String.format("Response received -> %s", message));
	}

}
