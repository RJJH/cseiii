package navi.main;

import java.util.ArrayList;
import java.util.List;

import data.dataservice;
import data.dataimpl;
import model.AllYearsStockModel;
import model.SimpleStockModel;
import model.StockModel;
import model.TigerModel;
import model.UserModel;
import model.ZhishuModel;


public class test {
	static dataservice data = new dataimpl() ;
	public static void main(String[] args) {
		dataservice data = new dataimpl() ;
//		String style = "stock_shanga";
//		String stock_date = "2017-05-24";

		String stock_id = "000001";
		String start_time = "2016-05-31";
		String end_time = "2016-06-24";

		List<UserModel> a = new ArrayList<UserModel>();
		UserModel b = new UserModel();
		b.setUser_name("1234");
		b.setUser_id("1111");
		b.setUser_password("111");
		a.add(b);
     
//		a=data.getStockByStockID(stock_id);
	//a=data.getStockByStartAndStockID("2017-05-31", stock_id);
		//for(int i = 0;i<a.size();i++){
//		List<List<String>> hushenData = new ArrayList<List<String>>();
//		for(int i = 0;i<a.size();i++){
//			List<String> x = new ArrayList<String>();
//			x.add(a.get(i).getStock_id());
//			x.add(a.get(i).getStock_name());
//			x.add(String.valueOf(a.get(i).getStock_price()));
//			x.add(String.valueOf(a.get(i).getStock_change()));
//			x.add(String.valueOf(a.get(i).getStock_range()));
//			x.add(String.valueOf(a.get(i).getStock_trading_number()));
//			x.add(String.valueOf(a.get(i).getStock_trading_value()));
//			x.add(String.valueOf(a.get(i).getStock_yesterdayfinish_price()));
//			x.add(String.valueOf(a.get(i).getStock_todaystart_price()));
//			x.add(String.valueOf(a.get(i).getStock_max_price()));
//			x.add(String.valueOf(a.get(i).getStock_min_price()));
//			hushenData.add(x);
//		}

//		System.out.println(a.get(0).getHigh());

		//}
//		System.exit(0);
	}
	
	
}
//		String[] startdate = start.split("-");
//		String[] enddate = end.split("-");
//		int Sta = (Integer.parseInt(startdate[0]) % 100) * 10000 + Integer.parseInt(startdate[1]) * 100
//				+ Integer.parseInt(startdate[2]);
//		int End = (Integer.parseInt(enddate[0]) % 100) * 10000 + Integer.parseInt(enddate[1]) * 100
//				+ Integer.parseInt(enddate[2]);
//		for (int i = 0; i < list.size(); i++) {
//			String tempdate = list.get(i).getDate();
//			String temp[] = tempdate.split("-");
//			int timecount = 10000 * Integer.parseInt(temp[0]) + Integer.parseInt(temp[1]) * 100
//					+ Integer.parseInt(temp[2]);
//			if (!(timecount <= End && timecount >= Sta))
//				list.remove(i--);
//		}

