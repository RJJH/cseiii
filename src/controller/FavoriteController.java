package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dataimpl;
import data.dataservice;
import model.PersonStockModel;
import model.StockModel;

@Controller
public class FavoriteController {
	dataservice dataservice = new dataimpl();
	
	@RequestMapping("addFavorite.spring")
	public String addFavorite(@RequestParam("stockidname") String stockInfo, HttpSession session ){
		
		List<PersonStockModel> persontemp = new ArrayList<PersonStockModel>();  //获取该用户的自选股票信息
		List<List<String>> personData = new ArrayList<List<String>>();
		
		String userid = session.getAttribute("email").toString();
		String username = userid.split("@")[0];
		PersonStockModel model = new PersonStockModel();
		model.setUser_id(userid.split("@")[0]);
		StockModel res = new StockModel();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		
		if(isNumeric(stockInfo)){                                                  //构建要存入数据库的model
			model.setStock_id(stockInfo);
			List<StockModel> temp = dataservice.getStockByStockID(stockInfo);
			for(StockModel model1:temp){
//				if(model1.getDate().equals(df.format(new Date()))){
			    if(model1.getDate().equals("2017-05-24")){
					res = model1;
					break;
				}
			}
			model.setStock_name(res.getStock_name());
		}else{
			model.setStock_name(stockInfo);
			List<StockModel> temp = dataservice.getStockByStockID(stockInfo);
			for(StockModel model1:temp){
//				if(model1.getDate().equals(df.format(new Date()))){
				if(model1.getDate().equals("2017-05-24")){
					res = model1;
					break;
				
				}
			}
			model.setStock_id(res.getStock_id());
		}
		dataservice.insertpersonstock(model);
		
		persontemp = dataservice.getPersonByUserID(username);
		for(PersonStockModel model1:persontemp){
			List<String> temp12 = new ArrayList<String>();
			temp12.add(model1.getStock_name());
			temp12.add(model1.getStock_id());
			temp12.add("wtf");
			personData.add(temp12);
		}
		
		session.setAttribute("Person", personData);
		
		return "singlestock.jsp";
	}
	

	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
