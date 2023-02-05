package app.history.dynasty;

import java.util.List;

import app.history.person.Person;
import app.history.storage.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Dynasty {
	// id = ++cnt dùng để đếm
	public static int cnt = 0;
	private int id;
	private String name; // tên triều đại
	private String exitedTime; // thời gian tồn tại triều
	private String capital; // kinh thành ở đâu
	private List <String> kingNameL;
	private ObservableList <Person> listOfKing = FXCollections.observableArrayList(); // vua

	/**
	 * Constructer thêm sự kiện triều đại lịch sử
	 *
	 */
	public Dynasty(String name, String exitedTime, String capital, List <String> kingNameL) {
		id = ++cnt;
		this.name = name;
		this.exitedTime = exitedTime;
		this.capital = capital;
		this.kingNameL = kingNameL;
	}

	public Dynasty() {
		id = ++cnt;
	}

	public Dynasty(String name) {
		this.name = name;
	}

	// các phương thức gettor
	public List <Person> getKing() {
		return listOfKing;
	}
	public String getExitedTime()
	{
		return exitedTime;
	}
	public String getName() {
		return name;
	}
	public String getCapital() {
		return capital;
	}
	public int getId() {
		return id;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}
	public void setExitedTime(String exitedTime) {
		this.exitedTime = exitedTime;
	}
	public void setKingNameL(List<String> kingNameL) {
		this.kingNameL = kingNameL;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setListOfKing(ObservableList<Person> listOfKing) {
		this.listOfKing = listOfKing;
	}

	public void addKing() {
		for (String name: kingNameL) {
			Person person = new Person(name);
			int index = Storage.persons.indexOf(person);
			if (index != -1 && !listOfKing.contains(person)) {
				listOfKing.add(Storage.persons.get(index));
				System.out.println("Them thanh cong " + name);
			} else {
				System.out.println("Khong thanh cong " + name);
			}
		}
	}

	/**
	 * Đinh nghĩa bằng nhau khi name của chúng bằng nhau
	 *
	 * @return true : if (name1 == name2)
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Dynasty) {
			Dynasty dynasty = (Dynasty) obj;
			return dynasty.getName().equals(this.name);
		}
		return false;
	}
}
