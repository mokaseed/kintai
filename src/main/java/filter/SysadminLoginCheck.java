package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entity.Employee;

/**
 * Servlet Filter implementation class SysadminLoginCheck
 */
@WebFilter(urlPatterns={"/DeleteEmpMaster", "/DeptMaster","/SysadminEmpList", "/AddEmpMaster", "/SysadminEmpMaster", "/SysadminWorkTime", "/SysadminUpdateWorkTime"})
public class SysadminLoginCheck implements Filter {

    /**
     * Default constructor. 
     */
    public SysadminLoginCheck() {
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
		HttpSession session = ((HttpServletRequest)request).getSession(false);

        try {
	        if(session.getAttribute("account") == null) {
	        	// セッションがNullならば、top画面へ
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
	            dispatcher.forward(request,response);
	        }else{
	        	// セッションがNULLでなければ、管理者権限があるか確認
	        	Employee account = (Employee)session.getAttribute("account");
	        	if(account.getSysadmin().equals("1")) {
	        	} else {
	        		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		            dispatcher.forward(request,response);
	        	}
	        }
        } catch(Exception e) {
        	e.printStackTrace();
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
            dispatcher.forward(request,response);
        }
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
