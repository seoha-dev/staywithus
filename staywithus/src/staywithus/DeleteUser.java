/* ����� ���������� ȸ��Ż�� | ������ ������¥: 2022-03-25 | ������ ������: �輭�� */

package staywithus;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class DeleteUser {

	LoginMgr mgr = new LoginMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb, pwdLb, noticeLb; 
	private JButton updateBtn, listBtn, delBtn, homeBtn, delUserBtn;
	private JPasswordField pwdTf;
	private JCheckBox notedCb;
	
	//����
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser updateUser = new DeleteUser();
					updateUser.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ������ (�Ű�����)
	public DeleteUser(String userId) {
		
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
		Font f3 = new Font("���� ���", Font.BOLD, 12); //�� ��Ʈ
		Font f4 = new Font("���� ���", Font.PLAIN, 11); 
		

		jf.setTitle(userId+"  ȸ��Ż��");
		p1 = new JPanel();
		
		//�ĳڲٹ̱�
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);

		//����������ư
		updateBtn = new JButton("����������");
		updateBtn.setBounds(20, 200, 100, 30);
		updateBtn.setFont(f2);
		updateBtn.setForeground(Color.white);
		updateBtn.setBackground(Color.black);
		p1.add(updateBtn);
		
		//������ȸ��ư
		listBtn = new JButton("��������ȸ");
		listBtn.setBounds(20, 250, 100, 30);
		listBtn.setFont(f2);
		listBtn.setForeground(Color.white);
		listBtn.setBackground(Color.black);
		p1.add(listBtn);
		
		//ȸ��Ż���ư(���缱�õ�)
		delUserBtn = new JButton ("ȸ��Ż��");
		p1.add(delUserBtn);
		delUserBtn.setBounds(20, 300, 100, 30);
		delUserBtn.setFont(f2);
		delUserBtn.setForeground(Color.black);
		delUserBtn.setBackground(Color.white);
		delUserBtn.setEnabled(false);
		
		//Ȩ��ư(��������)
		homeBtn = new JButton("Ȩ����");
		homeBtn.setBounds(20, 660, 100, 30);
		homeBtn.setFont(f2);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(Color.gray);
		p1.add(homeBtn);
		
		//Ÿ��Ʋ��
		titleLb = new JLabel("ȸ�� Ż��");
		titleLb.setFont(f1);
		titleLb.setBounds(470, 70, 300, 100);
		p1.add(titleLb);
		
		//"ȸ��Ż�� ����" ��
		noticeLb = new JLabel("ȸ��Ż��� ������ ��� ������ ���� �� ���������� �����˴ϴ�.");
		noticeLb.setFont(f3);
		noticeLb.setBounds(390, 300, 500, 20);
		p1.add(noticeLb);
		//"ȸ��Ż�� ����" üũ�ڽ�
		notedCb = new JCheckBox("������ ��⳻���� �����Ͽ����ϴ�.");
		p1.add(notedCb);
		notedCb.setFont(f4);
		notedCb.setBackground(Color.white);
		notedCb.setBounds(450, 330, 500, 20);
		
		//"��й�ȣ" ��
		pwdLb = new JLabel("��й�ȣ");
		pwdLb.setFont(f3);
		pwdLb.setBounds(450, 370, 70, 20);
		p1.add(pwdLb);
		//"��й�ȣ" �ؽ�Ʈ�ʵ�
		pwdTf = new JPasswordField();
		pwdTf.setColumns(10);
		pwdTf.setBounds(450, 390, 240, 20);
		pwdTf.setEchoChar('��');
		p1.add(pwdTf);				
		
		//"Ż��" ��ư
		delBtn = new JButton("ȸ��Ż��");
		delBtn.setBounds(450, 650, 240, 40);
		delBtn.setFont(f2);
		delBtn.setForeground(Color.WHITE);
		delBtn.setBackground(Color.BLACK);
		p1.add(delBtn);
				
		/*��� ����*/
			
		//ȸ��Ż��
		delBtn.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				// ��й�ȣ, �̸���, ����ó �� ĭ���� �� ���
				if (!notedCb.isSelected()) { // üũ����
					JOptionPane.showMessageDialog(null, "[ȸ��Ż��] �ȳ��� �а� ������ �����ߴٴ� üũ�� ���ּ���.");
				} else if (mgr.loginChk(userId, pwdTf.getText()) != null/* true */) { // ��й�ȣ ����� ��
					mgr.delUser(userId);
					JOptionPane.showMessageDialog(null, "[ȸ��Ż��] " + userId + " ���� Ż�� �Ϸ�Ǿ����ϴ�.");
					new MainPage();
					jf.dispose();
				} else if (mgr.loginChk(userId, pwdTf.getText()) == null) { //��й�ȣ ����� �Ⱦ�
					JOptionPane.showMessageDialog(null, "[ȸ��Ż��] ��й�ȣ�� ��Ȯ���� �ʽ��ϴ�.");
					
				} else {	
					JOptionPane.showMessageDialog(null,"[ȸ��Ż��] "+ userId + "������ �߻��Ͽ����ϴ�.");
					System.out.println("[DeleteUser]" + userId + "�� ȸ��Ż�� �� ������ �߻��Ͽ����ϴ�.");
				}
			}

		});
		
		// ������ȸ��ư �׼�: �� ���೻�� ��ȸ
		listBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReservationUser(userId);
				jf.dispose();
			}
		});
		
		//Ȩ��ư�׼�: ��������
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage(userId);
				jf.dispose();
			}
		});
		
		// ������������ư �׼� : ���������� �̵� 
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateUser(userId);
				jf.dispose();
			}
		});
}

	// ������
	public DeleteUser() {
		this(null);
	}

}
		
	
