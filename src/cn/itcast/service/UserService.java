package cn.itcast.service;

import java.util.List;
import java.util.Map;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

//业务接口
/**
 * @author 陈冠同
 *
 */

public interface UserService {
	
	/**
	 * 查询用户所有信息
	 * @return
	 */
	
	public List<User> findAll() ;
	
	
	public User login(User user);


	public void addUser(User user);


	public void deleteUser(String id);


	public User findUserById(String id);


	public void updateUser(User user);


	public void delSelectedUser(String[] ids);
	

//分页   条件  查询
	public PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
	
}
