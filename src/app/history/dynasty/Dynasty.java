package app.history.dynasty;


import app.history.person.Person;

public class Dynasty {
	// id = ++cnt dùng để đếm
	public static int cnt = 0;
	private int id;
	private String name; // tên triều đại
	private String startTime; // thời gian bắt đầu triều đại
	private String endTime; // thời gian kết thúc triều đại
	private String capital; // kinh thành ở đâu
	private Person king; // vua
	
	/**
	 * Constructer thêm sự kiện triều đại lịch sử
	 * 
	 */
	public Dynasty(String name, String startTime, String endTime, String capital, Person king) {
		id = ++cnt;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.capital = capital;
		this.king = king;
	}
	
	// các phương thức gettor
	public Person getKing() {
		return king;
	}
	public String getEndTime() {
		return endTime;
	}
	public String getStartTime() {
		return startTime;
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
		Dynasty dynasty = new Dynasty("Ly", "", null, null, null);
		System.out.println(dynasty.getName() + " "  + dynasty.getId());
		Dynasty dynasty2 = new Dynasty("Ly2", "", null, null, null);
		System.out.println(dynasty2.getName() + " "  + dynasty2.getId());
		System.out.println(dynasty.equals(dynasty2));
	}
}
