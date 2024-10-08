package service;

import java.util.Scanner;

import dao.FishDAO;
import dto.FishDTO;

public class FishService {
	// Fishdata 테이블에 데이터를 입력하기 위해서는 fishDAO 객체에 의존한다.
	FishDAO fishdao = new FishDAO();
	
	public void menu() {
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("1. 등록");
			System.out.println("2. 삭제");
			System.out.println("3. 검색");
			System.out.println("4. 전체보기");
			System.out.println("5. 수정");
			System.out.println("6. 종료");
			System.out.println("메뉴 선택");
			int num = in.nextInt();
			in.nextLine();
			if(num==1) {
				add();
			}else if(num==4) {
				list();
			}else if(num==6) {
				break;
			}
		}
	}
	private void add() {
		Scanner in = new Scanner(System.in);
		System.out.println("신규 Fish 등록");
		System.out.println("아이디를 입력");
		String id = in.nextLine();
		System.out.println("암호를 입력");
		String pass = in.nextLine();
		// DTO에 저장
		FishDTO fishdto = new FishDTO();
		fishdto.setId(id);
		fishdto.setPwd(pass);
		
		// DAO의 add 메서드 호출하여 데이터베이스에 insert
		fishdao.add(fishdto);
	}
	private void del() {
		
	}
	private void search() {
		
	}
	private void list() {
		
	}
	private void update() {
		
	}
}
