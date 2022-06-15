package staywithus;

import java.util.Calendar;

import javax.swing.JButton;

public class CalendarFunc {
	// Calendar 클래스로부터 인스턴스 가져오기
	Calendar cal = Calendar.getInstance();
	
	int year, month;
	
	// 생성자
	public CalendarFunc() {
		year = cal.get(Calendar.YEAR); // 현재 년도 가져오기
		// 캘린더 클래스 1월은 0부터 시작하므로 +1 해줘야 현재 월 숫자를 가리킴
		month = cal.get(Calendar.MONTH) + 1; // 2(캘린더 클래스에서 3월 뜻함) + 1 -> int month = 3
	}
	
	// 달력버튼 배열
	JButton dayBtns[];
	
	// setBtn 메소드 : 해당 월의 요일, 일자 셋팅
	public void setBtn(JButton[] btns) {
		this.dayBtns = btns;
	}
	// setCalText 메소드 : 소제목에 "0000년 00월" 셋팅 
	public String setCalText() {
		return year + "년" + String.format("%02d", month) + "월";
	}

	// setCal 메소드 : 달력 버튼의 날짜 셋팅
	public void setCal() {
		// Cal.set(YEAR 값 , MONTH 값 , DAY 값)
		/* -1 하는 이유 : MONTH 는 0~11까지의 값을 가지며,
		 * int month = 3은 캘린더 클래스에서 4월을 나타내므로 
		 * -1 해줘야 현재의 달로 셋팅 가능*/
		cal.set(year, Integer.parseInt(String.format("%02d", month-1)), 1); // 1일 셋팅
		 
		// DAY_OF_WEEK : 일주일에 해당되는 요일을 의미하며, 일요일(1)부터 시작
		int firstDay = cal.get(Calendar.DAY_OF_WEEK); 
		
		// DAY_OF_WEEK : 1~7 값을 리턴하므로 요일 배열(sDayName,eDayName 0~6)과 맞춰주려면 -1 처리
		firstDay--;
		
		for(int i = 1; i <= cal.getActualMaximum(cal.DATE)/*이 달의 마지막 날(28/30/31)*/; i++) {
			// dayBtns[0] ~ [6] : 요일, dayBtns[7] ~ 끝: 일자 셋팅
			dayBtns[6 + firstDay + i].setText(String.valueOf(i));
		}			
	}
	
	// startCal 메소드 : 
	public void startCal(int move) {
		// dayBtns[0] ~ [6] : 요일
		for(int i =7; i < dayBtns.length; i++) {
			// 요일은 놔두고 일자 버튼 모두 초기화
			dayBtns[i].setText("");
		}
		
		month += move; // move한 횟수에 따라 기존 month에 +- 
		
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