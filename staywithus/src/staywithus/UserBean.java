package staywithus;

/*빈즈(Beans:콩) 만드는 순서
 * 1.테이블 컬럼별로 private 변수 선언
 * 2.getter & setter 만들기
 * 3.빈즈는 테이블의 레코드를 담는 데이터 덩어리*/
public class UserBean/*테이블명+Bean*/ {
	
	//DB에 전화번호 데이터타입 CHAR로 하기 (정수가 아니라 숫자의 조합이니까)
	private String id, pwd, name, email, phone, birthday, gender;
	private int mode;
	
	public UserBean() {
	}
	
	public UserBean(String id, String pwd, String name, String email, String phone, String birthday,String gender, int mode) {	
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.birthday = birthday;
		this.gender = gender;
		this.mode = mode;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = 0;
	}
}



