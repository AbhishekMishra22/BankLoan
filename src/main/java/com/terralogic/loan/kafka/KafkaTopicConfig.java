package com.terralogic.loan.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
public class KafkaTopicConfig {
	@Value("${spring.kafka.topic.name}")
	private String topicName;

	@Value("${spring.kafka.topic1.name}")
	private String topic1Name;

	@Bean
	public NewTopic consumerTopic() {
		return TopicBuilder.name(topicName).build();
	}

	@Bean
	public NewTopic consumerTopic1() {
		return TopicBuilder.name(topic1Name).build();
	}

}
