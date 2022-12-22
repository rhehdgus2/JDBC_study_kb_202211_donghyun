package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcSelect2 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("작성자 id: ");
		int writerId = scanner.nextInt();
		
		Connection connection = DBConnection.getinstance().getConnection();
		
		String sql = "select * from board_mst where writer_id = ?";	// \'donghyun\' : 작은 따옴표는 역슬러시를 꼭 써줘야함
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, writerId); 		// setInt(1) : line 20에서의 첫번째 물음표를 의미함
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1) + " | "
				+ rs.getString(2) + " | "
				+ rs.getString(3) + " | "
				+ rs.getInt(4) + " | "
				+ rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
