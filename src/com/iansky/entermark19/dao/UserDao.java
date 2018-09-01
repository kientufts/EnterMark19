package com.iansky.entermark19.dao;

import com.iansky.entermark19.DataStore;
import com.iansky.entermark19.entities.User;

public class UserDao {
	public User[] getUsers() {
		return DataStore.getUsers();
	}
}
