package staywithus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class ReservationMgr {
	
	private int r_capacity;
	private int p_cost;
	private String r_status;

	// 매번 정보 가져오는 과부하 안걸리게 pool에 만든 인스턴스를 담기
	private DBConnectionMgr pool;

	// 생성자
	public ReservationMgr() {
		// 인스턴스화 시켜서 가져오기
		pool = DBConnectionMgr.getInstance();
	}

//	// INSERT : Bean으로 날짜 넣기
//	public int insertDate(ReservationBean bean) {
//
//		int result = 0;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		String sql;
//
//		try {
//			con = pool.getConnection();
//			sql = "INSERT reservation (id, r_room, startdate, enddate, r_capacity, r_status, p_cost, res_no) "
//					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//			pstmt = con.prepareStatement(sql);
//
//			pstmt.setString(1, bean.getId());
//			pstmt.setInt(2, bean.getR_room());
//			pstmt.setInt(3, bean.getStartdate());
//			pstmt.setInt(4, bean.getEnddate());
//			pstmt.setInt(5, bean.getR_capacity());
//			pstmt.setString(6, bean.getR_status());
//			pstmt.setInt(7, bean.getP_cost());
//			pstmt.setInt(8, bean.getRes_no());
//
//			// executeUpdate : insert, update, delete 실행문
//			result = pstmt.executeUpdate();
//
//			pstmt.close();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		return result;
//	} // --- Bean으로 날짜 넣기

	
	
	// (테스트)INSERT : 날짜 넣기
	public boolean InsertDate(String userId, int room, java.sql.Date startDate, java.sql.Date endDate, int headcount, String r_status, int p_cost) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql;
		boolean flagForInsertDate = false;

		try {
			con = pool.getConnection();
			sql = "INSERT reservation (id, r_room, startdate, enddate, headcount, r_status, p_cost ) " 
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userId);
			pstmt.setInt(2, room);
			pstmt.setDate(3, startDate);
			pstmt.setDate(4, endDate);
			pstmt.setInt(5, headcount);
			pstmt.setString(6, r_status);
			pstmt.setInt(7, p_cost);

			// executeUpdate : insert, update, delete 실행문
			int rsInsertDate = pstmt.executeUpdate();
			
			// 적용된 레코드 개수 : 에러 및 처리 : 0, 정상적인 처리 : 1 (1행씩 넣을거니까)
			if (rsInsertDate == 1)
				flagForInsertDate = true; // 값이 있다면 true 반환
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForInsertDate;
	} // --- (테스트) 날짜 넣기

	

	// SELECT : 중복날짜 체크
	public boolean dateChk(int room, java.sql.Date startDate, java.sql.Date endDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		boolean flagForDateDup = false;

		try {
			con = pool.getConnection();
			sql = "SELECT * " 
			+ "FROM reservation " 
			+ "WHERE (r_room = ?) AND (((?<=enddate) AND (?>=startdate)) "
			+ "OR((?<=enddate) AND (?>=startdate)));";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, room); // 1은 첫번째 ?를 의미
			pstmt.setDate(2, startDate); // 2은 두번째 ?를 의미
			pstmt.setDate(3, startDate); // 3은 세번째 ?를 의미
			pstmt.setDate(4, endDate); // 4은 세번째 ?를 의미
			pstmt.setDate(5, endDate); // 5은 세번째 ?를 의미
			
			// executeQuery : select 실행문
			rs = pstmt.executeQuery();

			if (rs.next())
				flagForDateDup = true; // 값이 있다면 true 반환 -> 중복된 일정이 있다.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flagForDateDup;
	}
	

		// 룸별 최대수용인원과 예약인원 체크
		public int capacityChk(int room) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			boolean flagForCapaChk = false;

			try {
				con = pool.getConnection();

				// 쿼리문
				sql = "SELECT r_capacity " 
				+ "FROM room " 
				+ "WHERE r_room = ?";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, room); // 1은 첫번째 ?를 의미

				// executeQuery : select 실행문
				rs = pstmt.executeQuery();

				if (rs.next()) {
					flagForCapaChk = true; 
					r_capacity = rs.getInt(1); 
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return r_capacity;
		}

		// (룸별 1박당 가격 * 예약일수) 합계 가격 체크 >> 이클립스에서 diffDays 구해서 곱하기
		public int totalCostChk(int room) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			boolean flagForCapaChk = false;

			try {
				con = pool.getConnection();

				// 쿼리문
				sql = "SELECT cost " 
				+ "FROM room " 
				+ "WHERE r_room = ?";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, room); // 1은 첫번째 ?를 의미

				// executeQuery : select 실행문
				rs = pstmt.executeQuery();

				if (rs.next()) {
					flagForCapaChk = true; 
					p_cost = rs.getInt(1); // 룸별 1박당 가격 들고와서 int형 p_cost에 넣기
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return p_cost;
		}
		
		
}
