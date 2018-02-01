package yujianroom.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import yujian.models.Users;
import yujian.service.UsersService;
import yujian.utilities.ConvertHelper;
import yujian.utilities.DESHelper;
import yujianroom.common.Skin;

@WebFilter(filterName = "LoginFilter", urlPatterns = { "/Index", "/Users/*", "/Adverts/*", "/Expressions/*",
		"/ExpressionBags/*", "/SysConfigs/*", "/Gifts/*", "/Orders/*", "/UserLovers/*", "/UserIntroduces/*",
		"/FeedBacks/*" })
public class LoginFilter implements Filter {

	UsersService usersService;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// 暂时不需要处理
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		try {
			// 判断用户是否登陆
			Users user = (Users) (hrequest).getSession().getAttribute(Skin.USER);
			if (user == null && !hrequest.getRequestURI().contains("Login")) {
				// 判断Cookie
				long userid = 0;
				if (hrequest.getCookies() != null) {
					for (Cookie cookie : hrequest.getCookies()) {
						if (cookie.getName().equals(Skin.USER)) {
							try {
								userid = ConvertHelper
										.convertToInt(DESHelper.decodeValue(Skin.DESKEY, cookie.getValue()));
							} catch (Exception e) {
								break;
							}
						}
					}
				}

				if (userid > 0) {
					user = usersService.getSingleByUserID(userid);
					hrequest.getSession().setAttribute(Skin.USER, user);
					chain.doFilter(request, response); // 继续
					return;
				}

				((HttpServletResponse) response).sendRedirect("/YuJianRoom/Login");
				return;
			}
		} catch (Exception e) {
			Logger.getRootLogger().error(e);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 手动初始化
		ServletContext servletContext = fConfig.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		usersService = (UsersService) ctx.getBean("usersService");
	}

}
