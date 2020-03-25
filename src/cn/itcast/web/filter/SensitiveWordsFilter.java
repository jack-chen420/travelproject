package cn.itcast.web.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class SensitiveWordsFilter
 * 
 * ���дʻ������
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SensitiveWordsFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//�������������ǿgetparameter����
		ServletRequest proxy_request=(ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				//��ǿgetparameter����
				//�жϷ���
				if (method.equals("getParameter")) {
					//��ǿ����ֵ
					String value= (String) method.invoke(request, args);
					if (value!=null) {
						for (String str : list) {
							if (value.contains(str)) {
								value=value.replaceAll(str, "***");
							}
						}
					}
					return value;
				}
				
				
				
				
				return method.invoke(request, args);//responseΪargs
			}
		});
		
		//����
		chain.doFilter(proxy_request, response);
		
	}

	/**
	 * 
	 * @see Filter#init(FilterConfig)
	 */
	
	private List<String> list=new ArrayList<String>();//���дʻ㼯��
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
		try {
			//1 �����ļ�
			ServletContext servletContext = fConfig.getServletContext();
			String realPath = servletContext.getRealPath("/WEB-INF/classes/���дʻ�.txt");
			//2 ��ȡ�ļ�
			BufferedReader br = new BufferedReader(new FileReader(realPath));
			//3���ļ�ÿһ����ӵ�������
			String line=null;
			while ((line=br.readLine())!=null) {
				list.add(line);
			}
			br.close();
			
			System.out.println(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
