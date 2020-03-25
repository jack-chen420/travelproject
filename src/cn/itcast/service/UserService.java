package cn.itcast.service;

import java.util.List;
import java.util.Map;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

//ҵ��ӿ�
/**
 * @author �¹�ͬ
 *
 */

public interface UserService {
	
	/**
	 * ��ѯ�û�������Ϣ
	 * @return
	 */
	
	public List<User> findAll() ;
	
	
	public User login(User user);


	public void addUser(User user);


	public void deleteUser(String id);


	public User findUserById(String id);


	public void updateUser(User user);


	public void delSelectedUser(String[] ids);
	

//��ҳ   ����  ��ѯ
	public PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
	
}
