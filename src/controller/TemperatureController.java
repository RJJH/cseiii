package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dataimpl;
import data.dataservice;
import model.AllYearsStockModel;

@Controller
public class TemperatureController {
	dataservice dataservice=new dataimpl();
	
	private List<AllYearsStockModel> list;
	
	private long sum;			//交易总量
	private int upTop;			//涨停股票数
	private int lowDown;		//跌停股票数
	private int upAbove;		//涨幅大于5%
	private int lowBelow;		//跌幅大于5%
	private int upBetween;		//开盘-收盘大于5%
	private int downBetween;	//开盘-收盘小于5%
	
	@RequestMapping("temperature.spring")
	public String temperature(@RequestParam("date") String date,HttpSession session){
		Date now=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String nowDate=df.format(now);
		
		
		list=new ArrayList<AllYearsStockModel>();
		list=dataservice.getStockByDate(date);
		String before=getYesterday(date);
		List<AllYearsStockModel> list2=dataservice.getStockByDate(before);
		while(list2.size()==0){
			before=getYesterday(before);
			list2=dataservice.getStockByDate(before);
		}
		
		sum=0;				
		upTop=0;			
		lowDown=0;			
		upAbove=0;			
		lowBelow=0;		    
		upBetween=0;		
		downBetween=0;
		int length=list.size();
		
//		System.out.println(length+"a");
		for(int i=0;i<length;i++){
			AllYearsStockModel sm=new AllYearsStockModel();
			AllYearsStockModel sm2=new AllYearsStockModel();
			sm=list.get(i);
			sm2=list2.get(i);
//			System.out.println(sm.getClose());
			calculateData(sm,sm2);
			sum=sum+Long.parseLong(sm.getVolume());
			
		}
		session.setAttribute("sum", sum);
		session.setAttribute("upTop", upTop);
		session.setAttribute("lowDown", lowDown);
		session.setAttribute("upAbove", upAbove);
		session.setAttribute("lowBelow", lowBelow);
		session.setAttribute("upBetween", upBetween);
		session.setAttribute("downBetween", downBetween);
		
//		System.out.println(upTop);
		
		List<Integer> listData=new ArrayList<Integer>();
		listData.add(upTop);
		listData.add(lowDown);
		listData.add(upAbove);
		listData.add(lowBelow);
		listData.add(upBetween);
		listData.add(downBetween);
		session.setAttribute("listData", listData);
		
		return "temperature.jsp";
	}
	
	public String getYesterday(String date){
		Calendar c=Calendar.getInstance();
		Date d=null;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		try {
			d=df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(d);
		int day=c.get(Calendar.DATE);
		c.set(Calendar.DATE, day-1);
		String nextDay=df.format(c.getTime());
		return nextDay;
	}
	
	public void calculateData(AllYearsStockModel sm,AllYearsStockModel sm2){
		double daychange=(Double.parseDouble(sm.getClose())-Double.parseDouble(sm2.getClose()))/Double.parseDouble(sm2.getClose());
		double daychange2=(Double.parseDouble(sm.getOpen())-Double.parseDouble(sm.getClose()))/Double.parseDouble(sm.getOpen());
		if(daychange>=0.1){
			upTop++;
			upAbove++;
		}else if((daychange<0.1)&&(daychange>=0.05)){
			upAbove++;
		}else if(daychange<=-0.1){
			lowDown++;
			lowBelow++;
		}else if((daychange>-0.1)&&(daychange<=-0.05)){
			lowBelow++;
		}
		
		if(daychange2>0.05){
			upBetween++;
		}else if(daychange2<-0.05){
			downBetween++;
		}
	}
	
	

}
