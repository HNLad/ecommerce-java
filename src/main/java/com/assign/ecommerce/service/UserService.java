package com.assign.ecommerce.service;

import com.assign.ecommerce.model.UserModel;

public interface UserService {

	public UserModel registerUser(UserModel user);

	public UserModel findByUsername(String username);

}
