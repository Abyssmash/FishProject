package dao;
// fishdata table CRUD

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.FishDTO;

public class FishDAO {

	private String username="root";
	private String password="11111111";
	private String url="jdbc:oracle:thin:@localhost:1521:orcl";
	private String driverName="oracle.jdbc.driver.OracleDriver";
	private Connection conn=null;
	
	public FishDAO(){
		init();
	}
	private void init() {	// 드라이버 로드
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("오라클 드라이버 로드 성공");
		// 이 문구가 출력된다면 오라클사에서 배포한 라이브러리를 사용할 준비가 완료된 것
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private boolean conn() {	// 커넥션 가져오는 공통 코드를 메서드로 정의
		try {
			conn = DriverManager.getConnection
						("jdbc:oracle:thin:@localhost:1521:orcl"/*포트넘버 1521*/,
								"system"/*ID*/,
								"11111111"/*PWD*/);
				System.out.println("커넥션 자원 획득 성공");
			return true;	// 커넥션 자원을 획득한 경우
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 커넥션 자원을 획득하지 못한 경우
	}
	public void add(FishDTO fdto) {
		if(conn()) {
			try {
				String sql = "insert into fishdata values (?,?,default)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, fdto.getId());
				psmt.setString(2, fdto.getPwd());
				// 쿼리 실행
				//psmt.executeUpdate();
				//쿼리 실행 리천을 받아서 다음 처리 작업 정의
				int resultInt = psmt.executeUpdate();
				// 트렌젝션
				if(resultInt > 0) {
					conn.commit(); // 영구적 저장
				}else {
					conn.rollback(); // 작업 취소
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
		}else {
			System.out.println("데이터 베이스 커넥션 실패");
		}
	}
}
