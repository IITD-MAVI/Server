package iitd.assistech.mavi.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MessageProcessor {
	
//	@Autowired
	private static MongoTemplate mongoTemplate;

	@Autowired
	private MessageProcessor(MongoTemplate mongoTemplate) {
		MessageProcessor.mongoTemplate = mongoTemplate;
	}

	public static String process(String jsonMessage) {
		mongoTemplate.save(jsonMessage, "General");
		return "";
	}
	
}
