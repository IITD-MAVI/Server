package iitd.assistech.mavi.event;

public abstract class Event {
	
	private String collection;
	private String operation;
	
	public Event(String operation) {
		this.operation = operation;
	}
	
	public Event(String collection, String operation) {
		this.collection = collection;
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}


}
