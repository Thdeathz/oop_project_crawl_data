package app.history.dynasty;

import java.util.ArrayList;
import java.util.List;

import app.history.person.Person;

public class Dynasty {
	// id = ++cnt dùng để đếm
	public static int cnt = 0;
	private int id;
	private String name; // tên triều đại
	private String exitedTime; // thời gian tồn tại triều 
	private String capital; // kinh thành ở đâu
	private List <String> kingNameL;
	private List <Person> listOfKing = new ArrayList <Person>(); // vua
	
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
	public Dynasty()
	{
		id = ++cnt;
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
	public void setListOfKing(List<Person> listOfKing) {
		this.listOfKing = listOfKing;
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

	//hàm main chạy thử thôi
	public static void main(String[] args) {
		Dynasty dynasty = new Dynasty("Ly", "", null, null);
		System.out.println(dynasty.getName() + " "  + (dynasty.getId() + 3));
		Dynasty dynasty2 = new Dynasty("Ly2", "", null, null);
		System.out.println(dynasty2.getName() + " "  + dynasty2.getId());
		System.out.println(dynasty.equals(dynasty2));
	}
}
