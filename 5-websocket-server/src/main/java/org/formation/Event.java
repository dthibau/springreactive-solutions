package org.formation;

public class Event {
	private String eventId;
	private int number;

    public Event() {}
    
	public Event(String eventId) {
		super();
		this.eventId = eventId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
