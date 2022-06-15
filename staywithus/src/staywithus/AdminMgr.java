/* ������������ ������� �Ŵ��� | ������ ������¥: 2022-03-27 | ������ ������: �輭�� */
package staywithus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JOptionPane;

// DB������ ����� Ŭ���� 
public class AdminMgr {
	// �Ź� ���� �������� ������ �Ȱɸ��� pool�� ���� �ν��Ͻ��� ���
	private DBConnectionMgr pool;

	public AdminMgr() {
		pool = DBConnectionMgr.getInstance();
	}

	
	//DB ��ü���೻�� ��ȸ
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

			// ������
			sql = "select * from reservation";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Vector in = new Vector<String>(); // 1���� ���ڵ� �����ϴ� ���� ����

				String id = rs.getString(1);
				String r_room = rs.getString(2);
				String startdate = rs.getString(3);
				String enddate = rs.getString(4);
				String headcount = rs.getString(5);
				String r_status = rs.getString(6);
				String p_cost = rs.getString(7);
				String res_no = rs.getString(8);


				// ���Ϳ� ������ �� �߰�

				in.add(id);
				in.add(r_room);
				in.add(startdate);
				in.add(enddate);
				in.add(headcount);
				in.add(r_status);
				in.add(p_cost);
				in.add(res_no);
						
				// ��ü �����͸� �����ϴ� ���Ϳ� in(1���� ������ ����) ���� �߰�
				data.add(in);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return data;
	}


	//�������: ReservationAdmin �ؽ�Ʈ�ʵ忡�� �����ȣ�� ����ͼ� ���� ����
	public void delete(String res_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		@SuppressWarnings("rawtypes")
		Vector data = new Vector<>();

		data.clear();
		
		try {
			con = pool.getConnection();

			// ������
			sql = "delete from reservation where res_no = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, res_no);
			//pstmt.executeUpdate();
			
			int cnt = pstmt.executeUpdate();
			if(cnt==1) 
				JOptionPane.showMessageDialog(null, "������� �Ϸ�.");
			else 
				JOptionPane.showMessageDialog(null, "������� ����."); 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//���� ���� üũ
	public boolean resChk(String r_room, java.sql.Date startDate, java.sql.Date endDate, int res_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		boolean flagForResChk = false;

		try {
			con = pool.getConnection();
			// ������
			sql = "SELECT * " 
					+ "FROM reservation " 
					+ "WHERE (r_room = ?) AND (((?<=enddate) AND (?>=startdate)) "
					+ "OR((?<=enddate) AND (?>=startdate))) AND res_no != ?;";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, r_room); // 1�� ù��° ?�� �ǹ�
			pstmt.setDate(2, startDate); // 2�� �ι�° ?�� �ǹ�
			pstmt.setDate(3, startDate); // 3�� ����° ?�� �ǹ�
			pstmt.setDate(4, endDate); // 4�� �׹�° ?�� �ǹ�
			pstmt.setDate(5, endDate); // 5�� �ټ���° ?�� �ǹ�
			pstmt.setInt(6, res_no); // 6�� ������° ?�� �ǹ�
			
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
	
	// �� �������� ������Ʈ
	public boolean resUpdt(ReservationBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flagForResUpdt = false;
		try {
			con = pool.getConnection();
			sql = "UPDATE reservation SET r_room =?, startdate =?, enddate =?, headcount =?, p_cost =? "
					+ "WHERE res_no = ?";//values (�����Ͱ���)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getR_room());//1�� ù��° ?�� �ǹ�
			pstmt.setDate(2, bean.getStartdate());//2�� �ι�° ?�� �ǹ�
			pstmt.setDate(3, bean.getEnddate());//3�� ����° ?�� �ǹ�
			pstmt.setString(4, bean.getHeadcount());//4�� �׹�° ?�� �ǹ�
			pstmt.setInt(5, bean.getP_cost());
			pstmt.setString(6, bean.getRes_no());
			
			//����� ���ڵ� ���� : ���� �� ó�� : 0, �������� ó�� : 1 
			int cnt = pstmt.executeUpdate();
			if(cnt==1) flagForResUpdt = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForResUpdt;
	}

	// SELECT: ReservationAdmin ���õ� ���̵� ���೻���� ��ȸ
	public Vector selectId(String id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector data = new Vector<>();
		data.clear();

		try {
			con = pool.getConnection();
			// ������
			sql = "select * from reservation where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector in = new Vector<String>(); // 1���� ���ڵ� �����ϴ� ���� ����

//				String id = rs.getString(1);
				String r_room = rs.getString(2);
				String startdate = rs.getString(3);
				String enddate = rs.getString(4);
				String headcount = rs.getString(5);
				String r_status = rs.getString(6);
				String p_cost = rs.getString(7);
				String res_no = rs.getString(8);
				System.out.println("���Ϳ��ֱ�");
				// ���Ϳ� ������ �� �߰�

				in.add(id);
				in.add(r_room);
				in.add(startdate);
				in.add(enddate);
				in.add(headcount);
				in.add(r_status);
				in.add(p_cost);
				in.add(res_no);

				// ��ü �����͸� �����ϴ� ���Ϳ� in(1���� ������ ����) ���� �߰�
				data.add(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return data;
	}// -- Ư�� id ���೻���� ��ȸ

}

	

