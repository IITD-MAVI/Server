package iitd.assistech.mavi.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateEvent<T> extends Event {
	
	@JsonIgnore
	private static final String OPERATION = "CREATE";
	
	private T data;
	
	public CreateEvent() {
		super(OPERATION);
	}
	
	public CreateEvent(T data) {
		super(data.getClass().getSimpleName(), OPERATION);
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
		this.setCollection(data.getClass().getSimpleName());
	}

}
