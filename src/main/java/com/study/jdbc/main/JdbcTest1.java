package main.java.com.study.jdbc.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcTest1 {

   public static void main(String[] args) {
	  
      Connection connection = DBConnection.getinstance().getConnection();	// DB 연결
      
      String sql = "select * from score_mst";
      
      try {
		PreparedStatement pstmt = connection.prepareStatement(sql);	// 쿼리를 입력하는 공간, 쿼리를 들고있다가
		ResultSet rs = pstmt.executeQuery();	//java.sql , 여기서 쿼리를 실행한다
		
		System.out.println("id\tname\t\tscore");
		
		while(rs.next()) {	// false가 뜰때까지 반복
			System.out.println("id: " + rs.getInt(1)	// 1 : 컬럼 번호, 0번부터 시작x, 1번부터 시작
			+ "\t name: " + rs.getString(2)
			+ "\t score: " + rs.getInt(3));
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }

}