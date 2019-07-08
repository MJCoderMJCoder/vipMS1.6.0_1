package com.sram.dao;

import java.util.List;

import com.sram.bean.User;

public interface UserDao {
	//添加
	public void addUser(User user);
	//修改
	public void updateUser(User user);
	//删除
	public void deleteUser(String string);
	//查询单个对象
	public User load(String id);
	//查询所有对象,所有对象用集合保存
	public List<User> listsUser();
	//模糊查询
	public List<User> findUsers(String id, String username, String tel);
}











