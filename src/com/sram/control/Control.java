package com.sram.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import com.sram.util.JDBCUtil;

public class Control {

	public static boolean checkID(String idT){ //核对帐号
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = JDBCUtil.getConnection();
		String sql = "select id from vipuser";
		boolean bool = false;
		try {
			ps = con.prepareStatement(sql);
			//得到返回结果集：把查询出来的数据保存在rs中
			rs = ps.executeQuery();
			while(rs.next()){
				if((rs.getString("id")).equals(idT)){
					bool = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return bool;
	}

	public static boolean checkPW(String idT, String pw){ //核对密码
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = JDBCUtil.getConnection();
		String sql = "select password from vipuser where id = ?";
		boolean bool = false;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, idT);
			//得到返回结果集：把查询出来的数据保存在rs中
			rs = ps.executeQuery();
			while(rs.next()){
				if((rs.getString(1)).equals(pw)){
					bool = true;
					break;
				} else {
					bool = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return bool;
	}

	public static boolean checkPhone(String idT, String phone){ //核对密码
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = JDBCUtil.getConnection();
		String sql = "select phone from vipuser where id = ?";
		boolean bool = false;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, idT);
			//得到返回结果集：把查询出来的数据保存在rs中
			rs = ps.executeQuery();
			while(rs.next()){
				if((rs.getString(1)).equals(phone)){
					bool = true;
					break;
				} else {
					bool = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return bool;
	}
	
	public static String findPw(String idT){ //找回密码
		String pw = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = JDBCUtil.getConnection();
		String sql = "select password from vipuser where id = ?";
		boolean bool = false;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, idT);
			//得到返回结果集：把查询出来的数据保存在rs中
			rs = ps.executeQuery();
			while(rs.next()){
				pw = rs.getString("password");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return pw;
	}

}
