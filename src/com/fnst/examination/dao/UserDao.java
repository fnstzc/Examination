package com.fnst.examination.dao;

import com.fnst.examination.entity.User;

public interface UserDao {
	public void addUser(User user);
	public void deleteUser(User user);
	public User selectUserByName(String name);
}
