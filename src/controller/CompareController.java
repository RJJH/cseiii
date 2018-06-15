package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dataimpl;
import data.dataservice;
import model.AllYearsStockModel;
import model.StockModel;
@Controller
public class CompareController {
	dataservice dataservice=new dataimpl();
	
	@RequestMapping("compare.spring")
	public String compare(@RequestParam("comstocks") String comstocks,@RequestParam("begin") String begin,
			@RequestParam("end")String end,HttpSession session){
		String[] stock=comstocks.split(",");
		List<AllYearsStockModel> list=new ArrayList<AllYearsStockModel>();
		List<String> date=new ArrayList<String>();
		List<String> code=new ArrayList<String>();
		DecimalFormat dff=new DecimalFormat("#.000");
		
		String[] title=new String[6];
		for(int i=0;i<6;i++){
			title[i]="";
		}
		double[] min=new double[6];
		double[] max=new double[6];
		double[] fudu=new double[6];
		double[] get=new double[6];
		for(int i=1;i<stock.length;i++){
			String name="";
			if(stock[i].length()>6){
				String[] s=stock[i].split("\\(");
				name=s[1].substring(0, 6);
			}else{
				name=stock[i];
			}
//			System.out.println(name);
			
			list=dataservice.getStockByDuringAndStockID(begin,end,name);
			min[i]=10000;
			max[i]=0;
			fudu[i]=0;
			get[i]=0;
			for(int j=0;j<list.size();j++){
				if(min[i]>Double.parseDouble(list.get(j).getLow())){
					min[i]=Double.parseDouble(list.get(j).getLow());
				}
				
				if(max[i]<Double.parseDouble(list.get(j).getHigh())){
					max[i]=Double.parseDouble(list.get(j).getHigh());
				}
				
			}
			
			fudu[i]=(Double.parseDouble(list.get(list.size()-1).getClose())-Double.parseDouble(list.get(0).getClose()))/Double.parseDouble(list.get(0).getClose());
			String result=dff.format(fudu[i]);
			fudu[i]=Double.parseDouble(result);
			
			if(fudu[i]>0){
				get[i]=Math.log(fudu[i]);
				result=dff.format(get[i]);
				get[i]=Double.parseDouble(result);
			}
			

			title[i]=name;
			code.add(name);
		}
		for(int i=7;i>0;i--){
			String[] tempformat=list.get(list.size()-i).getDate().split("-");
	    	String time0 = "'"+tempformat[0]+"'"+"+"+"'-'"+"+"+"'"+tempformat[1]+"'"+"+"+"'-'"+"+"+"'"+tempformat[2]+"'";
	    	date.add(time0);
		}
		
		List<String> data1=new ArrayList<String>();
		List<String> data2=new ArrayList<String>();
		List<String> data3=new ArrayList<String>();
		List<String> data4=new ArrayList<String>();
		List<String> data5=new ArrayList<String>();
        
		if(!(title[1].equals(""))){
			list=dataservice.getStockByDuringAndStockID(begin,end,title[1]);
			for(int i=7;i>0;i--){
		    	data1.add(list.get(list.size()-i).getClose());
			}
		}
		
		if(!(title[2].equals(""))){
		list=dataservice.getStockByDuringAndStockID(begin,end,title[2]);
		for(int i=7;i>0;i--){
	    	data2.add(list.get(list.size()-i).getClose());
		}
		}
		if(!(title[3].equals(""))){
		list=dataservice.getStockByDuringAndStockID(begin,end,title[3]);
		for(int i=7;i>0;i--){
	    	data3.add(list.get(list.size()-i).getClose());
		}
		}
		if(!(title[4].equals(""))){
		list=dataservice.getStockByDuringAndStockID(begin,end,title[4]);
		for(int i=7;i>0;i--){
	    	data4.add(list.get(list.size()-i).getClose());
		}
		}
		if(!(title[5].equals(""))){
		list=dataservice.getStockByDuringAndStockID(begin,end,title[5]);
		for(int i=7;i>0;i--){
	    	data5.add(list.get(list.size()-i).getClose());
		}
		}
		
//		System.out.println(date.get(0));
		session.setAttribute("code", code);
		session.setAttribute("min[1]", min[1]);
		session.setAttribute("max[1]", max[1]);
		session.setAttribute("get[1]", get[1]);
		session.setAttribute("fudu[1]", fudu[1]);
		session.setAttribute("title[1]", title[1]);
		session.setAttribute("min[2]", min[2]);
		session.setAttribute("max[2]", max[2]);
		session.setAttribute("get[2]", get[2]);
		session.setAttribute("fudu[2]", fudu[2]);
		session.setAttribute("title[2]", title[2]);
		session.setAttribute("min[3]", min[3]);
		session.setAttribute("max[3]", max[3]);
		session.setAttribute("get[3]", get[3]);
		session.setAttribute("fudu[3]", fudu[3]);
		session.setAttribute("title[3]", title[3]);
		session.setAttribute("min[4]", min[4]);
		session.setAttribute("max[4]", max[4]);
		session.setAttribute("get[4]", get[4]);
		session.setAttribute("fudu[4]", fudu[4]);
		session.setAttribute("title[4]", title[4]);
		session.setAttribute("min[5]", min[5]);
		session.setAttribute("max[5]", max[5]);
		session.setAttribute("get[5]", get[5]);
		session.setAttribute("fudu[5]", fudu[5]);
		session.setAttribute("title[5]", title[5]);
		session.setAttribute("date", date);
		session.setAttribute("data1", data1);
		session.setAttribute("data2", data2);
		session.setAttribute("data3", data3);
		session.setAttribute("data4", data4);
		session.setAttribute("data5", data5);
		return "stockcompare.jsp";
	}
	
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public String calDate(Date d,int day){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, day);
		String date=df.format(c.getTime());
		return date;
	}
	
	public String calMonth(Date d,int month){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH,month);
		String date=df.format(c.getTime());
		return date;
	}

}
