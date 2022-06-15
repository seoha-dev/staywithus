package staywithus;

import java.util.Calendar;

import javax.swing.JButton;

public class CalendarFunc {
	// Calendar Ŭ�����κ��� �ν��Ͻ� ��������
	Calendar cal = Calendar.getInstance();
	
	int year, month;
	
	// ������
	public CalendarFunc() {
		year = cal.get(Calendar.YEAR); // ���� �⵵ ��������
		// Ķ���� Ŭ���� 1���� 0���� �����ϹǷ� +1 ����� ���� �� ���ڸ� ����Ŵ
		month = cal.get(Calendar.MONTH) + 1; // 2(Ķ���� Ŭ�������� 3�� ����) + 1 -> int month = 3
	}
	
	// �޷¹�ư �迭
	JButton dayBtns[];
	
	// setBtn �޼ҵ� : �ش� ���� ����, ���� ����
	public void setBtn(JButton[] btns) {
		this.dayBtns = btns;
	}
	// setCalText �޼ҵ� : ������ "0000�� 00��" ���� 
	public String setCalText() {
		return year + "��" + String.format("%02d", month) + "��";
	}

	// setCal �޼ҵ� : �޷� ��ư�� ��¥ ����
	public void setCal() {
		// Cal.set(YEAR �� , MONTH �� , DAY ��)
		/* -1 �ϴ� ���� : MONTH �� 0~11������ ���� ������,
		 * int month = 3�� Ķ���� Ŭ�������� 4���� ��Ÿ���Ƿ� 
		 * -1 ����� ������ �޷� ���� ����*/
		cal.set(year, Integer.parseInt(String.format("%02d", month-1)), 1); // 1�� ����
		 
		// DAY_OF_WEEK : �����Ͽ� �ش�Ǵ� ������ �ǹ��ϸ�, �Ͽ���(1)���� ����
		int firstDay = cal.get(Calendar.DAY_OF_WEEK); 
		
		// DAY_OF_WEEK : 1~7 ���� �����ϹǷ� ���� �迭(sDayName,eDayName 0~6)�� �����ַ��� -1 ó��
		firstDay--;
		
		for(int i = 1; i <= cal.getActualMaximum(cal.DATE)/*�� ���� ������ ��(28/30/31)*/; i++) {
			// dayBtns[0] ~ [6] : ����, dayBtns[7] ~ ��: ���� ����
			dayBtns[6 + firstDay + i].setText(String.valueOf(i));
		}			
	}
	
	// startCal �޼ҵ� : 
	public void startCal(int move) {
		// dayBtns[0] ~ [6] : ����
		for(int i =7; i < dayBtns.length; i++) {
			// ������ ���ΰ� ���� ��ư ��� �ʱ�ȭ
			dayBtns[i].setText("");
		}
		
		month += move; // move�� Ƚ���� ���� ���� month�� +- 
		
		if(month <= 0) {
			year--;
			month = 12;
		} else if(month >= 13) {
			year++;
			month = 1;
		}
		setCal();
	}		
}