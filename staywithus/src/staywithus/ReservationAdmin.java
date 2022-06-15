/*-- ������������ ������� | ������ ������¥: 2022-03-25 | ������ ������: �輭��--*/

package staywithus;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReservationAdmin {
	AdminMgr mgr = new AdminMgr();
	JFrame jf = new JFrame();

	private JPanel p1;
	private JLabel titleLb, res_noLb, idLb, r_roomLb, sdLb, edLb, hcLb, r_statLb, p_costLb, searchLb;
	private JButton uptBtn, delBtn, homeBtn, searchBtn, freshBtn;
	private JTextField idTf, r_roomTf, sdTf, edTf, hcTf, r_statTf, p_costTf, res_noTf, searchTf;
	private DefaultTableModel model;
	@SuppressWarnings("rawtypes")
	private Vector title, result;
	private JTable table;
	private JScrollPane sp;
	private java.util.Date utilStartDate;
	private java.util.Date utilEndDate;
	private int p_cost;

	// ����
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationAdmin reservationAdmin = new ReservationAdmin();
					reservationAdmin.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ������ (�Ű�����)
	@SuppressWarnings({ "serial", "unchecked" })
	public ReservationAdmin(String userId) {
		// �⺻ ����
		jf.setSize(1200, 800);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// ����� ũ��
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		// �������� ũ��
		Dimension fDim = jf.getSize();

		// �������� ���� �𼭸� ��ǥ
		// �߾���ǥ : (����� ũ�� - ������ ũ��) / 2
		int x = (int) ((dim.getWidth() - fDim.getWidth()) / 2);
		int y = (int) ((dim.getHeight() - fDim.getHeight()) / 2);

		// ������ ��ġ ��Ű��
		jf.setLocation(x, y);

		// font ����
		Font f1 = new Font("���� ���", Font.BOLD, 40); // Ÿ��Ʋ ��Ʈ
		Font f2 = new Font("���� ���", Font.BOLD, 12); // ��ư ��Ʈ
		Font f3 = new Font("���� ���", Font.BOLD, 12); // �� ��Ʈ

		jf.setTitle("������������(" + userId + ")");
		p1 = new JPanel();

		model = new DefaultTableModel() {
			// ���̺� �� ���� �Ұ� (����Ʈ: ��������)
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		sp = new JScrollPane(table);

		// �ĳ� ����
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);

		// ���൥���� ���̺�
		result = mgr.selectAll();
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

		// ���̺� ��ġ
		sp.setBounds(450, 200, 550, 400);
		p1.add(sp);

		// Ȩ��ư
		homeBtn = new JButton("Ȩ����");
		homeBtn.setBounds(20, 660, 100, 30);
		homeBtn.setFont(f2);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(Color.gray);
		p1.add(homeBtn);

		// Ÿ��Ʋ��
		titleLb = new JLabel("��ü ���� ��ȸ");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);

		// ���̵� ��
		idLb = new JLabel("���̵�");
		p1.add(idLb);
		idLb.setFont(f3);
		idLb.setBounds(150, 200, 40, 20);

		// ���̵� �ؽ�Ʈ�ʵ�
		idTf = new JTextField();
		p1.add(idTf);
		idTf.setBounds(220, 200, 150, 20);
		idTf.setEditable(false);

		// ���� ��
		r_roomLb = new JLabel("����");
		p1.add(r_roomLb);
		r_roomLb.setFont(f3);
		r_roomLb.setBounds(150, 230, 40, 20);

		// ���� �ؽ�Ʈ�ʵ�
		r_roomTf = new JTextField();
		p1.add(r_roomTf);
		r_roomTf.setBounds(220, 230, 150, 20);

		// üũ�� ��
		sdLb = new JLabel("üũ��");
		p1.add(sdLb);
		sdLb.setFont(f3);
		sdLb.setBounds(150, 260, 40, 20);
		// üũ�� �ؽ�Ʈ�ʵ�
		sdTf = new JTextField();
		p1.add(sdTf);
		sdTf.setBounds(220, 260, 150, 20);

		// üũ�ƿ� ��
		edLb = new JLabel("üũ�ƿ�");
		p1.add(edLb);
		edLb.setFont(f3);
		edLb.setBounds(150, 290, 50, 20);
		// üũ�ƿ� �ؽ�Ʈ�ʵ�
		edTf = new JTextField();
		p1.add(edTf);
		edTf.setBounds(220, 290, 150, 20);

		// �����ο� ��
		hcLb = new JLabel("�����ο�");
		p1.add(hcLb);
		hcLb.setFont(f3);
		hcLb.setBounds(150, 320, 50, 20);
		// �����ο� �ؽ�Ʈ�ʵ�
		hcTf = new JTextField();
		p1.add(hcTf);
		hcTf.setBounds(220, 320, 150, 20);

		// ���� ���� ��
		r_statLb = new JLabel("��������");
		p1.add(r_statLb);
		r_statLb.setFont(f3);
		r_statLb.setBounds(150, 350, 50, 20);
		// �������� �ؽ�Ʈ�ʵ�
		r_statTf = new JTextField();
		p1.add(r_statTf);
		r_statTf.setBounds(220, 350, 150, 20);
		r_statTf.setEditable(false);

		// �����ݾ� ��
		p_costLb = new JLabel("�����ݾ�");
		p1.add(p_costLb);
		p_costLb.setFont(f3);
		p_costLb.setBounds(150, 380, 50, 20);

		// �����ݾ� �ؽ�Ʈ�ʵ�
		p_costTf = new JTextField();
		p1.add(p_costTf);
		p_costTf.setBounds(220, 380, 150, 20);
		p_costTf.setEditable(false);

		// �����ȣ ��
		res_noLb = new JLabel("�����ȣ");
		p1.add(res_noLb);
		res_noLb.setFont(f3);
		res_noLb.setBounds(150, 410, 50, 20);
		// �����ȣ �ؽ�Ʈ�ʵ�
		res_noTf = new JTextField();
		p1.add(res_noTf);
		res_noTf.setBounds(220, 410, 150, 20);
		res_noTf.setEditable(false);

		// ���� �߰� ��ư
//		addBtn = new JButton("���� �߰�");
//		addBtn.setBounds(200, 620, 100, 30);
//		addBtn.setFont(f2);
//		addBtn.setForeground(Color.black);
//		addBtn.setBackground(Color.white);
//		p1.add(addBtn);

		// ���� ���� ��ư
		uptBtn = new JButton("���� ����");
		uptBtn.setBounds(150, 500, 220, 30);
		uptBtn.setFont(f2);
		uptBtn.setForeground(Color.black);
		uptBtn.setBackground(Color.white);
		p1.add(uptBtn);

		// ���� ���� ��ư
		delBtn = new JButton("���� ����");
		delBtn.setBounds(150, 550, 220, 30);
		delBtn.setFont(f2);
		delBtn.setForeground(Color.black);
		delBtn.setBackground(Color.white);
		p1.add(delBtn);

		// �˻� ��
		searchLb = new JLabel("������ ID�� �Է��ϼ���");
		p1.add(searchLb);
		searchLb.setFont(f3);
		searchLb.setBounds(450, 603, 250, 20);

		// �˻� �ؽ�Ʈ�ʵ�
		searchTf = new JTextField();
		p1.add(searchTf);
		searchTf.setBounds(585, 605, 150, 20);

		// �˻� ��ư
		searchBtn = new JButton("�˻�");
		p1.add(searchBtn);
		searchBtn.setFont(f2);
		searchBtn.setBounds(740, 605, 60, 20);
		searchBtn.setForeground(Color.white);
		searchBtn.setBackground(Color.black);

		// ���ΰ�ħ ��ư
		freshBtn = new JButton("���ΰ�ħ");
		p1.add(freshBtn);
		freshBtn.setFont(f2);
		freshBtn.setBounds(915, 605, 85, 20);
		freshBtn.setForeground(Color.black);
		freshBtn.setBackground(Color.white);

		/* ��� ���� */

		// ���̺� Ŭ���� ����cell�� -> �ؽ�Ʈ�ʵ�� �������
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();

				String id = (String) table.getValueAt(row, 0);
				String r_room = (String) table.getValueAt(row, 1);
				String sd = (String) table.getValueAt(row, 2);
				String ed = (String) table.getValueAt(row, 3);
				String hc = (String) table.getValueAt(row, 4);
				String r_stat = (String) table.getValueAt(row, 5);
				String p_cost = (String) table.getValueAt(row, 6);
				String res_no = (String) table.getValueAt(row, 7);

				idTf.setText(id);
				r_roomTf.setText(r_room);
				sdTf.setText(sd);
				edTf.setText(ed);
				hcTf.setText(hc);
				r_statTf.setText(r_stat);
				p_costTf.setText(p_cost);
				res_noTf.setText(res_no);
				System.out.println("[ReservationAdmin]���õ� �����ȣ:" + res_no);

			}
		});

		// �������: ������ ����������ȸ -> ������������� ����
		uptBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �������ϴ� �� ��������
				String r_room =r_roomTf.getText(); // ���� ��
				String headcount = hcTf.getText(); // �����ο�
				String res_no = res_noTf.getText(); // �����ȣ
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // Date�������� �ٲٴ� Ŭ����
				// String -> java.util.date
				try {
					utilStartDate = sdf.parse(sdTf.getText().trim().replaceAll("[^0-9]", ""));
					utilEndDate = sdf.parse(edTf.getText().trim().replaceAll("[^0-9]", ""));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				// java.util.date -> java.sql.date ��ȯ
				java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
				java.sql.Date sqlEndDate = new java.sql.Date(utilEndDate.getTime());
				
				ReservationBean bean = new ReservationBean();
				// �������� �����Ϻ��� ������ ��������ϴ� ���
				if(Integer.parseInt(sdTf.getText().trim().replaceAll("[^0-9]", "")) >
				Integer.parseInt(edTf.getText().trim().replaceAll("[^0-9]", ""))) {
					JOptionPane.showMessageDialog(null, "�������� �����Ϻ��� ���� �� �����ϴ�.");
					
				} else {
					if (mgr.resChk(r_room, sqlStartDate, sqlEndDate, Integer.parseInt(res_no))) { // �ߺ� ���� �����ϴ� ���
						System.out.println("�� ��ȣ : " + r_room + " ���۳�¥ : " + sqlStartDate + ", ���ᳯ¥ : " + sqlEndDate);
						System.out.println("[ReservationAdmin] ������������");
						JOptionPane.showMessageDialog(null, "�ش� ������ ������ �Ұ����մϴ�.");
					} else { // �ߺ� ���� �������� �ʴ� ���
						System.out.println("[ReservationAdmin] �����������");
						System.out.println("�� ��ȣ : " + r_room + " ���۳�¥ : " + sqlStartDate + ", ���ᳯ¥ : " + sqlEndDate);
						
						ReservationMgr resMgr = new ReservationMgr(); // ������� �ִ�����ο��� 1�ڴ� ���� ��������
						int capacity = resMgr.capacityChk(Integer.parseInt(r_room));
						if (Integer.parseInt(headcount) > capacity) { // �ִ�����ο� �ʰ��� ���
							JOptionPane.showMessageDialog(null, r_room + "���� �ִ� �����ο��� " + capacity + "�� �Դϴ�.");
							
						} else { // �ʰ����� �ʴ� ���
							
							bean.setR_room(r_room);
							bean.setStartdate(sqlStartDate);
							bean.setEnddate(sqlEndDate);
							bean.setHeadcount(headcount);
							bean.setRes_no(res_no);
							
							p_cost = resMgr.totalCostChk(Integer.parseInt(r_room));
							System.out.println(p_cost);

							// 1�� ���ݿ� ���ϴ� �����ϼ� ���� ���ϱ�
							Calendar calStartDate = Calendar.getInstance();
							calStartDate.setTime(sqlStartDate);

							Calendar calEndDate = Calendar.getInstance();
							calEndDate.setTime(sqlEndDate); 
							
							long diffSec = (calEndDate.getTimeInMillis() - calStartDate.getTimeInMillis()) / 1000;
							int diffDays = (int)diffSec / (24*60*60); //���ڼ� ����
							
							// �����ϼ� * 1�ڴ� ���� ���ؼ� bean�� set
							bean.setP_cost(diffDays*p_cost);
							System.out.println(diffDays*p_cost);
							
							
							mgr.resUpdt(bean);
							System.out.println("[ReservationAdmin] �����ȣ:" + res_no + " ��������Ϸ�");
							JOptionPane.showMessageDialog(null, "��������� ���������� ó���Ǿ����ϴ�.");
							// ������ DB�� ���κҷ�����
							@SuppressWarnings("rawtypes")
							Vector result = mgr.selectAll();
							model.setDataVector(result, title);
						}
						

					}
				}
				

			}

		});

		// �������: ������ ���ʷ� ����� �� -> AdminMgr�� �Ἥ ����
		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String res_no = res_noTf.getText();
				mgr.delete(res_no);

				// ���� �� ��� �ҷ�����
				@SuppressWarnings("rawtypes")
				Vector result = mgr.selectAll();
				model.setDataVector(result, title);

			}
		});

		// �˻���ư: id�� ���೻���� ���
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = searchTf.getText();
				System.out.println(searchTf.getText());
				mgr.selectId(id);
				result = mgr.selectId(id);
				model.setDataVector(result, title);
			}
		});

		// ���ΰ�ħ ��ư: �ٽ� ��ü ���
		freshBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Vector result = mgr.selectAll();
				model.setDataVector(result, title);
			}
		});

		// Ȩ��ư Ŭ�� �׼�: �������� �̵�
		homeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage(userId);
				jf.dispose();

			}
		});

		// ���ΰ�ħ
		jf.validate();
	}

	// ������
	public ReservationAdmin() {
		this(null);
	}
}
