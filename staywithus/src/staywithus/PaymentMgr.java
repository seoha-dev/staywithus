package staywithus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PaymentMgr {
	private int totalCost;

	// 매번 정보 가져오는 과부하 안걸리게 pool에 만든 인스턴스를 담기
	private DBConnectionMgr pool;
	
	public PaymentMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	// UPDATE: 결제상태 변경 (결제 전 -> 결제완료)
		public boolean updateStatus(String userId) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flagForStatus = false;
			try {
				con = pool.getConnection();
				sql = "UPDATE reservation SET r_status = ? "
						+ "WHERE id = ?";//values (데이터값들)
				pstmt = con.prepareStatement(sql);
				
				String r_status = "결제완료";
				pstmt.setString(1, r_status);//1은 첫번째 ?를 의미
				pstmt.setString(2, userId);//2는 첫번째 ?를 의미
			
				//적용된 레코드 개수 : 에러 및 처리 : 0, 정상적인 처리 : 1 
				int cnt = pstmt.executeUpdate();
				
				if(cnt==1) 
					flagForStatus = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flagForStatus;
		} // -- 결제상태 변경	
	
	
		// SELECT : 예약내역의 총 가격 (예약 레코드 합) 가져오기
		public int totalCostChk(String userId) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			boolean flagForTotalCost = false;
			
			try {
				con = pool.getConnection();
				
				//쿼리문
				// GROUP BY의 위치는 WHERE 다음, HAVING 전의 자리
				sql = "SELECT p_cost " + 
				      "FROM reservation " +
				      "WHERE id = ? AND r_status = ? ";
				pstmt = con.prepareStatement(sql);
				
				String r_status = "결제 전";
				
				pstmt.setString(1, userId); // 1 : 첫번째 물음표
				pstmt.setString(2, r_status); // 2 : 첫번째 물음표
				// executeQuery : select 실행문
				rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					flagForTotalCost = true;  // 값이 있다면 true 반환
					totalCost = rs.getInt(1); // 1열 값(SUM(p_cost)) 가져와서 반환
				}
					

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return totalCost;
		}

		
		
}
