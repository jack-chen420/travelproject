package cn.itcast.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;

public class UserDaoImpl implements UserDao{
	private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from user";
		//泛型为了向下兼容
		List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));		
		return users;
	}
	@Override
	public User findUserByUsernameAndePassword(String username, String password) {
		// TODO Auto-generated method stub
		try {
			String sql="select * from user where username=? and password = ? " ;
			
			User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username,password);
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		String sql="insert into user values(null,?,?,?,?,?,?,null,null)";
		template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
	
		
	}
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from user where id = ?";
		template.update(sql,id);
		
	}
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		String sql="select * from user where id = ?";		
		
			
		return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),id);
	}
	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		String sql="update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
		template.update(sql, user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
		
		
		
	}
	@Override
	public int findTotalCount(Map<String, String[]> condition) {
		// TODO Auto-generated method stub
		String sql="select count(*) from user where 1=1";
		StringBuilder sb = new StringBuilder(sql);
		//遍历map
		Set<String> keySet = condition.keySet();
		//定义参数的集合
		List<Object> params=new ArrayList<Object>();
		for (String key : keySet) {
			//排除分页参数
			if ("currentPage".equals(key)||"rows".equals(key)) {
				//结束循环
				continue;
			}
			
			
			//获取value
			String value = condition.get(key)[0];
			//判断value是否有值
			if (value !=null&&!"".equals(value)) {
				//有值
				sb.append(" and " + key + " like ? ");
				params.add("%"+value+"%");//?条件的值
			}
		}
		
		System.out.println(sb.toString());
		System.out.println(params);
		
		
		
		return template.queryForObject(sb.toString(), Integer.class, params.toArray());
		
	}
	
	
	@Override
	public List<User> findByPage(int start, int rows,Map<String, String[]> condition) {
		// TODO Auto-generated method stub
		String sql="select * from user where 1 = 1 ";
		
		StringBuilder sb = new StringBuilder(sql);
		//遍历map
		Set<String> keySet = condition.keySet();
		//定义参数的集合
		List<Object> params=new ArrayList<Object>();
		for (String key : keySet) {
			//排除分页参数
			if ("currentPage".equals(key)||"rows".equals(key)) {
				//结束循环
				continue;
			}
			
			
			//获取value
			String value = condition.get(key)[0];
			//判断value是否有值
			if (value !=null&&!"".equals(value)) {
				//有值
				sb.append(" and " + key + " like ? ");
				params.add("%"+value+"%");//?条件的值
			}
		}
		
		//添加分页查询
		
		sb.append(" limit ? , ?");
		params.add(start);
		params.add(rows);
		
		System.out.println(sb.toString());
		System.out.println(params);
		
		
		return template.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class),params.toArray());
	}
	
	
	
	
}
