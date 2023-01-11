package app.history.event;

import java.util.ArrayList;
import java.util.List;

import app.history.person.Person;

public class Event {
	
	public static int cnt = 0;
	private String name;
	private String time;
	private String destination;
	private String description;
	private List<Person> relativePersons = new ArrayList<Person>();
	private int id;
	
	public Event(String time, String destination, String description, List<Person> relativePersons, int id,
			String name) {
		this.id = cnt++;
		this.time = time;
		this.destination = destination;
		this.description = description;
		this.relativePersons = relativePersons;
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Person> getRelativePersons() {
		return relativePersons;
	}

	public void setRelativePersons(List<Person> relativePersons) {
		this.relativePersons = relativePersons;
	}

	public int getId() {
		return id;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Event) {
			Event t = (Event) obj;
			return this.getName().equals(t.getName());
		}
		return false;
	}

}
