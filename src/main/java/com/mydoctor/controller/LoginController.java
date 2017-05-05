package com.mydoctor.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mydoctor.model.User;
import com.mydoctor.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, HttpServletRequest request) {
		System.out.println("로그인");
		// 받은 parameter가 error인 경우
		if (error != null) {
			model.addAttribute("error", "Invalid username and password");
		}
		// 받은 parameter가 logout인 경우
		if (logout != null) {
			model.addAttribute("logout", "You have been logged out successfully");
		}
		if (isRememberMeAuthenticated()) {
			// send login for update
			setRememberMeTargetUrlToSession(request);
		}
		String targetUrl = getRememberMeTargetUrlFromSession(request);
		System.out.println("targetUrl :  " + targetUrl);

		return "login";
	}
	
	

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?logout";
	}

	@ResponseBody
	@RequestMapping(value = "/mobile/login", method = RequestMethod.POST, produces = "application/json")
	public User loginMobile(@RequestBody User user) throws ClientProtocolException, IOException {

		String id = user.getId();
		String password = user.getPassword();

		System.out.println(id);
		System.out.println(password);

		// JSONObject obj = new JSONObject();
		// obj.put("token", loginService.getTokenById(id));
		// TokenData tokenData = new TokenData(loginService.getTokenById(id));

		return loginService.getUserById(id, password);
	}

	/**
	 * Check if user is login by remember me cookie, refer
	 * org.springframework.security.authentication.AuthenticationTrustResolverImpl
	 */
	private boolean isRememberMeAuthenticated() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}

		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

	/**
	 * save targetURL in session
	 */
	private void setRememberMeTargetUrlToSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.setAttribute("targetUrl", "/");
		}
	}

	/**
	 * get targetURL from session
	 */
	private String getRememberMeTargetUrlFromSession(HttpServletRequest request) {
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if (session != null) {
			targetUrl = session.getAttribute("targetUrl") == null ? "" : session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}

}
