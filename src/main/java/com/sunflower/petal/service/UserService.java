/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sunflower.petal.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import com.sunflower.petal.dao.UserDao;
import com.sunflower.petal.entity.User;

/**
 * UserService.java
 * 
 * @see
 * @author sunny
 * @version 1.0.0
 * @date 2014年3月31日
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 登录
	 * 
	 * @param user
	 */
	public boolean login(User user) {
		List<User> list = userDao.getUser();
		if (list == null || list.size() == 0) {
			throw new RuntimeException("there is no user");
		} else {
			for (User u : list) {
				if ((u.getUsername().equals(user.getUsername()))&& (u.getPassword().equals(md5Twice(user.getPassword())))) {
					return true;
				}
			}
			return false;
		}
	}

	public String md5Twice(String password) {
		try {
			byte[] b = DigestUtils.md5Digest(DigestUtils.md5Digest(password
					.getBytes("UTF-8")));
			return new String(b, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("login failed");
		}
	}

	/**
	 * 退出
	 * 
	 * @param user
	 */
	public boolean quit(User user) {
		return true;
	}

}
