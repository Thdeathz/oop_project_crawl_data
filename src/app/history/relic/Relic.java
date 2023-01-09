package app.history.relic;

import java.util.List;

import app.history.festival.Festival;
import app.history.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Relic {
	// id = ++cnt; cnt dùng để đếm
	public static int cnt = 0;
	int id;
	String title;
	String content;
	String address;
	ObservableList<Festival> relatedFestival = FXCollections.observableArrayList();
	ObservableList<Person> relatedHistoricalPerson = FXCollections.observableArrayList();

	public Relic(int id, String title, String content, String address) {
		this.id = ++cnt;
		this.title = title;
		this.content = content;
		this.address = address;

	}

	/**
	 * Đinh nghĩa bằng nhau khi title của chúng bằng nhau
	 * 
	 * @return true : if (title1 == title2)
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Relic) {
			Relic relic = (Relic) obj;
			return relic.getTitle().equals(this.title);
		}
		return false;
	}

	public String getTitle() {
		return title;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getAddress() {
		return address;
	}

	public List<Festival> getRelatedFestival() {
		return relatedFestival;
	}

	public List<Person> getRelatedHistoricalPerson() {
		return relatedHistoricalPerson;
	}

	/**
	 * Hàm này dùng để thêm lễ hội vào relatedFestival
	 * 
	 */
	public void addFestival(Festival festival) {
		// check if festival not exist in relatedFestival. If not I will add
		if (!relatedFestival.contains(festival)) {
			relatedFestival.add(festival);
		} else
			System.out.print("This festival has existed");

	}

	/**
	 * Hàm này dùng để thêm nhân vật lịch sử vào relatedHistoricalPerson
	 * 
	 */
	public void addHistoricalPerson(Person person) {
		// check if person not exist in relatedHistoricalPerson. If not I will add
		if (!relatedHistoricalPerson.contains(person)) {
			relatedHistoricalPerson.add(person);
		} else
			System.out.print("This person has existed");

	}

}