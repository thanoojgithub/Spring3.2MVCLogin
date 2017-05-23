package com.services.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.services.pojo.Login;
import com.services.pojo.User;
import com.services.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		ModelAndView mav = null;
		if (session != null && session.getAttributeNames().hasMoreElements()) {
			String username = session.getAttributeNames().nextElement();
			System.out.println("LoginController.showLogin() if " + username);
			System.out.println("LoginController.showLogin() " + username + (session.getAttribute(username) != null));
			mav = new ModelAndView("welcome").addObject("username", username);
		} else {
			System.out.println("LoginController.showLogin() else ");
			mav = new ModelAndView("login").addObject("login", new Login());
		}
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		ModelAndView mav = null;
		if (session != null && session.getAttributeNames().hasMoreElements()) {
			session.removeAttribute(session.getAttributeNames().nextElement());
			mav = new ModelAndView("login").addObject("login", new Login());
		} else {
			mav = new ModelAndView("login").addObject("login", new Login());
		}
		return mav;
	}

	@RequestMapping(value = "/loginProcess", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login) {
		ModelAndView mav = null;
		HttpSession session = request.getSession();
		if (session != null && session.getAttributeNames().hasMoreElements()) {
			System.out.println("LoginController.loginProcess() session != null " + (session != null));
			User user = (User) session.getAttribute(session.getAttributeNames().nextElement());
			if (login == null) {
				System.out.println("LoginController.loginProcess() login == null " + (login == null));
				System.out.println("LoginController.loginProcess() if " + user.getUsername());
				mav = new ModelAndView("welcome").addObject("username", user.getUsername());
			} else {
				System.out.println("LoginController.loginProcess() login != null " + (login != null));
				System.out.println("LoginController.loginProcess() if " + user.getUsername());
				mav = new ModelAndView("welcome").addObject("username", user.getUsername());
			}
		} else {
			System.out.println("LoginController.loginProcess() else new");
			User user = userService.validateUser(login);
			if (null != user) {
				System.out.println("LoginController.loginProcess() null != user " + user.getUsername());
				HttpSession sessionNew = request.getSession();
				sessionNew.setAttribute(user.getUsername(), user);
				mav = new ModelAndView("welcome").addObject("username", user.getUsername());
			} else {
				System.out.println("LoginController.loginProcess() else login");
				mav = new ModelAndView("login").addObject("message", "Username or Password is wrong!!");
			}
		}
		return mav;
	}
}
