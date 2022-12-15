package main.java.com.study.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DBConnection {

   private static DBConnection instance = null;

   private DBConnection() {
   }

   public static DBConnection getinstance() {
      if (instance == null) {
         instance = new DBConnection();
      }
      return instance;

   }

   public Connection getConnection() {
   Connection connection = null;
   String url = null;
   String username = null;
   String password = null;
      
      try {
         Class.forName(Driver.class.getName()); // 클래스를 찾아서 객체 생성
         System.out.println("데이터베이스 드라이브 로딩 성공!!!!!!");
         url = "jdbc:mysql://localhost:3306/subquery_study"; // 서버안에 들어있는 데이터명 
         username = "root";
         password = "root";
         
         connection = DriverManager.getConnection(url, username, password); // DB를 연결시켜주는 핵심 객체
         
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
         System.out.println("드라이버 로딩 실패!"); // 파일 손상 및 경로가 틀린경우
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("데이터베이스 연결 실패!"); // 주소가 틀리거나 아이디나 비번이 틀린경우
      }
      return connection;

   }
}