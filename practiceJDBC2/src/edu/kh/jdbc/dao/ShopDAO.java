package edu.kh.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.kh.jdbc.dto.ShopMember;

public class ShopDAO {
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public ShopMember selectMember(Connection conn, String memberId) {
		
		ShopMember sm = null;
		
		try {
			
			//1, 문제 쿼리문 문자열 인식 불가
			String sql = """
					SELECT 
						*
					FROM
						SHOP_MEMBER
					WHERE
						MEMBER_ID = ?
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			// 2  placeholder에 대입할 값이 없음.
			pstmt.setString(1, memberId);
			
			// 3 문제 결과 값 받을 객체가 없음
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				String id = rs.getString("MEMBER_ID");
				String pw = rs.getString("MEMBER_PW");
				String phone = rs.getString("PHONE");
				String gender = rs.getString("성별");
				
				sm = new ShopMember(id, pw, phone, gender);
						
			}
					
			
			
		} catch(SQLException e) {
			 e.printStackTrace();
		} 
		
		return sm;
	}
}
