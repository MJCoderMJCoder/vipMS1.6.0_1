package com.sram.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * JDBCUtil是操作数据库的JDBC工具，用来连接和关闭数据库连接
 */
public class JDBCUtil {
	static {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	//获取con
	public static Connection getConnection() {

		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/vipms";
		try {
			con = DriverManager.getConnection(url, "root", "6003");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	//关闭连接
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}












