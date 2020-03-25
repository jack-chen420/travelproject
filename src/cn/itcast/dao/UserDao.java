package cn.itcast.dao;

import java.util.List;
import java.util.Map;

import cn.itcast.domain.User;

/**
 * 用户操作的DAO
 * 
 * */


public interface UserDao {

	public List<User> findAll();
	
	public User findUserByUsernameAndePassword(String username,String password) ;

	public void add(User user);

	public void delete(int parseInt);

	public User findById(int parseInt);

	public void update(User user);





	public int findTotalCount(Map<String, String[]> condition);

	public List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
