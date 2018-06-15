package controller;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dataimpl;
import data.dataservice;
import model.AllYearsStockModel;
import model.HistoryStockModel;
import model.PersonStockModel;
import model.StockModel;

@Controller
public class SearchController {
	dataservice dataservice = new dataimpl();
	
	@RequestMapping("searchStock.spring")
	public String searchStock(@RequestParam("stock_info") String stockInfo, ModelMap map, HttpSession session){
		List<StockModel> temp = new ArrayList<StockModel>();         //获取所有的信息
		List<AllYearsStockModel> Kstat = new ArrayList<AllYearsStockModel>();        //获取一年内的信息
		List<HistoryStockModel> historytemp = new ArrayList<HistoryStockModel>();   //获取该用户的历史记录信息
		List<List<String>> historyData = new ArrayList<List<String>>();         //将要传到服务器的历史信息
		List<PersonStockModel> persontemp = new ArrayList<PersonStockModel>();  //获取该用户的自选股票信息
		List<List<String>> personData = new ArrayList<List<String>>();          //传到服务器的历史信息

		
		NumberFormat num = NumberFormat.getPercentInstance(); //百分数转化工具
		num.setMaximumIntegerDigits(3); 
		num.setMaximumFractionDigits(2); 
		
		if(stockInfo.length()>7){
			
			String search = stockInfo.split("\\(")[1].substring(0,6);
			temp = dataservice.getStockByStockID(search);
			Kstat = dataservice.getStockByDuringAndStockID("2005-02-21", "2017-06-14", search);
			
		}else if(isNumeric(stockInfo)){                                 //判断输入格式，调用不同的方法读取数据
			temp = dataservice.getStockByStockID(stockInfo);
			Kstat = dataservice.getStockByDuringAndStockID("2005-02-21", "2017-06-14", stockInfo);
		}else{
			temp = dataservice.getStockByStockName(stockInfo);
			Kstat = dataservice.getStockByDuringAndStockID("2005-02-21", "2017-06-14", temp.get(0).getStock_id());
		}
        

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		StockModel res = new StockModel();   //返回的当天的股票信息，需要进一步解析
		HistoryStockModel historymodel = new HistoryStockModel(); //加入历史记录的股票model
		
		//获取当天股票信息
		for(StockModel model:temp){

			if(model.getDate().equals("2017-06-14")){
				res = model;
				break;
			}
		}
		
		
		String[][] GSXW = dataservice.getStockGSXWByStockID(res.getStock_id());    //公司的新闻与公告
		String[][] GSGG = dataservice.getStockGSGGByStockID(res.getStock_id());
		
		List<List<String>> gsxw = new ArrayList<List<String>>();
		List<List<String>> gsgg = new ArrayList<List<String>>();
		
		for(int i=0;i<8;i++){
			List<String> xwtemp = new ArrayList<String>();
			List<String> ggtemp = new ArrayList<String>();
			for(int j=0;j<3;j++){
				xwtemp.add(GSXW[i][j]);
				ggtemp.add(GSGG[i][j]);
			}
			gsxw.add(xwtemp);
			gsgg.add(ggtemp);
		}
		//当用户登录状态时加入历史记录
		if(session.getAttribute("email")!=null){
		String username = session.getAttribute("email").toString().split("@")[0];
		String craw_time = df.format(new Date());
		historymodel.setCraw_time(craw_time);
		historymodel.setUser_id(username);
		historymodel.setStock_id(res.getStock_id());
		historymodel.setStock_name(res.getStock_name());
		dataservice.inserthistorystock(historymodel);
		historytemp = dataservice.getHistoryByUserID(username);
		for(HistoryStockModel model:historytemp){
		     List<String> temp11 = new ArrayList<String>();
		     temp11.add(model.getStock_name());
		     temp11.add(model.getStock_id());
		     String[] timetemp = model.getCraw_time().split("-");
		     temp11.add(timetemp[1]+"-"+timetemp[2]);
		     historyData.add(temp11);
		}
		
		persontemp = dataservice.getPersonByUserID(username);
		for(PersonStockModel model:persontemp){
			List<String> temp12 = new ArrayList<String>();
			temp12.add(model.getStock_name());
			temp12.add(model.getStock_id());
			
			personData.add(temp12);
		}
		
		}
		
		List<String> time = new ArrayList<String>();
		
	    for(AllYearsStockModel timemodel:Kstat){
	    	String time0 = timemodel.getDate();
	    	String[] tempformat = time0.split("-");
	    	
	    	time0 = "'"+tempformat[0]+"'"+"+"+"'-'"+"+"+"'"+tempformat[1]+"'"+"+"+"'-'"+"+"+"'"+tempformat[2]+"'";
	    	time.add(time0);
	    }
	   

		
		//要存入图表的信息——均线
		List<Double> AverageLine = new ArrayList<Double>();     //传入页面的均线数据
		List<Double> AverageLine5 = new ArrayList<Double>();     //传入页面的均线数据
		List<Double> AverageLine10 = new ArrayList<Double>();     //传入页面的均线数据
		List<Double> AverageLine30 = new ArrayList<Double>();     //传入页面的均线数据
		for(int a=0;a<Kstat.size();a++){
			double temp_1 = 0.0;
			temp_1 = Double.parseDouble(Kstat.get(a).getClose());
			AverageLine.add(temp_1);
		}
		for(int b=4;b<Kstat.size();b++){
			double temp_5=0.0;
			double sum = 0.0;
			for(int i=0;i<5;i++){
				sum+=Double.parseDouble(Kstat.get(b-i).getClose());
			}
			temp_5=sum/5;
			AverageLine5.add(temp_5);
			
		}
		for(int b=9;b<Kstat.size();b++){
			double temp_10=0.0;
			double sum = 0.0;
			for(int i=0;i<10;i++){
				sum+=Double.parseDouble(Kstat.get(b-i).getClose());
			}
			temp_10=sum/10;
			AverageLine10.add(temp_10);
			
		}
		for(int b=29;b<Kstat.size();b++){
			double temp_30=0.0;
			double sum = 0.0;
			for(int i=0;i<30;i++){
				sum+=Double.parseDouble(Kstat.get(b-i).getClose());
			}
			temp_30=sum/30;
			AverageLine30.add(temp_30);
		}
		
		//要存入图表的信息——K线
		
		List<List<Double>> Kline = new ArrayList<List<Double>>();
		for(AllYearsStockModel allmodel:Kstat){
			List<Double> tempk = new ArrayList<Double>();
			tempk.add(Double.parseDouble(allmodel.getOpen()));//开盘价
			tempk.add(Double.parseDouble(allmodel.getClose()));//收盘价
			tempk.add(Double.parseDouble(allmodel.getLow()));//最低价
			tempk.add(Double.parseDouble(allmodel.getHigh()));//最高价
			Kline.add(tempk);
		}
		
//要存入服务器的个股信息
		float range = res.getStock_range();
		float amplitude = res.getStock_amplitude();
		String rangetemp = num.format(range);
		String amptemp = num.format(amplitude);
		
		session.setAttribute("date", res.getDate());
		session.setAttribute("stock_name", res.getStock_name());
		session.setAttribute("stock_id", res.getStock_id());
		session.setAttribute("volume", res.getStock_trading_value());
		session.setAttribute("open", res.getStock_todaystart_price());
		session.setAttribute("finish",res.getStock_yesterdayfinish_price());
		session.setAttribute("amplitude", amptemp);//振幅
		session.setAttribute("price",res.getStock_price());
		session.setAttribute("change",res.getStock_change());//涨跌额
		session.setAttribute("max_price",res.getStock_max_price());
		session.setAttribute("min_price", res.getStock_min_price());
		session.setAttribute("range", rangetemp);
		session.setAttribute("cjl", res.getStock_trading_number());
		session.setAttribute("5min", res.getStock_fiveminuate_change());
		session.setAttribute("info_list",time);
		session.setAttribute("Kline", Kline);
		session.setAttribute("History",historyData);
		session.setAttribute("Person", personData);
		session.setAttribute("AverageLine", AverageLine);
		session.setAttribute("AverageLine5", AverageLine5);
		session.setAttribute("AverageLine10", AverageLine10);
		session.setAttribute("AverageLine30", AverageLine30);
		session.setAttribute("gsxw", gsxw);
		session.setAttribute("gsgg", gsgg);
		
		return "singlestock.jsp";
		
	}
	@RequestMapping("delown.spring")
	public String delown(@RequestParam("tobedel")String delstock,HttpSession s){
		String user_id = String.valueOf(s.getAttribute("email")).split("@")[0];
		String stock_id = delstock;
		dataservice.deletpersonstock(user_id, stock_id);
		List<PersonStockModel> persontemp = dataservice.getPersonByUserID(user_id);
		List<List<String>> personData = new ArrayList<List<String>>();
		for(PersonStockModel model1:persontemp){
			List<String> temp12 = new ArrayList<String>();
			temp12.add(model1.getStock_name());
			temp12.add(model1.getStock_id());
			personData.add(temp12);
		}
		s.setAttribute("Person", personData);
		
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
