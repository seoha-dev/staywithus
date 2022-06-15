/* 관리자페이지 예약관리 매니저 | 마지막 수정날짜: 2022-03-27 | 마지막 수정인: 김서하 */
package staywithus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JOptionPane;

// DB연동의 기능의 클래스 
public class AdminMgr {
	// 매번 정보 가져오는 과부하 안걸리게 pool에 만든 인스턴스를 담기
	private DBConnectionMgr pool;

	public AdminMgr() {
		pool = DBConnectionMgr.getInstance();
	}

	
	//DB 전체예약내역 조회
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector selectAll() { 

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector data = new Vector<>();

		data.clear();

		try {
			con = pool.getConnection();

			// 쿼리문
			sql = "select * from reservation";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Vector in = new Vector<String>(); // 1개의 레코드 저장하는 벡터 생성

				String id = rs.getString(1);
				String r_room = rs.getString(2);
				String startdate = rs.getString(3);
				String enddate = rs.getString(4);
				String headcount = rs.getString(5);
				String r_status = rs.getString(6);
				String p_cost = rs.getString(7);
				String res_no = rs.getString(8);


				// 벡터에 각각의 값 추가

				in.add(id);
				in.add(r_room);
				in.add(startdate);
				in.add(enddate);
				in.add(headcount);
				in.add(r_status);
				in.add(p_cost);
				in.add(res_no);
						
				// 전체 데이터를 저장하는 벡터에 in(1명의 데이터 저장) 벡터 추가
				data.add(in);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return data;
	}


	//예약삭제: ReservationAdmin 텍스트필드에서 예약번호만 끌어와서 삭제 실행
	public void delete(String res_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		@SuppressWarnings("rawtypes")
		Vector data = new Vector<>();

		data.clear();
		
		try {
			con = pool.getConnection();

			// 쿼리문
			sql = "delete from reservation where res_no = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, res_no);
			//pstmt.executeUpdate();
			
			int cnt = pstmt.executeUpdate();
			if(cnt==1) 
				JOptionPane.showMessageDialog(null, "예약삭제 완료.");
			else 
				JOptionPane.showMessageDialog(null, "예약삭제 실패."); 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//기존 예약 체크
	public boolean resChk(String r_room, java.sql.Date startDate, java.sql.Date endDate, int res_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		boolean flagForResChk = false;

		try {
			con = pool.getConnection();
			// 쿼리문
			sql = "SELECT * " 
					+ "FROM reservation " 
					+ "WHERE (r_room = ?) AND (((?<=enddate) AND (?>=startdate)) "
					+ "OR((?<=enddate) AND (?>=startdate))) AND res_no != ?;";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, r_room); // 1은 첫번째 ?를 의미
			pstmt.setDate(2, startDate); // 2은 두번째 ?를 의미
			pstmt.setDate(3, startDate); // 3은 세번째 ?를 의미
			pstmt.setDate(4, endDate); // 4은 네번째 ?를 의미
			pstmt.setDate(5, endDate); // 5은 다섯번째 ?를 의미
			pstmt.setInt(6, res_no); // 6은 여섯번째 ?를 의미
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flagForResChk = true;
			}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return flagForResChk;
		}	
	
	// 새 예약으로 업데이트
	public boolean resUpdt(ReservationBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flagForResUpdt = false;
		try {
			con = pool.getConnection();
			sql = "UPDATE reservation SET r_room =?, startdate =?, enddate =?, headcount =?, p_cost =? "
					+ "WHERE res_no = ?";//values (데이터값들)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getR_room());//1은 첫번째 ?를 의미
			pstmt.setDate(2, bean.getStartdate());//2은 두번째 ?를 의미
			pstmt.setDate(3, bean.getEnddate());//3은 세번째 ?를 의미
			pstmt.setString(4, bean.getHeadcount());//4은 네번째 ?를 의미
			pstmt.setInt(5, bean.getP_cost());
			pstmt.setString(6, bean.getRes_no());
			
			//적용된 레코드 개수 : 에러 및 처리 : 0, 정상적인 처리 : 1 
			int cnt = pstmt.executeUpdate();
			if(cnt==1) flagForResUpdt = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForResUpdt;
	}

	// SELECT: ReservationAdmin 선택된 아이디 예약내역만 조회
	public Vector selectId(String id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector data = new Vector<>();
		data.clear();

		try {
			con = pool.getConnection();
			// 쿼리문
			sql = "select * from reservation where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector in = new Vector<String>(); // 1개의 레코드 저장하는 벡터 생성

//				String id = rs.getString(1);
				String r_room = rs.getString(2);
				String startdate = rs.getString(3);
				String enddate = rs.getString(4);
				String headcount = rs.getString(5);
				String r_status = rs.getString(6);
				String p_cost = rs.getString(7);
				String res_no = rs.getString(8);
				System.out.println("백터에넣기");
				// 벡터에 각각의 값 추가

				in.add(id);
				in.add(r_room);
				in.add(startdate);
				in.add(enddate);
				in.add(headcount);
				in.add(r_status);
				in.add(p_cost);
				in.add(res_no);

				// 전체 데이터를 저장하는 벡터에 in(1명의 데이터 저장) 벡터 추가
				data.add(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return data;
	}// -- 특정 id 예약내역만 조회

}

	

