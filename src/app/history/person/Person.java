package app.history.person;


import app.history.dynasty.Dynasty;
import app.history.storage.Storage;

public class Person {
	private int id;
	private String name;
	private String givenName = "Không rõ";
	private String father = "Không rõ", reign;
	private String dateOfBirth = "Không rõ";
	private String dateOfDeath = "Không rõ";
	private String description;
	private Dynasty dynasty;
	private String dynastyName;
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

	public String getDynastyName() {
		return dynastyName;
	}

	public void setDynasty() {
		int index = Storage.dynasties.indexOf(new Dynasty(dynastyName));
		if (index != -1){
			dynasty = Storage.dynasties.get(index);
			System.out.println("Them thanh cong " + dynastyName);
		} else {
			System.out.println("Khong thanh cong" + dynastyName);
		}
	}

	public Person() {};

	public Person(String name) {
		this.name = name;
	}

	public Person(String name, String givenName, String father, String reign, String dob, String dod, String desc, String dynasty) {
		this.id = ++nbPerson;
		this.name = name;
		this.givenName = givenName;
		this.father = father;
		this.reign = reign;
		this.dateOfBirth = dob;
		this.dateOfDeath = dod;
		this.description = desc;
		this.dynastyName = dynasty;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person p2 = (Person) obj;
			return getName().equals(p2.getName());
		}
		return false;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public void setDynastyName(String dynastyName) {
		this.dynastyName = dynastyName;
	}

	public void setReign(String reign) {
		this.reign = reign;
	}
}
