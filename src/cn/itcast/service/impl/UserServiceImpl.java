package cn.itcast.service.impl;

import java.util.List;
import java.util.Map;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao dao = new UserDaoImpl();
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		//µ÷ÓÃdao		
		return dao.findAll();
	}
	
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return dao.findUserByUsernameAndePassword(user.getUsername(), user.getPassword());
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		dao.add(user);
		
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		dao.delete(Integer.parseInt(id));
	}

	@Override
	public User findUserById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(Integer.parseInt(id));
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		dao.update(user);
	}

	@Override
	public void delSelectedUser(String[] ids) {
		// TODO Auto-generated method stub
		if (ids!=null&&ids.length>0) {
			for (String id : ids) {
				dao.delete(Integer.parseInt(id));
		}
			
		}
	}

	@Override
	public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
		// TODO Auto-generated method stub
		
		
		int currentPage=Integer.parseInt(_currentPage);
		int rows = Integer.parseInt(_rows);
		
		if (currentPage<=1) {
			currentPage=1;
		}
		

		
		PageBean<User> pb = new PageBean<User>();
		pb.setCurrentPage(currentPage);
		pb.setRows(rows); 
		
		int totalCount=dao.findTotalCount(condition);		
		pb.setTotalCount(totalCount);
		
		
		int start=(currentPage-1)*rows;
		List<User>list=dao.findByPage(start,rows,condition);
		
		pb.setList(list);
		
		if (totalCount<rows) {
			int totalPage=1;
			pb.setTotalPage(totalPage);
		}else {
			int totalPage=(totalCount%rows) == 0 ? (totalCount%rows): (totalCount%rows) + 1;
			pb.setTotalPage(totalPage);
		}
		
		
		
		
		
	
	
		return pb;
	}
	
	
}
