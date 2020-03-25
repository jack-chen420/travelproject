package cn.itcast.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoginFilter
 */

/*
 * ��ɵ�¼��֤�Ĺ�����
 * */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//��ȡ��Դ����·��
		//ǿ��ת��
		HttpServletRequest request=(HttpServletRequest) req;
		//��ȡ��Դ����·��
		String uri = request.getRequestURI();
		//�ж��Ƿ������Դ·��,ע���ų�css/js/ͼƬ/��֤�����Դ
		if (uri.contains("/login.jsp")||uri.contains("/loginServlet")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts")||uri.contains("/checkCodeServlet")) {
			chain.doFilter(req, response);
		}else {
			Object user = request.getSession().getAttribute("user");
			if (user!=null) {
				chain.doFilter(req, response);
			}else {
				request.setAttribute("login_msg", "��δ��¼�����¼");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			
		}
		
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
