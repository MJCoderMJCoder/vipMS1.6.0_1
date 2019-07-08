package com.sram.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sram.bean.User;
import com.sram.dao.UserDao;
import com.sram.util.JDBCUtil;

/*
 * 实现类：实现借口implements UserDao
 */
public class UserDaoImpl implements UserDao{

	@Override
	public void addUser(User user) { //添加
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBCUtil.getConnection();
			//动态SQL语句
			String sql = "insert into vipuser(id, name, password, phone)" +
			"values(?,?,?,?)";
			//动态SQL语句对象ps
			ps = con.prepareStatement(sql);
			//构建动态SQL语句：替换占位符。下标代表占位符的位置，从1开始
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, null);
		}
	}

	@Override
	public void deleteUser(String id) { //删除
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = JDBCUtil.getConnection();
			String sql = "delete from vipuser where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(con, ps, null);
		}
	}

	@Override
	public List<User> findUsers(String id, String username, String tel) { //模糊查询
		List<User> users = new ArrayList<User>();
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = JDBCUtil.getConnection();
		String sql = "select id, name, phone from vipuser where id like ? and name like ? and phone like ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, username);
			ps.setString(3, tel);
			//得到返回结果集：把查询出来的数据保存在rs中
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User();
				//把rs保存的东西取出来，赋值给user
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPhone(rs.getString(3));
				//把查出来的用户放到集合里面
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return users;
	}

	@Override
	public List<User> listsUser() { //查询所有对象,所有对象用集合保存
		List<User> users = new ArrayList<User>();
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = JDBCUtil.getConnection();
		String sql = "select id, name, phone from vipuser";
		try {
			ps = con.prepareStatement(sql);
			 //得到返回结果集：把查询出来的数据保存在rs中
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User();
				//把rs保存的东西取出来，赋值给user
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPhone(rs.getString(3));
				//把查出来的用户放到集合里面
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return users;
	}

	@Override
	public User load(String id) { //查询单个对象
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = JDBCUtil.getConnection();
		String sql = "select * from vipuser where id = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			 //得到返回结果集：把查询出来的数据保存在rs中
			rs = ps.executeQuery();
			if(rs.next()){
				user = new User();
				//把rs保存的东西取出来，赋值给user
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setPhone(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, rs);
		}
		return user;
	}

	@Override
	public void updateUser(User user) { //修改
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = JDBCUtil.getConnection();
			String sql = "update vipuser set name = ?, password = ?, phone = ? where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getId());
			ps.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			JDBCUtil.close(con, ps, null);
		}
	}

}









