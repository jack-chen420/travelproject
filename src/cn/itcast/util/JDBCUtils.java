package cn.itcast.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

//jdbc工作类

public class JDBCUtils {

	
	private static DataSource ds;
	
	static {
		
		
//加载配置文件
		
		try {
			Properties pro =new Properties();
			//加载配置文件
			InputStream is=JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
			
			pro.load(is);
			
			ds=DruidDataSourceFactory.createDataSource(pro);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	
	
	//连接池对象
	
	public static DataSource getDataSource() {
		return ds;
	}
	
	
	
	
	
//	获取connect对象
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	
	
	
	
	
}
