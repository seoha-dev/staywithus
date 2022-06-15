/* 예약테이블 빈 | 마지막 수정날짜: 2022-03-25 | 마지막 수정인: 김서하 */
package staywithus;

/*빈즈(Beans:콩) 만드는 순서
 * 1.테이블 컬럼별로 private 변수 선언
 * 2.getter & setter 만들기
 * 3.빈즈는 테이블의 레코드를 담는 데이터 덩어리*/

public class ReservationBean/* 테이블명+Bean */ {
	// 컬럼별 private 변수 선언
	private String id;
	private String r_room;
	private java.sql.Date startdate;
	private java.sql.Date enddate;
	private String headcount;
	private String r_status;
	private int p_cost;
	private String res_no;

	// 디폴트 생성자
	public ReservationBean() {

	}

	// 매개변수 생성자
	public ReservationBean(String id, String r_room, java.sql.Date startdate,
			java.sql.Date enddate, String headcount, String r_status,
			int p_cost,String res_no) {
		
		this.id = id;
		this.r_room = r_room;
		this.startdate = startdate;
		this.enddate = enddate;
		this.headcount = headcount;
		this.r_status = r_status;
		this.p_cost = p_cost;
		this.res_no = res_no;
	}

	// getter & setter 생성
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getR_room() {
		return r_room;
	}

	public void setR_room(String r_room) {
		this.r_room = r_room;
	}

	public java.sql.Date getStartdate() {
		return startdate;
	}

	public void setStartdate(java.sql.Date startdate) {
		this.startdate = startdate;
	}

	public java.sql.Date getEnddate() {
		return enddate;
	}

	public void setEnddate(java.sql.Date enddate) {
		this.enddate = enddate;
	}

	public String getHeadcount() {
		return headcount;
	}

	public void setHeadcount(String headcount) {
		this.headcount = headcount;
	}

	public String getR_status() {
		return r_status;
	}

	public void setR_status(String r_status) {
		this.r_status = r_status;
	}

	public int getP_cost() {
		return p_cost;
	}

	public void setP_cost(int p_cost) {
		this.p_cost = p_cost;
	}
	public String getRes_no() {
		return res_no;
	}

	public void setRes_no(String res_no) {
		this.res_no = res_no;
	}

}
