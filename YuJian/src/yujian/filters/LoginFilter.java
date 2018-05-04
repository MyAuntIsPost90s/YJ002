package yujian.filters;

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
/*
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import yujian.common.Skin;
import yujian.models.Users;
/*
import yujian.common.Skin;
import yujian.models.Users;*/
import yujian.service.UsersService;
import yujian.utilities.ConvertHelper;
import yujian.utilities.DESHelper;
/*
import yujian.utilities.ConvertHelper;
import yujian.utilities.DESHelper;*/

/**
 * Servlet Filter implementation class LoginFilter
 */
// @WebFilter(filterName="LoginFilter",urlPatterns={"/CommonUsers","/ExpressionShop","/Expressions"
// ,"/SortUsers","/Mine","/UserDetail","/MyInfoSet","/LeaveWords"
// ,"/FeedBacks","/MyIntroduces","/MyIntroducesAdd","/MyIntroducesEdit"
// ,"/GiftShop","/UserGifts","/GiftRank","/CollectUser"
// ,"/RegisterHeadImg","/RegisterBase","/RegisterHobby"
// ,"/MyInfo","/MyInfoMatchmaker","/UpdateHobby","/UpdateRedHobby"
// ,"/UpdateBaseInfo","/UpdateRedBaseInfo","/UpdateSelectCondition"
// ,""})
//@WebFilter(filterName = "LoginFilter", urlPatterns = { "/*" })
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
		// 配置不过滤
		if (hrequest.getRequestURI().contains("Login") || hrequest.getRequestURI().contains("Share")
				|| hrequest.getRequestURI().contains("GetOtherUserInfo")
				|| hrequest.getRequestURI().contains("WxNotify")
				|| hrequest.getRequestURI().contains("GetUserImgs") || hrequest.getRequestURI().contains("404")
				|| hrequest.getRequestURI().contains("500") || hrequest.getRequestURI().contains("LoginErr")
				|| hrequest.getRequestURI().contains("Contents")) {
			chain.doFilter(request, response);
			return;
		}
		// 判断用户是否登陆
		Users user = (Users) (hrequest).getSession().getAttribute(Skin.USER);
		if (user == null && !hrequest.getRequestURI().contains("Login")) {
			// 判断Cookie
			Long userid = null;
			if (hrequest.getCookies() != null) {
				for (Cookie cookie : hrequest.getCookies()) {
					if (cookie.getName().equals(Skin.USER)) {
						try {
							userid = ConvertHelper.convertToLong(DESHelper.decodeValue(Skin.DESKEY, cookie.getValue()));
						} catch (Exception e) {
							break;
						}
					}
				}
			}

			if (userid != null && userid > 0) {
				user = usersService.getSingleByUserID(userid);
				hrequest.getSession().setAttribute(Skin.USER, user);
				chain.doFilter(request, response); // 继续
				return;
			}

			// 跳出去登陆
			((HttpServletResponse) response).sendRedirect("/YuJian/WeiXinLogin");
			return;
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
		// System.out.println(usersService);
	}

}
