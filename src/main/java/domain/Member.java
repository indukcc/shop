package domain;

public class Member {
	//CRUD(Create, Read, Update, Delete) : 데이터베이스 기본 연산 
	//데이터베이스 테이블의 필드와 관련성이 있음
	//Data Access Layer 에서 사용하는 객체
	//DTO(Data Transfer Object), VO(Valuable Object) - creat, update, delete 포함 
	//VO(Valuable Object) - read 중심, select query 대상
	private Long id;
	private String email;
	private String pw;
	private String name;
	private String phone;
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
