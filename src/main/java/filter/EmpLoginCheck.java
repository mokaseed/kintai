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

/**
 * Servlet Filter implementation class LoginCheck
 */
@WebFilter(urlPatterns={ "/ClockOn", "/Cond", "/Forward", "/SelectWorkTimeList", "/UpdateWorkTime", "/EmpList", "/PersonalSettingsEmpMaster", "/AddSchedule", "/MyCalendarMain"})

public class EmpLoginCheck implements Filter {

    /**
     * Default constructor. 
     */
    public EmpLoginCheck() {
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
		
		// セッションが存在しない場合NULLを返す
        HttpSession session = ((HttpServletRequest)request).getSession(false);

        try {
	        if(session.getAttribute("account") == null) {
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
	            dispatcher.forward(request,response);
	        }else{
	        	// セッションがNULLでなければ、通常どおりの遷移
	            chain.doFilter(request, response);
	        }
        } catch(Exception e) {
        	e.printStackTrace();
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
            dispatcher.forward(request,response);
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
