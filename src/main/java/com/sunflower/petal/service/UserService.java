
package com.sunflower.petal.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import com.sunflower.petal.dao.UserDao;
import com.sunflower.petal.entity.DataTableRequest;
import com.sunflower.petal.entity.DataTableResponse;
import com.sunflower.petal.entity.User;
import com.sunflower.petal.utils.CommonUtil;
import com.sunflower.petal.utils.IdWorker;

/**
 * UserService.java
 * 
 * @see
 * @author sunny
 * @version 1.0.0
 * @date 2014年3月31日
 */
@Service
public class UserService{
	@Autowired
	private UserDao userDao;
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	private IdWorker worker = new IdWorker(2);
	/** 管理员管理用户相关 *********************************************************************************************/
	/**
	 * 管理员新增加一个用户
	 * 用于维护客户，供货商等基础信息，和系统关联上
	 * 应用于非注册用户，单用户信息应当维护在系统内的场景
	 * 用户名不能为空
	 * 1： 如果管理员有给予用户名，则使用改用户名，否则用户名和名称一致
	 * 2： 密码和用户名一致
	 * 3: 不保障添加用户成功（出于登陆用户名不重复等规则限制等考虑）
	 * 4: 初始化用外部生成用户唯一Id策略
	 * @param user
	 */
	@Transactional
	public boolean add(User user){
		boolean result = false;
		try{
			Long uid = user.getId();
			if(CommonUtil.IsNull(uid)){
				uid=worker.nextId();
			}
			user.setId(uid);
			if(CommonUtil.IsNull(user.getUsername())) {
				user.setUsername(user.getName());
			}
			user.setPassword(user.getUsername());
			userDao.add(user);
			userDao.updateAccount(uid,user.getUsername(),this.md5Twice(user.getPassword()));
			userDao.logout(uid);
			userDao.disable(uid);
			result = true;
		}catch (Exception e){
			e.printStackTrace();
			logger.error("添加用户失败",e);
			result = false;
			throw new RuntimeException(e);
		}finally {
			return result;
		}
	}

	/**
	 * 批量删除用户 此接口出于业务规则，会做一定程度上限制
	 * @param ids
	 */
	public  void delete(Long[] ids){
		userDao.batchDelete(ids);
	}

	/**
	 * 管理员更新维护一个用户信息
	 * 规则：
	 * 1： 除角色以外，不能维护账号体系相关内容，包括用户名和密码
	 * 2：
	 * @param user
	 */
	public void update(User user) {
		userDao.update(user);
	}
	/**
	 * 设置让用户暂时不能登录该系统
	 * @param user
	 */
	public void disable(User user) {
		userDao.logout(user.getId());
		userDao.disable(user.getId());
	}

	/**
	 * 设置让用户能使用改系统
	 * @param user
	 */
	public void enable(User user) {
		userDao.enable(user.getId());
	}
	/** 系统账户相关 **************************************************************************************************/
	/**
	 * 新用户注册到系统中
	 * 新增电话号码，联系方式等基础信息
	 * <username,password> 为必选项
	 * @param user
	 * @return 是否注册成功
	 */
	public boolean register(User user) {
		userDao.add(user);
		Long uid = user.getId();
		userDao.updateAccount(uid,user.getUsername(), this.md5Twice(user.getPassword()));
		return true;
	}

	/**
	 * 激活该账户
	 * @param user
	 * @return
	 */
	public boolean activate(User user) {
		userDao.enable(user.getId());
		return true;
	}

	/**
	 * 注销账户
	 * 系统修改标志位状态，表示账号注销
	 * @param user
	 * @return 是否成功注销
	 */
	public boolean discard(User user) {
		userDao.disable(user.getId());
		return true;
	}
	/**
	 * 登录
	 * 需要用户名+密码
	 * @param user
	 */
	public boolean login(User user) {
		User dbUser = userDao.getUserByCount(user.getUsername(), this.md5Twice(user.getPassword()));
		if(null == dbUser)
			return false;
		//修改登陆状态
		userDao.login(dbUser.getId());
		return true;
	}

	/**
	 * 退出
	 * 1: 使用用户名和密码
	 * 2: 使用uid 去退出登陆
	 * @param user
	 */
	public boolean logout(User user) {
		User dbUser = userDao.getUserByCount(user.getUsername(), this.md5Twice(user.getPassword()));
		if(null==dbUser) {
			if(null==user.getId()){
				return false;
			}else{
				userDao.logout(user.getId());
				return true;
			}
		}else{
			userDao.logout(dbUser.getId());
			return true;
		}
	}

	protected String md5Twice(String password) {
		try {
			byte[] b = DigestUtils.md5Digest(DigestUtils.md5Digest(password
					.getBytes("UTF-8")));
			return new String(b, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("login failed");
		}
	}
	public DataTableResponse getDataTableList(DataTableRequest request) {
		Integer start = request.getStart();
		Integer length = request.getLength();
		String search = request.getSearch().get(DataTableRequest.SEAECH_VALUE);
		List<User> results = listPageAndSearchByName(start, length, search);
		Long count = getCountBySearchName(search);
		DataTableResponse response = new DataTableResponse();
		response.setData(results.toArray());
		response.setDraw(request.getDraw());
		response.setRecordsTotal(count);
		response.setRecordsFiltered(count);
		return response;
	}

	public Long getCountBySearchName(String search) {
		return userDao.getCountBySearchName(search);
	}

	public List<User> listPageAndSearchByName(Integer start, Integer length, String search) {
		List<User> users = userDao.listPageAndSearchByNamess(start, length, search);
		return users;
	}


	public User get(Long id) {
		return userDao.get(id);
	}

	public void disable(Long[] ids) {
		for (Long id : ids) {
			userDao.disable(id);
		}

	}
}
