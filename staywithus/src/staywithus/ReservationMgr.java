package staywithus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class ReservationMgr {
	
	private int r_capacity;
	private int p_cost;
	private String r_status;

	// �Ź� ���� �������� ������ �Ȱɸ��� pool�� ���� �ν��Ͻ��� ���
	private DBConnectionMgr pool;

	// ������
	public ReservationMgr() {
		// �ν��Ͻ�ȭ ���Ѽ� ��������
		pool = DBConnectionMgr.getInstance();
	}

//	// INSERT : Bean���� ��¥ �ֱ�
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
//			// executeUpdate : insert, update, delete ���๮
//			result = pstmt.executeUpdate();
//
//			pstmt.close();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		return result;
//	} // --- Bean���� ��¥ �ֱ�

	
	
	// (�׽�Ʈ)INSERT : ��¥ �ֱ�
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

			// executeUpdate : insert, update, delete ���๮
			int rsInsertDate = pstmt.executeUpdate();
			
			// ����� ���ڵ� ���� : ���� �� ó�� : 0, �������� ó�� : 1 (1�྿ �����Ŵϱ�)
			if (rsInsertDate == 1)
				flagForInsertDate = true; // ���� �ִٸ� true ��ȯ
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForInsertDate;
	} // --- (�׽�Ʈ) ��¥ �ֱ�

	

	// SELECT : �ߺ���¥ üũ
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

			pstmt.setInt(1, room); // 1�� ù��° ?�� �ǹ�
			pstmt.setDate(2, startDate); // 2�� �ι�° ?�� �ǹ�
			pstmt.setDate(3, startDate); // 3�� ����° ?�� �ǹ�
			pstmt.setDate(4, endDate); // 4�� ����° ?�� �ǹ�
			pstmt.setDate(5, endDate); // 5�� ����° ?�� �ǹ�
			
			// executeQuery : select ���๮
			rs = pstmt.executeQuery();

			if (rs.next())
				flagForDateDup = true; // ���� �ִٸ� true ��ȯ -> �ߺ��� ������ �ִ�.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flagForDateDup;
	}
	

		// �뺰 �ִ�����ο��� �����ο� üũ
		public int capacityChk(int room) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			boolean flagForCapaChk = false;

			try {
				con = pool.getConnection();

				// ������
				sql = "SELECT r_capacity " 
				+ "FROM room " 
				+ "WHERE r_room = ?";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, room); // 1�� ù��° ?�� �ǹ�

				// executeQuery : select ���๮
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

		// (�뺰 1�ڴ� ���� * �����ϼ�) �հ� ���� üũ >> ��Ŭ�������� diffDays ���ؼ� ���ϱ�
		public int totalCostChk(int room) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			boolean flagForCapaChk = false;

			try {
				con = pool.getConnection();

				// ������
				sql = "SELECT cost " 
				+ "FROM room " 
				+ "WHERE r_room = ?";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, room); // 1�� ù��° ?�� �ǹ�

				// executeQuery : select ���๮
				rs = pstmt.executeQuery();

				if (rs.next()) {
					flagForCapaChk = true; 
					p_cost = rs.getInt(1); // �뺰 1�ڴ� ���� ���ͼ� int�� p_cost�� �ֱ�
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return p_cost;
		}
		
		
}
