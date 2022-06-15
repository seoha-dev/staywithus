/*-- �α��θŴ��� | ������ ������¥: 2022-03-25 | ������ ������: �輭��--*/
package staywithus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JOptionPane;


// DB������ ����� Ŭ���� 
public class LoginMgr {
	
	private String logSucId;
	private int mode;
	
	
	// �Ź� ���� �������� ������ �Ȱɸ��� pool�� ���� �ν��Ͻ��� ���
	private DBConnectionMgr pool;
	
	public LoginMgr() {
		pool = DBConnectionMgr.getInstance();
	}

	
	// SELECT : ID,��� �Ű������� �޾Ƽ� DB�� ��ġ�ϴ��� ���� �� �α���
	public String loginChk(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		boolean flag = false; // flag�� �Ҹ��𰪿� ���� ��� ����
		
		try {
			con = pool.getConnection();
			
			//������
			sql = "SELECT id FROM user WHERE id = ? AND pwd = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id); // 1 : ù��° ����ǥ
			pstmt.setString(2, pwd); // 2 : ù��° ����ǥ
			// executeQuery : select ���๮
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = true;  // ���� �ִٸ� true ��ȯ
				logSucId = rs.getString(1); // �������� ù��° �� (id)�� logSucId�� ������ ����
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return logSucId;
	}
	
	// SELECT : ID �޾ƿͼ� ������ ���� Ȯ��
	public int modeChk(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			
			//������
			sql = "SELECT mode FROM user WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id); // 1 : ù��° ����ǥ
			// executeQuery : select ���๮
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mode = rs.getInt(1); // �������� ù��° �� (��尪)�� mode�� ������ ����
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return mode;
	}	
	
	// SELECT : ȸ�����Խ� ���̵� �ߺ�Ȯ�� 
	public boolean idChk(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		boolean flagForIdDup = false; // flagForIdDup�� �Ҹ��𰪿� ���� ��� ����
		
		try {
			con = pool.getConnection();
			//������
			sql = "select id from user where id = ? ";
			// �Ʒ��������� ������ ���̱⵵ ����
//			sql += "SELECT id ";
//			sql += "FROM   user ";
//			sql += "WHERE id = ? "; //1
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // 1 : ù��° ����ǥ
			
			// executeQuery : select ���๮
			rs = pstmt.executeQuery(); 

			if(rs.next())
				flagForIdDup = true; // ���� �ִٸ� true ��ȯ
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flagForIdDup; 
	} 
	


	// INSERT : ȸ�����ԿϷ� -> DB�� �Է�
	public boolean userSign(UserBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flagForUserSign = false; 
		
		try {
			con = pool.getConnection();
			//������
			sql = "INSERT user (id, pwd, name, email, phone, birthday, gender, mode)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";//values (�����Ͱ���)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());//1�� ù��° ?�� �ǹ�
			pstmt.setString(2, bean.getPwd());//2�� �ι�° ?�� �ǹ�
			pstmt.setString(3, bean.getName());//3�� ����° ?�� �ǹ�
			pstmt.setString(4, bean.getEmail());//4�� �׹�° ?�� �ǹ�
			pstmt.setString(5, bean.getPhone());
			pstmt.setString(6, bean.getBirthday());
			pstmt.setString(7, bean.getGender());
			pstmt.setInt(8, bean.getMode());
			
			// executeUpdate : insert, update, delete ���๮
			int cnt = pstmt.executeUpdate(); 
			// ����� ���ڵ� ���� : ���� �� ó�� : 0, �������� ó�� : 1 (1�྿ �����Ŵϱ�) 
			if(cnt==1) 
				flagForUserSign = true; // ���� �ִٸ� true ��ȯ
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForUserSign;
	} //---ȸ������
	
	
	// SELECT: UpdateUser_ȸ��������ȸ
	public UserBean userInfo(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		UserBean bean = new UserBean();

		try {
			con = pool.getConnection();
			sql = "select name, email, phone, birthday, gender from user where id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setPhone(rs.getString("phone"));
				bean.setBirthday(rs.getString("birthday"));
				bean.setGender(rs.getString("gender"));
//				System.out.println("[loginMgr_userInfo]: userid:(" + id + ")������ bean�� ��ҽ��ϴ�.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);			
		}
		return bean;
	}//--ȸ��������ȸ
	

	// UPDATE: UpdateUser_������ ���� (ȸ��)
	public boolean userUpdt(UserBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flagForUserUpdt = false;
		try {
			con = pool.getConnection();
			sql = "UPDATE user SET pwd =?, email =?, phone =? "
					+ "WHERE id = ?";//values (�����Ͱ���)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getPwd());//1�� ù��° ?�� �ǹ�
			pstmt.setString(2, bean.getEmail());//2�� �ι�° ?�� �ǹ�
			pstmt.setString(3, bean.getPhone());//3�� ����° ?�� �ǹ�
			pstmt.setString(4, bean.getId());//4�� �׹�° ?�� �ǹ�
			//����� ���ڵ� ���� : ���� �� ó�� : 0, �������� ó�� : 1 
			int cnt = pstmt.executeUpdate();
			if(cnt==1) flagForUserUpdt = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForUserUpdt;
	} //--����������	
	
	
	// SELECT: ReservationUser_ȸ��������ȸ
	public Vector resInfo(String id) { 

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
	}//-- ȸ��������ȸ
	
	
	// DELETE: ReservationUser_ȸ���������
	public void ur_del(String res_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
//		Vector data = new Vector<>();
//		data.clear();

		try {
			con = pool.getConnection();

			// ������
			sql = "delete from reservation where res_no = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, res_no);
			int cnt = pstmt.executeUpdate();

			if (cnt == 1)
				JOptionPane.showMessageDialog(null, "[���� ���] �����ȣ: " + res_no + " ������ ��ҵǾ����ϴ�.");
			else
				JOptionPane.showMessageDialog(null, "[���� ��� error] �����ȣ: " + res_no + " ��� �� ������ �߻��Ͽ����ϴ�.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}//-- ȸ���������

	// DELETE: DeleteUser_ȸ��Ż��
	@SuppressWarnings("resource")
	public void delUser(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = pool.getConnection();

			// ������
			sql = "delete from reservation where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			int cnt_r = pstmt.executeUpdate();
			
			// ������2
			sql = "delete from user where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			int cnt_u = pstmt.executeUpdate();
					

			if (cnt_u == 1 && cnt_r > 0) {
				System.out.println("[LoginMgr] " + id + " ȸ���� ���� �� ������ �����Ǿ����ϴ�.");
			} else if (cnt_u ==1 && cnt_r == 0) {
				System.out.println("[LoginMgr] " + id + " ȸ���� ������ �����Ǿ����ϴ�. (������ ���� ����)");
			} else if (cnt_u == 0 && cnt_r > 0) {
				System.out.println("[LoginMgr] " + id + " ȸ���� ��� ������ �����Ǿ����ϴ�.");
			} else {
				System.out.println("[LoginMgr] " + id + " ȸ���� ����� ���� ������ ������ �߻��Ͽ����ϴ�.");
				System.out.println("[LoginMgr] ������ ���హ��: "+cnt_r+"  ������ ��������: "+ cnt_u);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}//-- ȸ��Ż��
	
}
