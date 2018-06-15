package controller;

import java.util.ArrayList;
import java.util.List;

import data.dataimpl;
import data.dataservice;
import model.AllYearsStockModel;
import model.SimpleStockModel;
import model.StockModel;

public class SimpleStockGet {
	private dataservice DATA ;
	
	public SimpleStockGet(){
		DATA= new dataimpl();
	}

	
	public void main(String args[]){
		List<SimpleStockModel> a = new ArrayList<SimpleStockModel>();
		a = GetSharesInfoByDate("2017-05-04","2017-05-31","000001");
		System.out.println(a.size());
	}
	public List<SimpleStockModel> GetSharesInfoByDate(String start, String end, String nameORcode) {
		// TODO Auto-generated method stub
		

		List<AllYearsStockModel> list = new ArrayList<AllYearsStockModel>();

		if (isNumeric(nameORcode)){
			list = DATA.getStockByDuringAndStockID(start, end, nameORcode);
		}
		else{
			String code = DATA.getStockByStockName(nameORcode).get(0).getStock_id();
			list = DATA.getStockByDuringAndStockID(start, end, code);
		}
		if (list == null) {
			return null;
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

		List<SimpleStockModel> res = new ArrayList<SimpleStockModel>();

		for (int i = list.size() - 1; i >= 0; i--) {
			AllYearsStockModel t = list.get(i);
			res.add(new SimpleStockModel(t.getCode(), Double.parseDouble(list.get(i).getClose()),Long.parseLong(t.getVolume()),t.getDate()));
		}

		return res;
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
