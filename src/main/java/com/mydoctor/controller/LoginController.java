package com.mydoctor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mydoctor.exception.UserNotFoundException;
import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.BloodSugar;
import com.mydoctor.model.HeartRate;
import com.mydoctor.model.StepCount;
import com.mydoctor.model.User;
import com.mydoctor.model.UserCheckList;
import com.mydoctor.model.UserInfo;
import com.mydoctor.service.BloodPressureService;
import com.mydoctor.service.BloodSugarService;
import com.mydoctor.service.HeartRateService;
import com.mydoctor.service.LoginService;
import com.mydoctor.service.StepCountService;
import com.mydoctor.service.UserCheckListService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	@Autowired
	private BloodPressureService bloodPressureService;

	@Autowired
	private BloodSugarService bloodSugarService;

	@Autowired
	private HeartRateService heartRateService;

	@Autowired
	private StepCountService stepCountService;
	
	@Autowired
	private UserCheckListService userCheckListService;
	
	static AuthenticationManager am = new SampleAuthenticationManager();

	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, HttpServletRequest request) {
		System.out.println("로그인");
		// 받은 parameter가 error인 경우
		if (error != null) {
			model.addAttribute("error", "Invalid username and password");
			System.out.println("로그인 실패");
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

	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/mobile/login", method = RequestMethod.POST)
	public UserCheckList login(@RequestBody User user) throws ClientProtocolException, IOException {
		System.out.println(user.getId());
		System.out.println(user.getPassword());
		//UserInfo userInfo = new UserInfo();
		UserCheckList currentUserCheckList = new UserCheckList();
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword());
			Authentication result = am.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);

			//user.setIslogin(true);
			//this.loginService.setIsLogin(user);
			/*
			 * 최근꺼 가져와야함.
			 */

			
			BloodPressure bloodPressure = this.bloodPressureService.getRecentBloodPressure(user.getId());
			HeartRate heartRate = this.heartRateService.getRecentHeartRate(user.getId());
			StepCount stepCount = this.stepCountService.getRecentStepCount(user.getId());
			BloodSugar bloodSugar = this.bloodSugarService.getRecentBloodSugar(user.getId());

			currentUserCheckList = userCheckListService.findById(user.getId());
			System.out.println(currentUserCheckList);
			currentUserCheckList.getUser().setIslogin(true);
			currentUserCheckList.setLastHP(Integer.parseInt(bloodPressure.getHP()));
			currentUserCheckList.setLastHR(Integer.parseInt(bloodPressure.getHR()));
			currentUserCheckList.setLastHeartrate(heartRate.getHeartRate());
			currentUserCheckList.setLastStepcount(stepCount.getStepCount());
			currentUserCheckList.setLastBloodsugar(Integer.parseInt(bloodSugar.getBG()));
			
			if (currentUserCheckList == null) {
				throw new UserNotFoundException(user.getId());
			}
			
			//System.out.println("app dashboard" + userInfo);

		} catch (AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
		}
		System.out.println("success  " + SecurityContextHolder.getContext().getAuthentication());

		return currentUserCheckList;
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

class SampleAuthenticationManager implements AuthenticationManager {
	static final List AUTHORITIES = new ArrayList();
	static {
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));

	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication.getName().equals(authentication.getCredentials())) {
			return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(),
					AUTHORITIES);
		}
		// TODO 자동 생성된 메소드 스텁
		throw new BadCredentialsException("Bad");
	}

}
