package edu.kh.jdbc.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.PracticeService;

public class PracticeView {

	private PracticeService service = new PracticeService();
	private Scanner sc = new Scanner(System.in);
	
	
	public void mainMenu() {
		int input = 0;
		
		do {
			try {
				
				System.out.println("\n==== User 관리 프로그램 ====\n");
				System.out.println("1. User 등록(INSERT)");
				System.out.println("2. User 전체 조회(SELECT)");
				System.out.println("3. User 중 이름에 검색어가 포함된 회원 조회(SELECT)");
				System.out.println("4. USER_NO 번호를 입력 받아 일치하는 User 조회(SELECT)");
				System.out.println("5. USER_NO 번호를 입력 받아 일치하는 User 삭제(DELETE)");
				System.out.println("6. ID, PW가 일치하는 회원이 있을 경우 이름 수정(UPDATE)");
				System.out.println("7. User 등록(아이디 중복 검사)");
				System.out.println("8. 여러 User 등록하기");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 버퍼에 남은 개행문자 제거
				
				switch(input) {
				case 1 : insertUser(); 		break;
				case 2 : selectAll();			break;
				case 3 : 			break;
				case 4 : 	 		break;
				case 5 :  			break;
				case 6 : 			break;
				case 7 : 			break;
				case 8 : 		break;
				case 0 : System.out.println("\n[프로그램 종료]\n"); break;
				default : System.out.println("\n[메뉴 번호만 입력하세요]\n");
				}
				
				System.out.println("\n----------------------------------------\n");
				
				
			} catch(InputMismatchException e) {
				
				// Scanner를 이용한 입력 시 자료형이 잘못된 경우
				System.out.println("\n잘못 입력하셨습니다.\n");
				
				input = -1; // 잘못 입력해서 while문 멈추는걸 방지
				
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자 제거
				
			} catch(Exception e) {
					e.printStackTrace();
			}
			
		} while(input != 0);
	}
	
	// 1. User 등록(INSERT)
	public void insertUser() throws SQLException {
		
		System.out.println("\n=== 1. User 등록(INSERT) ===\n");
		
		System.out.println("ID :");
		String userId = sc.nextLine();
		
		System.out.println("PW : ");
		String userPw = sc.nextLine();
		
		System.out.println("NAME : ");
		String userName = sc.nextLine();
		
		User user = new User();
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setUserName(userName);
		
		boolean result = service.insertUser(user);
		
		if(result) {
			System.out.println("\n" + userId + "사용자가 등록 되었습니다.\n");
		}else {
			System.out.println("\n** 등록 실패 **\n");
		}
		
		
	}
	
	
	// 2. User 전체 조회(SELECT)
	private void selectAll() throws SQLException {
		
		System.out.println("\n=== 2. User 전체 조회(SELECT) ===\n");
		
		
		
		List<User> userList = service.selectAll();
		
		// 객체는 존재하지만, 컬렉션이 비어 있음
		if(userList.isEmpty()) {
			System.out.println("\n***조회 결과가 없습니다.***\n");
			return;
		}
		
		for(User user : userList ) {
			System.out.println(user);
		}
		
	}
	
	
}
