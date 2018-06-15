package controller;

import java.text.DecimalFormat;
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

import model.AllYearsStockModel;
import model.PersonStockModel;
import model.StockModel;
import data.dataimpl;
import data.dataservice;

@Controller
public class MomDrivenController2 {
	dataservice dataservice=new dataimpl();
	
	private List<AllYearsStockModel> listCode;
	
	private List<AllYearsStockModel> list;
	
	private List<List<AllYearsStockModel>> listFull;
	
	private List<Double> earn;
	
	private List<Double> staEarn;
	
	@RequestMapping("strategy_2_1.spring")
	public String momDriven2(@RequestParam("begin") String begin,@RequestParam("end") String end,
			@RequestParam("stockpool") String style,HttpSession session){
		//固定形成期
		listCode=new ArrayList<AllYearsStockModel>();
		list=new ArrayList<AllYearsStockModel>();
		listFull=new ArrayList<List<AllYearsStockModel>>();
		staEarn=new ArrayList<Double>();
		earn=new ArrayList<Double>();
		List<Double> over=new ArrayList<Double>();
		List<Double> win=new ArrayList<Double>();
		List<Integer> zhouqi=new ArrayList<Integer>();
		
		//修正日期定义形成期为20天
		int xing=20;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String start="";
		try {
			Date d=df.parse(begin);
			start=calDate(d,-xing);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(style.equals("0")){
			style="stock_shang";
		}else if(style.equals("1")){
			style="stock_shen";
		}else if(style.equals("2")){
			style="stock_chuangye";
		}else if(style.equals("3")){
			style="自选股";
		}
		
		//读数据
		int earnsize=0;
		int size=0;
		if(style.equals("自选股")){		
			List<PersonStockModel> listC=dataservice.getPersonByUserID(String.valueOf(session.getAttribute("email")).split("@")[0]);
			list=dataservice.getStockByDuringAndStockID(begin, end, listC.get(0).getStock_id());
			earnsize=list.size();
			list=dataservice.getStockByDuringAndStockID(start, end, listC.get(0).getStock_id());
			size=list.size();
			
			for(int i=0;i<listC.size();i++){
				list=dataservice.getStockByDuringAndStockID(start, end, listC.get(i).getStock_id());
				int minsize=list.size();
				if(minsize<size){
					size=minsize;
				}
				listFull.add(list);
				list=dataservice.getStockByDuringAndStockID(begin, end, listC.get(i).getStock_id());
				int minsize2=list.size();
				if(minsize2<earnsize){
					earnsize=minsize2;
				}			
			}
		}else{
			listCode=dataservice.getStockByStyle(style, begin);			
			list=dataservice.getStockByDuringAndStockID(begin, end, listCode.get(0).getCode());
			earnsize=list.size();
			list=dataservice.getStockByDuringAndStockID(start, end, listCode.get(0).getCode());
			size=list.size();
			
			for(int i=0;i<listCode.size();i++){
				list=dataservice.getStockByDuringAndStockID(start, end, listCode.get(i).getCode());
				int size2=list.size();
				if(size2>=size){
					listFull.add(list);
				}
			}
		}
		
		int days=size-1;
		DecimalFormat dff=new DecimalFormat("#.000");
		
		//基准结果
		double yes2=0;
		for(int i=size-earnsize;i<days;i++){
			double day2=0;
			for(int j=0;j<listFull.size();j++){
	    		list=listFull.get(j);
	    		day2=(Double.parseDouble(list.get(i+1).getClose())-Double.parseDouble(list.get(i).getClose()))/Double.parseDouble(list.get(i).getClose())+day2;
	    	}
			day2=day2/listFull.size()+yes2;
			String result=dff.format(day2);
			day2=Double.parseDouble(result);
			staEarn.add(day2);
			yes2=day2;
		}
		
		double st=staEarn.get(staEarn.size()-1);
		
		for(int chi=5;chi<=60;chi=chi+5){
//		System.out.println(chi+"a");
		double temp=0;
		int min=0;
		int max=0;
		int earnzero=size-earnsize;
		earn=new ArrayList<Double>();
		while(min<days&&max<days){
			//形成期
			List<Double> money=new ArrayList<Double>();
			
			if((min+xing)<=days){
			    max=min+xing;
			}else{
			    max=days;
			}
			
			for(int i=0;i<listFull.size();i++){
	    		list=listFull.get(i);
	    		double result=(Double.parseDouble(list.get(max).getClose())-Double.parseDouble(list.get(min).getClose()))/Double.parseDouble(list.get(min).getClose());
	    		String str=dff.format(result);
	    		result=Double.parseDouble(str);
	    		money.add(result);
	    	}
			
			//计算排名前列的
			List<Integer> top=new ArrayList<Integer>();

			for(int i=0;i<(listFull.size()/5);i++){
			    double result=0;
			    int aim=0;
			    int j=0;
			    for(j=0;j<money.size()-i;j++){
			    	if(result<=money.get(j))
			    		aim=j;
			    }
			    top.add(aim);
			    money.remove(aim);
			}
			//持有期
	    	double yes=0;
	    	int shang=earnzero+chi;
	    	if(shang>days){
	    		shang=days;
	    	}
	    	for(int j=earnzero;j<shang;j++){
	    		double day=0;
		    	for(int i=0;i<top.size();i++){
		    		int code=top.get(i);
		    		list=listFull.get(code);		    		
		    		day=(Double.parseDouble(list.get(j+1).getClose())-Double.parseDouble(list.get(j).getClose()))/Double.parseDouble(list.get(j).getClose())+day;
		    				    		
		    	}	    	
		    	day=day/top.size()+yes;		    	
				String result=dff.format(day);
				day=Double.parseDouble(result);
//				System.out.println(day+"w");
		    	earn.add(day);
		    	yes=day;
	    	}
	    	earnzero=shang;
			    	
			min=max+chi-xing;
			    	
		}
		double mom=earn.get(earn.size()-1);
//		System.out.println(mom);
		String result=dff.format(mom-st);
		over.add(Double.parseDouble(result));
		
		int length=0;
		if(earn.size()<=staEarn.size()){
			length=earn.size();
		}else{
			length=staEarn.size();
					
		}
		
		for(int j=0;j<length;j++){
			if(earn.get(j)>=staEarn.get(j)){
				temp++;
			}
		}
		double zong=staEarn.size();
		result=dff.format(temp/zong);
		win.add(Double.parseDouble(result));
		zhouqi.add(chi);
		}
//		for(int i=0;i<12;i++){
//			System.out.println(win.get(i));
//		}	
//		System.out.println(over.size());
		session.setAttribute("over", over);
		session.setAttribute("win", win);
		session.setAttribute("zhouqi", zhouqi);
		
		return "strategy_2_1.jsp";
	}
	
	public String calDate(Date d,int day){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, day);
		String date=df.format(c.getTime());
		return date;
	}
	
	public int countDate(String date1,String date2){
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
			
			c1.set(Calendar.HOUR_OF_DAY, 0);
			c1.set(Calendar.MINUTE,0);
			c1.set(Calendar.SECOND, 0);
			c2.set(Calendar.HOUR_OF_DAY, 0);
			c2.set(Calendar.MINUTE,0);
			c2.set(Calendar.SECOND, 0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int days=(int) (((c1.getTime().getTime()/1000)-(c2.getTime().getTime()/1000))/3600/24);
		return days;
	}

}
