/* ����� ���������� ��������ȸ | ������ ������¥: 2022-03-25 | ������ ������: �輭�� */

package staywithus;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReservationUser {

	LoginMgr mgr = new LoginMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb, pwdLb; 
	private JButton updateBtn, listBtn, cancelBtn, homeBtn, delUserBtn;
	private JPasswordField pwdTf;
	private DefaultTableModel model;
	@SuppressWarnings("rawtypes")
	private Vector title, result;
	private JTable table;
	private JScrollPane sp;
	
	//����
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationUser reservationUser = new ReservationUser();
					reservationUser.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ������ (�Ű�����)
	@SuppressWarnings({ "unchecked", "serial" })
	public ReservationUser(String userId) {
		// �⺻ ����
		jf.setSize(1200,800);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//����� ũ��
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//�������� ũ��
		Dimension fDim = jf.getSize();
		
		// �������� ���� �𼭸� ��ǥ
		// �߾���ǥ : (����� ũ�� - ������ ũ��) / 2
		int x = (int)((dim.getWidth()-fDim.getWidth())/2);
		int y = (int)((dim.getHeight()- fDim.getHeight())/2);
		
		// ������ ��ġ ��Ű��
		jf.setLocation(x, y);
		
		// font ����
		Font f1 = new Font("���� ���", Font.BOLD, 40); //Ÿ��Ʋ ��Ʈ
		Font f2 = new Font("���� ���", Font.BOLD, 12); //��ư ��Ʈ	
		
		jf.setTitle("[ȸ��������ȸ]: "+userId);
		p1 = new JPanel();
		
		model = new DefaultTableModel() {
			// ���̺� �� ���� �Ұ� (����Ʈ: ��������)
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		//�ĳ� ����
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);

		// ���൥���� ���̺�
		result = mgr.resInfo(userId);
		title = new Vector<>();
		title.add("id");
		title.add("����");
		title.add("üũ��");
		title.add("üũ�ƿ�");
		title.add("�����ο�");
		title.add("���� ����");
		title.add("���� �ݾ�");
		title.add("�����ȣ");
		model.setDataVector(result, title);
		
		sp.setBounds(200, 200, 800, 400);
		p1.add(sp);
		
		//����������ư --> �������������� �̵�
		updateBtn = new JButton("����������");
		updateBtn.setBounds(20, 200, 100, 30);
		updateBtn.setFont(f2);
		updateBtn.setForeground(Color.white);
		updateBtn.setBackground(Color.black);
		p1.add(updateBtn);

		//������ȸ��ư --> ������������ư(��Ȱ��ȭ)
		listBtn = new JButton("��������ȸ");
		listBtn.setBounds(20, 250, 100, 30);
		listBtn.setFont(f2);
		listBtn.setForeground(Color.black);
		listBtn.setBackground(Color.white);
		listBtn.setEnabled(false);
		p1.add(listBtn);
		
		//ȸ��Ż���ư
		delUserBtn = new JButton ("ȸ��Ż��");
		p1.add(delUserBtn);
		delUserBtn.setBounds(20, 300, 100, 30);
		delUserBtn.setFont(f2);
		delUserBtn.setForeground(Color.white);
		delUserBtn.setBackground(Color.lightGray);
		
		//Ȩ��ư(��������)
		homeBtn = new JButton("Ȩ����");
		homeBtn.setBounds(20, 660, 100, 30);
		homeBtn.setFont(f2);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(Color.gray);
		p1.add(homeBtn);

		//Ÿ��Ʋ��
		titleLb = new JLabel("�� ���� ��ȸ");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);

		//"�������" ��ư
		cancelBtn = new JButton("�������");
		cancelBtn.setBounds(450, 650, 240, 40);
		cancelBtn.setFont(f2);
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setBackground(Color.BLACK);
		p1.add(cancelBtn);
		
		//��й�ȣ ��
		pwdLb = new JLabel("��й�ȣ �Է�");
		p1.add(pwdLb);
		pwdLb.setFont(f2);
		pwdLb.setBounds(450, 600, 100, 40);
		//��й�ȣ �ؽ�Ʈ�ʵ�
		pwdTf = new JPasswordField();
		p1.add(pwdTf);
		pwdTf.setEchoChar('��');
		pwdTf.setBounds(540, 614, 140, 20);
		
		
		
		/*��� ����*/
		
		// id������ �� ���������ҷ�����
		
		// ���� ��� LoginMgr�� ����
		cancelBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(mgr.loginChk(userId, pwdTf.getText())!=null/*true*/) {
					//���̵� ��й�ȣ ��ġ
					int row = table.getSelectedRow();
					String res_no = (String)table.getValueAt(row, 7);
					mgr.ur_del(res_no);
					//���� �� ��� �ҷ�����
					@SuppressWarnings("rawtypes")
					Vector result = mgr.resInfo(userId);
					model.setDataVector(result, title);
				} else { // ��й�ȣ ��ġ �� ������
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
			}
		});
		
				
		//����������ư �׼� (�������������� �̵�)
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateUser(userId);
				jf.dispose();
			}
		});
		
		// ȸ��Ż���ư �׼�: ȸ��Ż��â���� �̵�
		delUserBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteUser(userId);
				jf.dispose();
			}
		});		
		
		//Ȩ��ư �׼� (�������� �̵�)
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage(userId);
				jf.dispose();
			}
		});
}

	// ������
	public ReservationUser() {
		this(null);
	}
}
		
	
