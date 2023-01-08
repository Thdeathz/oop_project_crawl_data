package app.history.relic;

import java.util.List;

import app.history.festival.Festival;
import app.history.person.Person;
import javafx.collections.FXCollections;

public class Relic {
	// id = ++cnt; cnt dùng để đếm
	public static int cnt = 0;
	int id;
	String title;
	String content;
	String address;
	List<Festival> relatedFestival = FXCollections.observableArrayList();
	List<Person> relatedHistoricalPerson = FXCollections.observableArrayList();

	/**
	 * Constructer thêm sự kiện lịch sử
	 * 
	 */
	public Relic(int id, String title, String content, String address, List<Festival> relatedFestival,
			List<Person> relatedHistoricalPerson) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.address = address;
		this.relatedFestival = relatedFestival;
		this.relatedHistoricalPerson = relatedHistoricalPerson;
	}

	// get title
	public String getTitle() {
		return title;
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

}
