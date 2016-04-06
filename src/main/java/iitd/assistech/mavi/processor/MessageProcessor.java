package iitd.assistech.mavi.processor;

import java.util.List;

import iitd.assistech.mavi.utility.JsonPath;
import iitd.assistech.mavi.utility.JsonUtil;

import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.BasicQuery;
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
		LOG.debug("Query String: " + JsonUtil.toJson(jsonPath.read(QUERY_PATH)));
		BasicQuery query = new BasicQuery(JsonUtil.toJson(jsonPath.read(QUERY_PATH)));
		List<String> results =  mongoTemplate.find(query, String.class, jsonPath.read(COLLECTION_PATH));
		LOG.debug("Number of documents retreived: " + results.size());
		return JsonUtil.toJson(results);
	}

	private static String update(JsonPath jsonPath) {
		LOG.debug("Query String: " + JsonUtil.toJson(jsonPath.read(QUERY_PATH)));
		BasicQuery query = new BasicQuery(JsonUtil.toJson(jsonPath.read(QUERY_PATH)));
		List<String> results = mongoTemplate.find(query, String.class, jsonPath.read(COLLECTION_PATH));
		LOG.debug("Number of documents retreived: " + results.size());
		
		if(results.isEmpty()) {
			LOG.info("No documents to be updated.");
		} else {
			LOG.debug("Update String: " + JsonUtil.toJson(jsonPath.read(UPDATE_PATH)));
			BasicUpdate update = new BasicUpdate(JsonUtil.toJson(jsonPath.read(UPDATE_PATH)));
			mongoTemplate.updateMulti(query, update, String.class, jsonPath.read(COLLECTION_PATH));
			LOG.info("Documents updated in MongoDB.");
		}
		
		return JsonUtil.toJson(results);
	}
	
	private static String delete(JsonPath jsonPath) {
		LOG.debug("Query String: " + JsonUtil.toJson(jsonPath.read(QUERY_PATH)));
		BasicQuery query = new BasicQuery(JsonUtil.toJson(jsonPath.read(QUERY_PATH)));
		List<String> results = mongoTemplate.find(query, String.class, jsonPath.read(COLLECTION_PATH));
		LOG.debug("Number of documents retreived: " + results.size());
		
		if(results.isEmpty()) {
			LOG.info("No documents to be deleted.");
		} else {
			mongoTemplate.remove(query, jsonPath.read(COLLECTION_PATH));
			LOG.info("Documents deleted from MongoDB.");
		}
		
		return JsonUtil.toJson(results);
	}
	
}
