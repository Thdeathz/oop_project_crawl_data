package app.history.festival;

import java.util.ArrayList;
import java.util.List;

import app.history.event.Event;
import app.history.person.Person;
import app.history.relic.Relic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Festival {

	public static int cnt = 0;
	private String name;
	private String time;
	private String destination;
	private String description;
	private List<String> relativeEventsName = new ArrayList<String>();
	private List<String> relativeCharacterName = new ArrayList<String>();
	private List<String> relativeRelicName = new ArrayList<String>();
	ObservableList<Event> relativeEvents = FXCollections.observableArrayList();
	ObservableList<Person> relativeCharacters = FXCollections.observableArrayList();
	ObservableList<Relic> relativeRelics = FXCollections.observableArrayList();
	private int id;
	
	public Festival(String name, String time, String destination, String description, List<String> relativeEventsName,
			List<String> relativeCharacterName, List<String> relativeRelicName, ObservableList<Event> relativeEvents,
			ObservableList<Person> relativeCharacters, ObservableList<Relic> relativeRelics, int id) {
		super();
		this.name = name;
		this.time = time;
		this.destination = destination;
		this.description = description;
		this.relativeEventsName = relativeEventsName;
		this.relativeCharacterName = relativeCharacterName;
		this.relativeRelicName = relativeRelicName;
		this.relativeEvents = relativeEvents;
		this.relativeCharacters = relativeCharacters;
		this.relativeRelics = relativeRelics;
		this.id = cnt++;
	}

	public Festival(String name, String time, String destination, String description) {
		this.name = name;
		this.time = time;
		this.destination = destination;
		this.description = description;
		this.id = cnt++;
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

	public int getId() {
		return id;
	}

	public List<String> getRelativeEventsName() {
		return relativeEventsName;
	}

	public void setRelativeEventsName(List<String> relativeEventsName) {
		this.relativeEventsName = relativeEventsName;
	}

	public List<String> getRelativeCharacterName() {
		return relativeCharacterName;
	}

	public void setRelativeCharacterName(List<String> relativeCharacterName) {
		this.relativeCharacterName = relativeCharacterName;
	}

	public List<String> getRelativeRelicName() {
		return relativeRelicName;
	}

	public void setRelativeRelicName(List<String> relativeRelicName) {
		this.relativeRelicName = relativeRelicName;
	}

	public ObservableList<Event> getRelativeEvents() {
		return relativeEvents;
	}

	public void setRelativeEvents(ObservableList<Event> relativeEvents) {
		this.relativeEvents = relativeEvents;
	}

	public ObservableList<Person> getRelativeCharacters() {
		return relativeCharacters;
	}

	public void setRelativeCharacters(ObservableList<Person> relativeCharacters) {
		this.relativeCharacters = relativeCharacters;
	}

	public ObservableList<Relic> getRelativeRelics() {
		return relativeRelics;
	}

	public void setRelativeRelics(ObservableList<Relic> relativeRelics) {
		this.relativeRelics = relativeRelics;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Festival) {
			Festival t = (Festival) obj;
			return this.getName().equals(t.getName());
		}
		return false;
	}
}
