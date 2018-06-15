package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dataimpl;
import data.dataservice;
import model.HistoryStockModel;
import model.PersonStockModel;
import model.StockModel;
import model.TigerModel;
import model.UserModel;
import model.ZhishuModel;

@Controller
public class LoginController {
	dataservice dataservice = new dataimpl();
	
	@RequestMapping("login.spring")
	public String login(@RequestParam("email") String username, @RequestParam("password") String password,ModelMap modelMap,HttpSession session){
       
		List<List<String>> historyData = new ArrayList<List<String>>();
		List<List<String>> personData = new ArrayList<List<String>>();
		
		UserModel user = dataservice.getUserByUserID(username.split("@")[0]);
		if(user!=null&&password.equals(user.getUser_password())){
			session.setAttribute("email", username);
			session.setMaxInactiveInterval(15*60);
		    System.out.println(session.getAttribute("email"));
			System.out.println("登陆成功！");
			String user_id = username.split("@")[0];
			List<PersonStockModel> favoriteList = dataservice.getPersonByUserID(user_id);
			List<HistoryStockModel> historyList = dataservice.getHistoryByUserID(user_id);
			for(HistoryStockModel model:historyList){
			     List<String> temp11 = new ArrayList<String>();
			     temp11.add(model.getStock_name());
			     temp11.add(model.getStock_id());
			     String[] timetemp = model.getCraw_time().split("-");
			     temp11.add(timetemp[1]+"-"+timetemp[2]);
			     historyData.add(temp11);
			}
			for(PersonStockModel model:favoriteList){
				List<String> temp12 = new ArrayList<String>();
				temp12.add(model.getStock_name());
				temp12.add(model.getStock_id());
				
				personData.add(temp12);
			}
			
			session.setAttribute("Person", personData);
			session.setAttribute("History", historyData);
			
			
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");                 //设置日期格式
			double[][] Matrix = Markov();
			
			List<Double> today = new ArrayList<Double>();
			List<Double> today_shen = new ArrayList<Double>();
			double pointtoday = Double.parseDouble(dataservice.getShangzhi("2017-06-09").get(0).getB());    //这是今日的指数,调用数据库获得
			double pointtoday_shen = Double.parseDouble(dataservice.getShenzhi("2017-06-09").get(0).getB()); 
			
			if(pointtoday<3081){
				today.add(1.0);
				today.add(0.0);
				today.add(0.0);
				today.add(0.0);
			}
			if(pointtoday<3161&&pointtoday>=3081){
				today.add(0.0);
				today.add(1.0);
				today.add(0.0);
				today.add(0.0);
			}
			if(pointtoday<3241&&pointtoday>=3161){
				today.add(0.0);
				today.add(0.0);
				today.add(1.0);
				today.add(0.0);
			}
			if(pointtoday>=3241){
				today.add(0.0);
				today.add(0.0);
				today.add(0.0);
				today.add(1.0);
			}
			
			
			if(pointtoday_shen<3081){
				today_shen.add(1.0);
				today_shen.add(0.0);
				today_shen.add(0.0);
				today_shen.add(0.0);
			}
			if(pointtoday_shen<3161&&pointtoday_shen>=3081){
				today_shen.add(0.0);
				today_shen.add(1.0);
				today_shen.add(0.0);
				today_shen.add(0.0);
			}
			if(pointtoday_shen<3241&&pointtoday_shen>=3161){
				today_shen.add(0.0);
				today_shen.add(0.0);
				today_shen.add(1.0);
				today_shen.add(0.0);
			}
			if(pointtoday_shen>=3241){
				today_shen.add(0.0);
				today_shen.add(0.0);
				today_shen.add(0.0);
				today_shen.add(1.0);
			}
			
			List<Double> result1 = cal(today,Matrix);
			List<Double> result2 = cal(result1,Matrix);
			List<Double> result3 = cal(result2,Matrix);
			List<Double> result4 = cal(result3,Matrix);
			List<Double> result5 = cal(result4,Matrix);
			List<Double> result6 = cal(result5,Matrix);
			List<Double> result7 = cal(result6,Matrix);
			List<Double> result8 = cal(result7,Matrix);
			List<Double> result9 = cal(result8,Matrix);
			List<Double> result10 = cal(result9,Matrix);
			
			List<Double> shen_result1 = cal(today_shen,Matrix);
			List<Double> shen_result2 = cal(shen_result1,Matrix);
			List<Double> shen_result3 = cal(shen_result2,Matrix);
			List<Double> shen_result4 = cal(shen_result3,Matrix);
			List<Double> shen_result5 = cal(shen_result4,Matrix);
			List<Double> shen_result6 = cal(shen_result5,Matrix);
			List<Double> shen_result7 = cal(shen_result6,Matrix);
			List<Double> shen_result8 = cal(shen_result7,Matrix);
			List<Double> shen_result9 = cal(shen_result8,Matrix);
			List<Double> shen_result10 = cal(shen_result9,Matrix);
			
			List<List<Double>> shangres = new ArrayList<List<Double>>();
			shangres.add(result1);
			shangres.add(result2);
			shangres.add(result3);
			shangres.add(result4);
			shangres.add(result5);
			shangres.add(result6);
			shangres.add(result7);
			shangres.add(result8);
			shangres.add(result9);
			shangres.add(result10);
			
			List<List<Double>> shenres = new ArrayList<List<Double>>();
			shenres.add(shen_result1);
			shenres.add(shen_result2);
			shenres.add(shen_result3);
			shenres.add(shen_result4);
			shenres.add(shen_result5);
			shenres.add(shen_result6);
			shenres.add(shen_result7);
			shenres.add(shen_result8);
			shenres.add(shen_result9);
			shenres.add(shen_result10);
			
			List<Double> shang1 = new ArrayList<Double>();
			List<Double> shang2 = new ArrayList<Double>();
			List<Double> shang3 = new ArrayList<Double>();
			List<Double> shang4 = new ArrayList<Double>();
			
			List<Double> shen1 = new ArrayList<Double>();
			List<Double> shen2 = new ArrayList<Double>();
			List<Double> shen3 = new ArrayList<Double>();
			List<Double> shen4 = new ArrayList<Double>();
			
				shang1.add(result1.get(0));
				shang1.add(result2.get(0));
				shang1.add(result3.get(0));
				shang1.add(result4.get(0));
				shang1.add(result5.get(0));
				shang1.add(result6.get(0));
				shang1.add(result7.get(0));
				shang1.add(result8.get(0));
				shang1.add(result9.get(0));
				shang1.add(result10.get(0));
				
				shen1.add(shen_result1.get(0));
				shen1.add(shen_result2.get(0));
				shen1.add(shen_result3.get(0));
				shen1.add(shen_result4.get(0));
				shen1.add(shen_result5.get(0));
				shen1.add(shen_result6.get(0));
				shen1.add(shen_result7.get(0));
				shen1.add(shen_result8.get(0));
				shen1.add(shen_result9.get(0));
				shen1.add(shen_result10.get(0));
				
				shang2.add(result1.get(1));
				shang2.add(result2.get(1));
				shang2.add(result3.get(1));
				shang2.add(result4.get(1));
				shang2.add(result5.get(1));
				shang2.add(result6.get(1));
				shang2.add(result7.get(1));
				shang2.add(result8.get(1));
				shang2.add(result9.get(1));
				shang2.add(result10.get(1));
				
				shen2.add(shen_result1.get(1));
				shen2.add(shen_result2.get(1));
				shen2.add(shen_result3.get(1));
				shen2.add(shen_result4.get(1));
				shen2.add(shen_result5.get(1));
				shen2.add(shen_result6.get(1));
				shen2.add(shen_result7.get(1));
				shen2.add(shen_result8.get(1));
				shen2.add(shen_result9.get(1));
				shen2.add(shen_result10.get(1));
			
				shang3.add(result1.get(2));
				shang3.add(result2.get(2));
				shang3.add(result3.get(2));
				shang3.add(result4.get(2));
				shang3.add(result5.get(2));
				shang3.add(result6.get(2));
				shang3.add(result7.get(2));
				shang3.add(result8.get(2));
				shang3.add(result9.get(2));
				shang3.add(result10.get(2));
				
				shen3.add(shen_result1.get(2));
				shen3.add(shen_result2.get(2));
				shen3.add(shen_result3.get(2));
				shen3.add(shen_result4.get(2));
				shen3.add(shen_result5.get(2));
				shen3.add(shen_result6.get(2));
				shen3.add(shen_result7.get(2));
				shen3.add(shen_result8.get(2));
				shen3.add(shen_result9.get(2));
				shen3.add(shen_result10.get(2));
				
				shang4.add(result1.get(3));
				shang4.add(result2.get(3));
				shang4.add(result3.get(3));
				shang4.add(result4.get(3));
				shang4.add(result5.get(3));
				shang4.add(result6.get(3));
				shang4.add(result7.get(3));
				shang4.add(result8.get(3));
				shang4.add(result9.get(3));
				shang4.add(result10.get(3));
				
				shen4.add(shen_result1.get(3));
				shen4.add(shen_result2.get(3));
				shen4.add(shen_result3.get(3));
				shen4.add(shen_result4.get(3));
				shen4.add(shen_result5.get(3));
				shen4.add(shen_result6.get(3));
				shen4.add(shen_result7.get(3));
				shen4.add(shen_result8.get(3));
				shen4.add(shen_result9.get(3));
				shen4.add(shen_result10.get(3));
				
				session.setAttribute("shang1", shang1);
				session.setAttribute("shang2", shang2);
				session.setAttribute("shang3", shang3);
				session.setAttribute("shang4", shang4);
				session.setAttribute("shen1", shen1);
				session.setAttribute("shen2", shen2);
				session.setAttribute("shen3", shen3);
				session.setAttribute("shen4", shen4);
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

			String date = "2017-06-14";
			//沪深板块
			List<StockModel> hushen = new ArrayList<StockModel>();
			hushen = dataservice.getAllStocks("stock_hushen",date);
			List<List<String>> hushenData = new ArrayList<List<String>>();
			for(int i = 0;i<hushen.size();i++){
				List<String> x = new ArrayList<String>();
				x.add(hushen.get(i).getStock_id());
				x.add(hushen.get(i).getStock_name());
				x.add(String.valueOf(hushen.get(i).getStock_price()));
				x.add(String.valueOf(hushen.get(i).getStock_change()));
				x.add(String.valueOf(hushen.get(i).getStock_range()));
				x.add(String.valueOf(hushen.get(i).getStock_trading_number()));
				x.add(String.valueOf(hushen.get(i).getStock_trading_value()));
				x.add(String.valueOf(hushen.get(i).getStock_yesterdayfinish_price()));
				x.add(String.valueOf(hushen.get(i).getStock_todaystart_price()));
				x.add(String.valueOf(hushen.get(i).getStock_max_price()));
				x.add(String.valueOf(hushen.get(i).getStock_min_price()));
				hushenData.add(x);
			}
			//上证A
			List<StockModel> shanga = new ArrayList<StockModel>();
			shanga = dataservice.getAllStocks("stock_shanga", date);
			List<List<String>> shangaData = new ArrayList<List<String>>();
			for(int i = 0;i<shanga.size();i++){
				List<String> x = new ArrayList<String>();
				x.add(shanga.get(i).getStock_id());
				x.add(shanga.get(i).getStock_name());
				x.add(String.valueOf(shanga.get(i).getStock_price()));
				x.add(String.valueOf(shanga.get(i).getStock_change()));
				x.add(String.valueOf(shanga.get(i).getStock_range()));
				x.add(String.valueOf(shanga.get(i).getStock_trading_number()));
				x.add(String.valueOf(shanga.get(i).getStock_trading_value()));
				x.add(String.valueOf(shanga.get(i).getStock_yesterdayfinish_price()));
				x.add(String.valueOf(shanga.get(i).getStock_todaystart_price()));
				x.add(String.valueOf(shanga.get(i).getStock_max_price()));
				x.add(String.valueOf(shanga.get(i).getStock_min_price()));
				shangaData.add(x);
			}
			//上证B
			List<StockModel> shangb = new ArrayList<StockModel>();
			shangb = dataservice.getAllStocks("stock_shangb", date);
			List<List<String>> shangbData = new ArrayList<List<String>>();
			for(int i = 0;i<shangb.size();i++){
				List<String> x = new ArrayList<String>();
				x.add(shangb.get(i).getStock_id());
				x.add(shangb.get(i).getStock_name());
				x.add(String.valueOf(shangb.get(i).getStock_price()));
				x.add(String.valueOf(shangb.get(i).getStock_change()));
				x.add(String.valueOf(shangb.get(i).getStock_range()));
				x.add(String.valueOf(shangb.get(i).getStock_trading_number()));
				x.add(String.valueOf(shangb.get(i).getStock_trading_value()));
				x.add(String.valueOf(shangb.get(i).getStock_yesterdayfinish_price()));
				x.add(String.valueOf(shangb.get(i).getStock_todaystart_price()));
				x.add(String.valueOf(shangb.get(i).getStock_max_price()));
				x.add(String.valueOf(shangb.get(i).getStock_min_price()));
				shangbData.add(x);
			}
			//深证A
			List<StockModel> shena= new ArrayList<StockModel>();
			shena = dataservice.getAllStocks("stock_shena", date);
			List<List<String>> shenaData = new ArrayList<List<String>>();
			for(int i = 0;i<shena.size();i++){
				List<String> x = new ArrayList<String>();
				x.add(shena.get(i).getStock_id());
				x.add(shena.get(i).getStock_name());
				x.add(String.valueOf(shena.get(i).getStock_price()));
				x.add(String.valueOf(shena.get(i).getStock_change()));
				x.add(String.valueOf(shena.get(i).getStock_range()));
				x.add(String.valueOf(shena.get(i).getStock_trading_number()));
				x.add(String.valueOf(shena.get(i).getStock_trading_value()));
				x.add(String.valueOf(shena.get(i).getStock_yesterdayfinish_price()));
				x.add(String.valueOf(shena.get(i).getStock_todaystart_price()));
				x.add(String.valueOf(shena.get(i).getStock_max_price()));
				x.add(String.valueOf(shena.get(i).getStock_min_price()));
				shenaData.add(x);
			}
			//深证B
			List<StockModel> shenb= new ArrayList<StockModel>();
			shenb = dataservice.getAllStocks("stock_shenb", date);
			List<List<String>> shenbData = new ArrayList<List<String>>();
			for(int i = 0;i<shenb.size();i++){
				List<String> x = new ArrayList<String>();
				x.add(shenb.get(i).getStock_id());
				x.add(shenb.get(i).getStock_name());
				x.add(String.valueOf(shenb.get(i).getStock_price()));
				x.add(String.valueOf(shenb.get(i).getStock_change()));
				x.add(String.valueOf(shenb.get(i).getStock_range()));
				x.add(String.valueOf(shenb.get(i).getStock_trading_number()));
				x.add(String.valueOf(shenb.get(i).getStock_trading_value()));
				x.add(String.valueOf(shenb.get(i).getStock_yesterdayfinish_price()));
				x.add(String.valueOf(shenb.get(i).getStock_todaystart_price()));
				x.add(String.valueOf(shenb.get(i).getStock_max_price()));
				x.add(String.valueOf(shenb.get(i).getStock_min_price()));
				shenbData.add(x);
			}
			//上海指数
			List<StockModel> shanghai_zhishu= new ArrayList<StockModel>();
			shanghai_zhishu = dataservice.getAllStocks("stock_shanghai_zhishu", date);
			List<List<String>> shanghai_zhishuData = new ArrayList<List<String>>();
			for(int i = 0;i<shanghai_zhishu.size();i++){
				List<String> x = new ArrayList<String>();
				x.add(shanghai_zhishu.get(i).getStock_id());
				x.add(shanghai_zhishu.get(i).getStock_name());
				x.add(String.valueOf(shanghai_zhishu.get(i).getStock_price()));
				x.add(String.valueOf(shanghai_zhishu.get(i).getStock_change()));
				x.add(String.valueOf(shanghai_zhishu.get(i).getStock_range()));
				x.add(String.valueOf(shanghai_zhishu.get(i).getStock_trading_number()));
				x.add(String.valueOf(shanghai_zhishu.get(i).getStock_trading_value()));
				x.add(String.valueOf(shanghai_zhishu.get(i).getStock_yesterdayfinish_price()));
				x.add(String.valueOf(shanghai_zhishu.get(i).getStock_todaystart_price()));
				x.add(String.valueOf(shanghai_zhishu.get(i).getStock_max_price()));
				x.add(String.valueOf(shanghai_zhishu.get(i).getStock_min_price()));
				shanghai_zhishuData.add(x);
			}
			//深圳指数
			List<StockModel> shenzhen_zhishu= new ArrayList<StockModel>();
			shenzhen_zhishu = dataservice.getAllStocks("stock_shenzhen_zhishu", date);
			List<List<String>> shenzhen_zhishuData = new ArrayList<List<String>>();
			for(int i = 0;i<shenzhen_zhishu.size();i++){
				List<String> x = new ArrayList<String>();
				x.add(shenzhen_zhishu.get(i).getStock_id());
				x.add(shenzhen_zhishu.get(i).getStock_name());
				x.add(String.valueOf(shenzhen_zhishu.get(i).getStock_price()));
				x.add(String.valueOf(shenzhen_zhishu.get(i).getStock_change()));
				x.add(String.valueOf(shenzhen_zhishu.get(i).getStock_range()));
				x.add(String.valueOf(shenzhen_zhishu.get(i).getStock_trading_number()));
				x.add(String.valueOf(shenzhen_zhishu.get(i).getStock_trading_value()));
				x.add(String.valueOf(shenzhen_zhishu.get(i).getStock_yesterdayfinish_price()));
				x.add(String.valueOf(shenzhen_zhishu.get(i).getStock_todaystart_price()));
				x.add(String.valueOf(shenzhen_zhishu.get(i).getStock_max_price()));
				x.add(String.valueOf(shenzhen_zhishu.get(i).getStock_min_price()));
				shenzhen_zhishuData.add(x);
			}
			//创业板
			List<StockModel> chuangye= new ArrayList<StockModel>();
			chuangye = dataservice.getAllStocks("stock_chuangye", date);
			List<List<String>> chuangyeData = new ArrayList<List<String>>();
			for(int i = 0;i<chuangye.size();i++){
				List<String> x = new ArrayList<String>();
				x.add(chuangye.get(i).getStock_id());
				x.add(chuangye.get(i).getStock_name());
				x.add(String.valueOf(chuangye.get(i).getStock_price()));
				x.add(String.valueOf(chuangye.get(i).getStock_change()));
				x.add(String.valueOf(chuangye.get(i).getStock_range()));
				x.add(String.valueOf(chuangye.get(i).getStock_trading_number()));
				x.add(String.valueOf(chuangye.get(i).getStock_trading_value()));
				x.add(String.valueOf(chuangye.get(i).getStock_yesterdayfinish_price()));
				x.add(String.valueOf(chuangye.get(i).getStock_todaystart_price()));
				x.add(String.valueOf(chuangye.get(i).getStock_max_price()));
				x.add(String.valueOf(chuangye.get(i).getStock_min_price()));
				chuangyeData.add(x);
			}
			//中小板
			List<StockModel> zhongxiao= new ArrayList<StockModel>();
			zhongxiao = dataservice.getAllStocks("stock_zhongxiao", date);
			List<List<String>> zhongxiaoData = new ArrayList<List<String>>();
			for(int i = 0;i<zhongxiao.size();i++){
				List<String> x = new ArrayList<String>();
				x.add(zhongxiao.get(i).getStock_id());
				x.add(zhongxiao.get(i).getStock_name());
				x.add(String.valueOf(zhongxiao.get(i).getStock_price()));
				x.add(String.valueOf(zhongxiao.get(i).getStock_change()));
				x.add(String.valueOf(zhongxiao.get(i).getStock_range()));
				x.add(String.valueOf(zhongxiao.get(i).getStock_trading_number()));
				x.add(String.valueOf(zhongxiao.get(i).getStock_trading_value()));
				x.add(String.valueOf(zhongxiao.get(i).getStock_yesterdayfinish_price()));
				x.add(String.valueOf(zhongxiao.get(i).getStock_todaystart_price()));
				x.add(String.valueOf(zhongxiao.get(i).getStock_max_price()));
				x.add(String.valueOf(zhongxiao.get(i).getStock_min_price()));
				zhongxiaoData.add(x);
			}
			//新股
			List<StockModel> xingu= new ArrayList<StockModel>();
			xingu = dataservice.getAllStocks("stock_xingu", date);
			List<List<String>> xinguData = new ArrayList<List<String>>();
			for(int i = 0;i<xingu.size();i++){
				List<String> x = new ArrayList<String>();
				x.add(xingu.get(i).getStock_id());
				x.add(xingu.get(i).getStock_name());
				x.add(String.valueOf(xingu.get(i).getStock_price()));
				x.add(String.valueOf(xingu.get(i).getStock_change()));
				x.add(String.valueOf(xingu.get(i).getStock_range()));
				x.add(String.valueOf(xingu.get(i).getStock_trading_number()));
				x.add(String.valueOf(xingu.get(i).getStock_trading_value()));
				x.add(String.valueOf(xingu.get(i).getStock_yesterdayfinish_price()));
				x.add(String.valueOf(xingu.get(i).getStock_todaystart_price()));
				x.add(String.valueOf(xingu.get(i).getStock_max_price()));
				x.add(String.valueOf(xingu.get(i).getStock_min_price()));
				xinguData.add(x);
			}
			//龙虎榜
			List<TigerModel> dragon= new ArrayList<TigerModel>();
			dragon = dataservice.getTigerStyle();
			List<List<String>> dragonData = new ArrayList<List<String>>();
			for(int i = 0;i<dragon.size();i++){
				List<String> x = new ArrayList<String>();
				x.add(dragon.get(i).getCode());
				x.add(dragon.get(i).getName());
				x.add(dragon.get(i).getDate());
				x.add(dragon.get(i).getStyle());
				x.add(dragon.get(i).getStart_time());
				x.add(dragon.get(i).getEnd_time());
				x.add(dragon.get(i).getDays());
				x.add(dragon.get(i).getAmount());
				x.add(dragon.get(i).getValue());
				x.add(dragon.get(i).getStockchange());
				dragonData.add(x);
			}
			session.setAttribute("hushenData", hushenData);
			session.setAttribute("shangaData", shangaData);
			session.setAttribute("shangbData", shangbData);
			session.setAttribute("shenaData", shenaData);
			session.setAttribute("shenbData", shenaData);
			session.setAttribute("shanghai_zhishu", shanghai_zhishuData);
			session.setAttribute("shenzhen_zhishu", shenzhen_zhishuData);
			session.setAttribute("chuangye", chuangyeData);
			session.setAttribute("zhongxiao", zhongxiaoData);
			session.setAttribute("xingu", xinguData);
			session.setAttribute("dragon", dragonData);
			
			session.setAttribute("shangres", shangres);
		    session.setAttribute("shenres", shenres);
		    return "index.jsp";
	    }else if(user==null){
	    	System.out.println("用户不存在!");
	    	session.setAttribute("status", "用户不存在！");
	    }else{
	    	System.out.println("用户名或密码错误！");
	    	session.setAttribute("status", "用户名或密码错误！");
	    }
		return "login.jsp";
	}
	
	@RequestMapping("logout.spring")
	public String logout(HttpSession session){
		session.removeAttribute("email");
		session.removeAttribute("status");
		session.removeAttribute("History");
		session.removeAttribute("Person");
		session.removeAttribute("regstatus");
		
		return "index.jsp";
	}
	
	public double[][] Markov(){
		List<ZhishuModel> pointstemp = dataservice.getDuringShangzhi("2016-03-01", "2017-06-08");            //调用数据库的方法
		
		List<Double> points = new ArrayList<Double>();
		for(ZhishuModel temp:pointstemp){
			points.add(Double.parseDouble(temp.getB()));
			
		}
		
		int[][] count = new int[4][4];       //设定四个区间为：<3080、3081~3160、3161~3240、大于3241
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				count[i][j]=0;
			}
		}
		for(int i=0;i<points.size()-1;i++){
			if(points.get(i)<3081&&points.get(i+1)<3081){
				count[0][0]+=1;
			}
			if(points.get(i)<3081&&points.get(i+1)<3161&&points.get(i+1)>=3081){
				count[0][1]+=1;
			}
			if(points.get(i)<3081&&points.get(i+1)<3241&&points.get(i+1)>=3161){
				count[0][2]+=1;
			}
			if(points.get(i)<3081&&points.get(i+1)>=3241){
				count[0][3]+=1;
			}
			if(points.get(i)<3161&&points.get(i)>=3081&&points.get(i+1)<3081){
				count[1][0]+=1;
			}
			if(points.get(i)<3161&&points.get(i)>=3081&&points.get(i+1)<3161&&points.get(i+1)>=3081){
				count[1][1]+=1;
			}
			if(points.get(i)<3161&&points.get(i)>=3081&&points.get(i+1)<3241&&points.get(i+1)>=3161){
				count[1][2]+=1;
			}
			if(points.get(i)<3161&&points.get(i)>=3081&&points.get(i+1)>=3241){
				count[1][3]+=1;
			}
			if(points.get(i)<3241&&points.get(i)>=3161&&points.get(i+1)<3081){
				count[2][0]+=1;
			}
			if(points.get(i)<3241&&points.get(i)>=3161&&points.get(i+1)<3161&&points.get(i+1)>=3081){
				count[2][1]+=1;
			}
			if(points.get(i)<3241&&points.get(i)>=3161&&points.get(i+1)<3241&&points.get(i+1)>=3161){
				count[2][2]+=1;
			}
			if(points.get(i)<3241&&points.get(i)>=3161&&points.get(i+1)>=3241){
				count[2][3]+=1;
			}
			if(points.get(i)<3321&&points.get(i)>=3241&&points.get(i+1)<3081){
				count[3][0]+=1;
			}
			if(points.get(i)<3321&&points.get(i)>=3241&&points.get(i+1)<3161&&points.get(i+1)>=3081){
				count[3][1]+=1;
			}
			if(points.get(i)<3321&&points.get(i)>=3241&&points.get(i+1)<3241&&points.get(i+1)>=3161){
				count[3][2]+=1;
			}
			if(points.get(i)<3321&&points.get(i)>=3241&&points.get(i+1)>=3241){
				count[3][3]+=1;
			}
		}
		
		double[][] Marcov = new double[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				Marcov[i][j]= (double)(count[i][j]) / (double)(count[i][0]+count[i][1]+count[i][2]+count[i][3]);
			}
		}
		
		return Marcov;
		
	}
	public List<Double> cal(List<Double> today,double[][] Marcov){
		DecimalFormat df = new DecimalFormat("#.0000");

		List<Double> res = new ArrayList<Double>();
		double[] temp = new double[4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				temp[i]+=today.get(j)*Marcov[j][i];
			}
		}
		for(int i=0;i<4;i++){
			res.add(Double.parseDouble(df.format(temp[i])));
		}
		return res;
	}

}
