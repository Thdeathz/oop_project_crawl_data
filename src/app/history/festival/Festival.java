package app.history.festival;

import java.util.ArrayList;
import java.util.List;

import app.history.event.Event;
import app.history.person.Person;
import app.history.relic.Relic;

public class Festival {
	
	public static int cnt = 0;
	private String name;
	private String time;
	private String destination;
	private String description;
	private List<Event> relativeEvents = new ArrayList<Event>();
	private List<Person> relativeCharacter = new ArrayList<Person>();
	private List<Relic> relativeRelic = new ArrayList<Relic>();
	private int id;

	public Festival(String name, String time, String destination, String description, List<Event> relativeEvents,
			List<Person> relativeCharacter, List<Relic> relativeRelic, int id) {
		this.id = cnt++;
		this.name = name;
		this.time = time;
		this.destination = destination;
		this.description = description;
		this.relativeEvents = relativeEvents;
		this.relativeCharacter = relativeCharacter;
		this.relativeRelic = relativeRelic;
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
	
	public boolean equals(Object obj) {
		if (obj instanceof Festival) {
			Festival t = (Festival) obj;
			return this.getName().equals(t.getName());
		}
		return false;
	}

}
