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

import data.dataimpl;
import data.dataservice;
import model.AllYearsStockModel;
import model.PersonStockModel;
import model.StockModel;
@Controller
public class MomDrivenController {
	
	dataservice dataservice=new dataimpl();
	
	private List<AllYearsStockModel> list;
	
	private List<AllYearsStockModel> listCode;
	
	private List<List<AllYearsStockModel>> listFull;
	
	private List<Double> earn;
	
	private List<Double> staEarn;
	
	@RequestMapping("strategy_1_1.spring")
	public String momDriven(@RequestParam("begin")String begin,@RequestParam("end") String end,@RequestParam("stockpool")String style,
			@RequestParam("chiyou") String chiyou,@RequestParam("xingcheng") String xingcheng,HttpSession session){
		list=new ArrayList<AllYearsStockModel>();
		listCode=new ArrayList<AllYearsStockModel>();
		listFull=new ArrayList<List<AllYearsStockModel>>();
		List<String> listDate=new ArrayList<String>();
		earn=new ArrayList<Double>();
		staEarn=new ArrayList<Double>();
		
		int chi=Integer.parseInt(chiyou);
		int xing=Integer.parseInt(xingcheng);
		
		//修正日期
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
		int earnsize=0;
		int size=0;
		//读数据
		
		if(style.equals("自选股")){
			List<PersonStockModel> listC=dataservice.getPersonByUserID(String.valueOf(session.getAttribute("email")).split("@")[0]);
//			System.out.println(listC.size()+"a");
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
		System.out.println(true);
		int earnzero=size-earnsize;
		
		System.out.println(listFull.size());
		int min=0;
		int max=0;
		int days=size-1;
		DecimalFormat dff=new DecimalFormat("#.000");
		//策略结果
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
	    
//	    for(int i=0;i<earn.size();i++){
//	    	System.out.println(earn.get(i));
//	    }
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
	    	String[] tempformat=list.get(i).getDate().split("-");
	    	String time0 = "'"+tempformat[0]+"'"+"+"+"'-'"+"+"+"'"+tempformat[1]+"'"+"+"+"'-'"+"+"+"'"+tempformat[2]+"'";
	    	listDate.add(time0);
	    	
	    }

	    //年收益率
	    double annualreturn=0.0;
	    annualreturn=earn.get(earn.size()-1);
	    annualreturn=annualreturn/earn.size()*365;
	    String result=dff.format(annualreturn);
	    annualreturn=Double.parseDouble(result);
	    
	    //基准年化收益率
	    double staAnnualreturn=0.0;
	    staAnnualreturn=staEarn.get(staEarn.size()-1);
	    staAnnualreturn=staAnnualreturn/staEarn.size()*365;
	    result=dff.format(staAnnualreturn);
	    staAnnualreturn=Double.parseDouble(result);
	    	
	    //夏普利率
	    double sharp=0.0;
	    double rf=0.0175;//无风险利率
	    double average=annualreturn/365;
	    double past1=0.0;
	    double today1=0.0;
	    double sum=0.0;
	    for(int i=0;i<earn.size();i++){
	    	today1=earn.get(i);
	    	sum=Math.pow(today1-past1-average, 2)+sum;
	    	past1=today1;			
	    }
	    double Biaozhun=Math.sqrt(sum/earn.size());
	    sharp=(average*earn.size()-rf)/Biaozhun;
	    result=dff.format(sharp);
	    sharp=Double.parseDouble(result);
	  
	    //贝塔利率
	    double beta=0.0;
	    double staAverage=staAnnualreturn/365;
	    past1=0.0;  today1=0.0;
	    double past2=0.0;  double today2=0.0;
	    double xiefangcha=0.0;
	    double staFangcha=0.0;
	    for(int i=0;i<earn.size();i++){
	    	today1=earn.get(i);
	    	today2=staEarn.get(i);
	    	xiefangcha=(today1-past1-average)*(today2-past2-staAverage)+xiefangcha;
	    	staFangcha=Math.pow(today2-past2-staAverage, 2)+staFangcha;
	    	past1=today1;  past2=today2;
	    }
	    xiefangcha=xiefangcha/earn.size()+average*staAverage;
	    staFangcha=staFangcha/earn.size();
	    beta=xiefangcha/staFangcha;
	    result=dff.format(beta);
	    beta=Double.parseDouble(result);
	  
	    //求阿尔法比率
	    double alpha=0.0;
	    alpha=(annualreturn-rf)-beta*(staAverage*earn.size()-rf);
	    result=dff.format(alpha);
	    alpha=Double.parseDouble(result);
	  
	    //求最大回撤
	    double MaxDrawdown=0.0;
	    for (int i = 0; i < earn.size()-1; i++) {
	    	for (int j = i + 1; j < earn.size()-1; j++) {
	    		double	temp = earn.get(i) - earn.get(j);
	  				
	    		if (temp > MaxDrawdown)
	    			MaxDrawdown = temp;
	    	}
	    }
	    result=dff.format(MaxDrawdown);
	    alpha=Double.parseDouble(result);	
	    
	    List<Integer> countNum = new ArrayList<Integer>();
        int[] count=new int[20];
	    for (int i = 0; i < earn.size()-1; i++) {
	    	double temp = earn.get(i+1)-earn.get(i);
	    	if(temp<-0.09){
	    		count[0]++;
	    	}else if(temp>=-0.09&&temp<-0.08){
	    		count[1]++;
	    	}else if(temp>=-0.08&&temp<-0.07){
	    		count[2]++;
	    	}else if(temp>=-0.07&&temp<-0.06){
	    		count[3]++;
	    	}else if(temp>=-0.06&&temp<-0.05){
	    		count[4]++;
	    	}else if(temp>=-0.05&&temp<-0.04){
	    		count[5]++;
	    	}else if(temp>=-0.04&&temp<-0.03){
	    		count[6]++;
	    	}else if(temp>=-0.03&&temp<-0.02){
	    		count[7]++;
	    	}else if(temp>=-0.02&&temp<-0.01){
	    		count[8]++;
	    	}else if(temp>=-0.01&&temp<0){
	    		count[9]++;
	    	}else if(temp>=-0&&temp<0.01){
	    		count[10]++;
	    	}else if(temp>=0.01&&temp<0.02){
	    		count[11]++;
	    	}else if(temp>=0.02&&temp<0.03){
	    		count[12]++;
	    	}else if(temp>=0.03&&temp<0.04){
	    		count[13]++;
	    	}else if(temp>=0.04&&temp<0.05){
	    		count[14]++;
	    	}else if(temp>=0.05&&temp<0.06){
	    		count[15]++;
	    	}else if(temp>=0.06&&temp<0.07){
	    		count[16]++;
	    	}else if(temp>=0.07&&temp<0.08){
	    		count[17]++;
	    	}else if(temp>=0.08&&temp<0.09){
	    		count[18]++;
	    	}else if(temp>=0.09){
	    		count[19]++;
	    	}
	    }
	    for(int i=0;i<20;i++){
//	    	System.out.println(count[i]);
	    	countNum.add(count[i]);
	    }
	    
	    session.setAttribute("earn", earn);
	    session.setAttribute("staEarn", staEarn);
	    session.setAttribute("annualreturn", annualreturn);
	    session.setAttribute("staAnnualreturn", staAnnualreturn);
	    session.setAttribute("sharp", sharp);
	    session.setAttribute("beta", beta);
	    session.setAttribute("alpha", alpha);
	    session.setAttribute("MaxDrawdown", MaxDrawdown);
	    session.setAttribute("countNum", countNum);
	    session.setAttribute("listDate", listDate);
	    
		System.out.println(true);
		return "strategy_1_1.jsp";
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
