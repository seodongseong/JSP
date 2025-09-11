package Ch02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestDto {
	private String username;
	private int age;
	private LocalDateTime date;
	
	//디폴트 생성자
	public TestDto() {}
	//모든인자 생성자
	public TestDto(String username, int age, LocalDateTime date) {
		super();
		this.username = username;
		this.age = age;
		this.date = date;
	}
	
	//getter and setter
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		System.out.println("!!!");
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDateTime getDate() {
		return date;
	}

//	public void setDate(LocalDateTime date) {
//		this.date =date;
//	}
	
	public void setStringtoDate(String date) {
		System.out.println("setStringtoDate : " + date);
		DateTimeFormatter fmtin = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		this.date = LocalDateTime.parse(date,fmtin);		
	}
	
	@Override
	public String toString() {
		return "TestDto [username=" + username + ", age=" + age + ", date=" + date + "]";
	}

	//toString





	
}