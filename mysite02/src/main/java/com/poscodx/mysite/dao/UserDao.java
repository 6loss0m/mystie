package com.poscodx.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poscodx.mysite.vo.UserVo;

public class UserDao {

	public Boolean insert(UserVo userVo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();

			String sql = "insert into user values(null, ?, ?, password(?), ?, current_date())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getEmail());
			pstmt.setString(3, userVo.getPassword());
			pstmt.setString(4, userVo.getGender());

			int count = pstmt.executeUpdate();

			// 5. 결과 처리
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public UserVo findByEmailAndPassword(String email, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo userVo = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select no, name from user where email = ? and password = password(?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
			}

			// 5. 결과 처리
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userVo;
	}

	public UserVo findByNo(Long no) {
		UserVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = DBConnection.getConnection();
			
			String sql = "select no, name, email, gender from user where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVo();
				
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setGender(rs.getString(4));
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}

	public void update(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			
			if("".equals(vo.getPassword())) {
				String sql = "update user set name=?, gender=? where no=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setLong(3, vo.getNo());
			} else {
				String sql = "update user set name=?, gender=?, password=password(?) where no=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setString(3, vo.getPassword());
				pstmt.setLong(4, vo.getNo());
			}
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
}
