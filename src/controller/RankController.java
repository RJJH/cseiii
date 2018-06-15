package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dataimpl;
import data.dataservice;
import model.AllYearsStockModel;
import model.StockModel;

@Controller
public class RankController {
	dataservice dataservice=new dataimpl();
	
	List<AllYearsStockModel> list;
	
	@RequestMapping("allstock.string")
	public String allstock(@RequestParam("stock")String style,HttpSession session){
		Date d=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String date=df.format(d);
		
		list=dataservice.getStockByStyle(style, date);
		
		
		return "allstock.html";
	}

}
