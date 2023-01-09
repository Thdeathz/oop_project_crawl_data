package app.history.person;

import java.util.List;

import app.history.dynasty.Dynasty;
import app.history.event.Event;

public class Person {
	private int id;
	private String name;
	private String givenName = "Không rõ";
	private String father = "Không rõ", reign;
	private String dateOfBirth = "Không rõ";
	private String dateOfDeath = "Không rõ";
	private String description;
	private Dynasty dynasty;
	private List<Event> lstEvent;
	private static int nbPerson = 0;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getFather() {
		return father;
	}

	public String getReign() {
		return reign;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getDateOfDeath() {
		return dateOfDeath;
	}

	public String getDescription() {
		return description;
	}

	public Dynasty getDynasty() {
		return dynasty;
	}

	public List<Event> getLstEvent() {
		return lstEvent;
	}

	/*
	 * thêm sự kiện liên quan
	 * return true nếu thành công
	 * return false nếu đã tồn tại
	 */
	public boolean addEvent(Event e) {
		if (lstEvent.contains(e)) {
			System.out.println("Already exist");
			return false;
		} else {
			getLstEvent().add(e);
			System.out.println("Add successfully");
			return true;
		}
	}

	public boolean removeEvent(Event e) {
		if (lstEvent.remove(e)) {
			System.out.println("Remove successfully");
			return true;
		} else {
			System.out.println("Event list empty or not found event");
			return false;
		}
	}

	public Person(String name, String givenName, String father, String dob, String dod, String desc, Dynasty dynasty) {
		this.id = ++nbPerson;
		this.name = name;
		this.givenName = givenName;
		this.father = father;
		this.dateOfBirth = dob;
		this.dateOfDeath = dod;
		this.description = desc;
		this.dynasty = dynasty;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person p2 = (Person) obj;
			return getName().equals(p2.getName()) && getFather().equals(p2.getFather());
		}
		return false;
	}
}
