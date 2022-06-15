package staywithus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PaymentMgr {
	private int totalCost;

	// �Ź� ���� �������� ������ �Ȱɸ��� pool�� ���� �ν��Ͻ��� ���
	private DBConnectionMgr pool;
	
	public PaymentMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	// UPDATE: �������� ���� (���� �� -> �����Ϸ�)
		public boolean updateStatus(String userId) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flagForStatus = false;
			try {
				con = pool.getConnection();
				sql = "UPDATE reservation SET r_status = ? "
						+ "WHERE id = ?";//values (�����Ͱ���)
				pstmt = con.prepareStatement(sql);
				
				String r_status = "�����Ϸ�";
				pstmt.setString(1, r_status);//1�� ù��° ?�� �ǹ�
				pstmt.setString(2, userId);//2�� ù��° ?�� �ǹ�
			
				//����� ���ڵ� ���� : ���� �� ó�� : 0, �������� ó�� : 1 
				int cnt = pstmt.executeUpdate();
				
				if(cnt==1) 
					flagForStatus = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flagForStatus;
		} // -- �������� ����	
	
	
		// SELECT : ���೻���� �� ���� (���� ���ڵ� ��) ��������
		public int totalCostChk(String userId) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			boolean flagForTotalCost = false;
			
			try {
				con = pool.getConnection();
				
				//������
				// GROUP BY�� ��ġ�� WHERE ����, HAVING ���� �ڸ�
				sql = "SELECT p_cost " + 
				      "FROM reservation " +
				      "WHERE id = ? AND r_status = ? ";
				pstmt = con.prepareStatement(sql);
				
				String r_status = "���� ��";
				
				pstmt.setString(1, userId); // 1 : ù��° ����ǥ
				pstmt.setString(2, r_status); // 2 : ù��° ����ǥ
				// executeQuery : select ���๮
				rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					flagForTotalCost = true;  // ���� �ִٸ� true ��ȯ
					totalCost = rs.getInt(1); // 1�� ��(SUM(p_cost)) �����ͼ� ��ȯ
				}
					

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return totalCost;
		}

		
		
}
