package cn.itcast.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

//jdbc������

public class JDBCUtils {

	
	private static DataSource ds;
	
	static {
		
		
//���������ļ�
		
		try {
			Properties pro =new Properties();
			//���������ļ�
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
	
	
	
	
	//���ӳض���
	
	public static DataSource getDataSource() {
		return ds;
	}
	
	
	
	
	
//	��ȡconnect����
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	
	
	
	
	
}
