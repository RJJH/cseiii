package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dataimpl;
import data.dataservice;
import model.UserModel;

@Controller
public class RegisterController {
	dataservice dataservice = new dataimpl();
	
	@RequestMapping("register.spring")
	public String register(@RequestParam("email") String username, @RequestParam("password") String password, @RequestParam("password2") String password2,HttpSession session){
		    if(username.equals("")){
		    	session.setAttribute("regstatus", "用户名不能为空！");
		    	return "register.jsp";
		    }
		    if(!password.equals(password2)){
		    	session.setAttribute("regstatus", "两次输入的密码不一致！");
		    	return "register.jsp";
		    }
		    
		    boolean judge = true;
			UserModel user = new UserModel();
			user.setUser_name(username);
			user.setUser_id(user.getUser_name().split("@")[0]);
			user.setUser_password(password);
			List<UserModel> res = new ArrayList<UserModel>();
			res.add(user);
			try {
				dataservice.insertuser(res);
			} catch (SQLException e) {
				judge=false;
				session.setAttribute("regstatus", "该用户已存在！");
			}
			
			if(judge){
			return "login.jsp";
			}else{
				return "register.jsp";
			}
	}

}
