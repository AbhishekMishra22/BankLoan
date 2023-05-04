package com.terralogic.loan.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

	@Value("${spring.kafka.topic.name}")
	private String topicName;

	@Value("${spring.kafka.topic1.name}")
	private String topic1Name;

	private KafkaTemplate<String, String> kafkaTemplate;

	public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String topic, String message)

	{
		logger.info(String.format("Message Sent %s", message));

		kafkaTemplate.send(topicName, message.toString());
	}

	public void sendResponse(String topic, String message) {
		logger.info(String.format("Response Sent %s", message));
		kafkaTemplate.send(topic1Name, message.toString());
	}

}
