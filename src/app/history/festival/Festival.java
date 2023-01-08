package app.history.festival;

import java.util.ArrayList;
import java.util.List;

import app.history.event.Event;
import app.history.person.Person;
import app.history.relic.Relic;

public class Festival {
	
	private String time;
	private String destination;
	private String description;
	private List<Event> relativeEvents = new ArrayList<Event>();
	private List<Person> relativeCharacter = new ArrayList<Person>();
	private List<Relic> relativeRelic = new ArrayList<Relic>();
	private int id;

	public Festival() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Festival(String time, String destination, String description, List<Event> relativeEvents,
			List<Person> relativeCharacter, List<Relic> relativeRelic, int id) {
		super();
		this.time = time;
		this.destination = destination;
		this.description = description;
		this.relativeEvents = relativeEvents;
		this.relativeCharacter = relativeCharacter;
		this.relativeRelic = relativeRelic;
		this.id = id;
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

	public List<Event> getRelativeEvents() {
		return relativeEvents;
	}

	public void setRelativeEvents(List<Event> relativeEvents) {
		this.relativeEvents = relativeEvents;
	}

	public List<Person> getRelativeCharacter() {
		return relativeCharacter;
	}

	public void setRelativeCharacter(List<Person> relativeCharacter) {
		this.relativeCharacter = relativeCharacter;
	}

	public List<Relic> getRelativeRelic() {
		return relativeRelic;
	}

	public void setRelativeRelic(List<Relic> relativeRelic) {
		this.relativeRelic = relativeRelic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
