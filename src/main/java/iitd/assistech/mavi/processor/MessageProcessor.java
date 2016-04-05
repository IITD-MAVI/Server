package iitd.assistech.mavi.processor;

import iitd.assistech.mavi.utility.JsonPath;
import iitd.assistech.mavi.utility.JsonUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MessageProcessor {
	
	private static final Logger LOG = Logger.getLogger(MessageProcessor.class);
	
	private static final String COLLECTION_PATH = "$.collection";
	private static final String OPERATION_PATH = "$.operation";
	private static final String DATA_PATH = "$.data";
	private static final String QUERY_PATH = "$.query";
	private static final String UPDATE_PATH = "$.update";
	
//	@Autowired
	private static MongoTemplate mongoTemplate;

	@Autowired
	private MessageProcessor(MongoTemplate mongoTemplate) {
		MessageProcessor.mongoTemplate = mongoTemplate;
	}

	public static String process(String jsonMessage) {
		LOG.info("JSON message received by Processor: " + jsonMessage);
		JsonPath jsonPath = new JsonPath(jsonMessage);
		
		String operation = jsonPath.read(OPERATION_PATH);
		if("CREATE".equalsIgnoreCase(operation)) {
			return create(jsonPath);
		}
		else if("READ".equalsIgnoreCase(operation)) {
			return read(jsonPath);
		}
		else if("DELETE".equalsIgnoreCase(operation)) {
			return delete(jsonPath);
		}
		else if("UPDATE".equalsIgnoreCase(operation)) {
			return update(jsonPath);
		}
		else {
			String errorMessage = "This operation is not supported by Analytics Service. ";
			LOG.error(errorMessage);
			return "";
		}
		
	}
	
	private static String create(JsonPath jsonPath) {
		String toSave = JsonUtil.toJson(jsonPath.read(DATA_PATH));
		mongoTemplate.save(toSave, jsonPath.read(COLLECTION_PATH));
		LOG.info("New document saved in database:\n"+toSave);
		return toSave;
	}
	
	private static String read(JsonPath jsonPath) {
		
	}

	private static String update(JsonPath jsonPath) {
		
	}
	
	private static String delete(JsonPath jsonPath) {
		
	}
	
}
