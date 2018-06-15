package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dataimpl;
import data.dataservice;
import model.AllYearsStockModel;
import model.PersonStockModel;
import model.SimpleStockModel;
import model.StrategyModel;

@Controller
public class MeanReversionController {
	@Autowired
	HttpServletRequest request;
	
	dataservice dataservice=new dataimpl();
	
	private SimpleStockGet stockbl = new SimpleStockGet();
	
	double riskfree_rate = 0.018;// 取的是一年期存款利率
    
	@RequestMapping("strategy_1_2.spring")
	public String MeanReversion(@RequestParam("stockpool")String stock_pool, @RequestParam("line")int day_interval, @RequestParam("chiyou")int position_cycle, @RequestParam("shuliang")int num_stock,
			@RequestParam("start")String start, @RequestParam("end")String end,HttpSession session){
		DecimalFormat df = new DecimalFormat("#.000");
		
		
		if(stock_pool.equals("沪股")){
			stock_pool = "stock_shang";
		}else if(stock_pool.equals("深股")){
			stock_pool = "stock_shen";
		}else if(stock_pool.equals("创业板")){
			stock_pool = "stock_chuangye";
		}else if(stock_pool.equals("自选股")){
			stock_pool = "自选股";
		}
		
		
		List<List<Double>> sources = Profit_Cal(stock_pool, day_interval, position_cycle, num_stock, start, end);
		List<Double> profit = sources.get(0);
		List<Double> strprofit = sources.get(1);
		
		List<SimpleStockModel> info = stockbl.GetSharesInfoByDate(start, end, "000001");
		
		//gram1
		StrategyModel model1 = MeanReversion(profit, strprofit,  position_cycle);
		double AnnualReturn = Double.parseDouble(df.format(model1.getAnnualReturn()));           //填充到表格里的数据
		double BaseReturn = Double.parseDouble(df.format(model1.getBaseAnnualReturn()));
		double MaxDrawdown = Double.parseDouble(df.format(model1.getMaxDrawdown()));
		double Beta = Double.parseDouble(df.format(model1.getBeta()));
		double Alpha = Double.parseDouble(df.format(model1.getAlpha()));
		double SharpeRatio = Double.parseDouble(df.format(model1.getSharpeRatio()));
		
		List<String> dates = new ArrayList<String>();
		for(int i=info.size()-1;i>=0;i--){
			String timetemp = info.get(i).getDate();
            String[] tempformat = timetemp.split("-");
	    	timetemp = "'"+tempformat[0]+"'"+"+"+"'-'"+"+"+"'"+tempformat[1]+"'"+"+"+"'-'"+"+"+"'"+tempformat[2]+"'";
			dates.add(timetemp);             
		}
		
		List<Double> base = new ArrayList<Double>();
		List<Double> strategy = new ArrayList<Double>();
		for(int i = 0;i<info.size();i++){
			base.add(Double.parseDouble(df.format(profit.get(i))));                                //分别是基准收益率和策略收益率，用小数表示，填充到第一张图中
			strategy.add(Double.parseDouble(df.format(strprofit.get(i))));
		}
		
		//gram3
		StrategyModel model2 = MeanReversion(profit, strprofit, position_cycle);
		int positive_num = model2.getPostive_period().size();
		int negative_num = model2.getNegative_period().size();
		double temp1 = positive_num+negative_num;
		double temp2 = positive_num;
		double win_rate = (double)temp2/temp1;
		
		List<Double> positive = model2.getPostive_period();
		List<Double> negative = model2.getNegative_period();
		
		Map<Double,Integer> map1 = new HashMap<Double,Integer>();
		Map<Double,Integer> map2 = new HashMap<Double,Integer>();

		for(int i = 0;i<positive.size();i++){        //统计不同区间出现的频数
			Double key = positive.get(i);
			if(!map1.containsKey(key)){
				map1.put(key, 1);
				
			}
			else{
				int temp = map1.get(key);
				map1.put(key, temp+1);
			}
		}
		for(int i = 0;i<negative.size();i++){
			Double key = negative.get(i);
			if(!map2.containsKey(key)){
				map2.put(key, 1);
				
			}
			else{
				int temp = map2.get(key);
				map2.put(key, temp+1);
			}
		}

		
		int[] counter = new int[20];
		for(int i =0 ;i<20;i++){
			counter[i]=0;
		}
		
		for(Map.Entry<Double, Integer>entry:map2.entrySet()){
			if(entry.getKey()>-0.1&&entry.getKey()<=0){
				counter[9]++;
			}
			if(entry.getKey()>-0.2&&entry.getKey()<=-0.1){
				counter[8]++;
			}
			if(entry.getKey()>-0.3&&entry.getKey()<=-0.2){
				counter[7]++;
			}
			if(entry.getKey()>-0.4&&entry.getKey()<=-0.3){
				counter[6]++;
			}
			if(entry.getKey()>-0.5&&entry.getKey()<=-0.4){
				counter[5]++;
			}
			if(entry.getKey()>-0.6&&entry.getKey()<=-0.5){
				counter[4]++;
			}
			if(entry.getKey()>-0.7&&entry.getKey()<=-0.6){
				counter[3]++;
			}
			if(entry.getKey()>-0.8&&entry.getKey()<=-0.7){
		    	counter[2]++;
			}
			if(entry.getKey()>-0.9&&entry.getKey()<=-0.8){
				counter[1]++;
			}
			if(entry.getKey()<=-0.9){
				counter[0]++;
			}
		}
		for(Map.Entry<Double, Integer>entry:map1.entrySet()){
			if(entry.getKey()>0&&entry.getKey()<=0.1){
				counter[10]++;
			}
			if(entry.getKey()>0.1&&entry.getKey()<=0.2){
				counter[11]++;
			}
			if(entry.getKey()>0.2&&entry.getKey()<=0.3){
				counter[12]++;
			}
			if(entry.getKey()>0.3&&entry.getKey()<=0.4){
				counter[13]++;
			}
			if(entry.getKey()>0.4&&entry.getKey()<=0.5){
				counter[14]++;
			}
			if(entry.getKey()>0.5&&entry.getKey()<=0.6){
				counter[15]++;
			}
			if(entry.getKey()>0.6&&entry.getKey()<=0.7){
				counter[16]++;
			}
			if(entry.getKey()>0.7&&entry.getKey()<=0.8){
		    	counter[17]++;
			}
			if(entry.getKey()>0.8&&entry.getKey()<=0.9){
				counter[18]++;
			}
			if(entry.getKey()>0.9){
				counter[19]++;
			}
		}
		
		List<Integer> templist = new ArrayList<Integer>();
		for(int i=0;i<20;i++){
			templist.add(counter[i]);                 //横坐标对应的频数，填充到第二张图中
		}
				
		session.setAttribute("AnnualReturn", AnnualReturn);   //表格里的数据
		session.setAttribute("BaseReturn",BaseReturn);
		session.setAttribute("MaxDrawDown",MaxDrawdown);
		session.setAttribute("Beta",Beta);
		session.setAttribute("Alpha",Alpha);
		session.setAttribute("SharpeRatio",SharpeRatio);
		
		session.setAttribute("dates", dates);
		session.setAttribute("base", base);
		session.setAttribute("strategy", strategy);
		session.setAttribute("fenbu", templist);
	
								
	    return "strategy_1_2.jsp";
	}
	
	//计算
	public List<List<Double>> Profit_Cal(String stock_pool,int day_interval, int position_cycle,int num_stock,
			String start, String end) {
		
		List<String> stocks_name = new ArrayList<String>();
		HttpSession session = request.getSession();

		List<List<SimpleStockModel>> stocks = new ArrayList<List<SimpleStockModel>>();
		int stock_total;
		if(stock_pool.equals("自选股")){
        	List<PersonStockModel> templist1 = dataservice.getPersonByUserID(String.valueOf(session.getAttribute("email")).split("@")[0]);
        	for(PersonStockModel personmodel:templist1){
        		stocks_name.add(personmodel.getStock_id());
        	}
        }else {
			List<AllYearsStockModel> temps= dataservice.getStockByStyle(stock_pool, "2017-05-04");
			for(AllYearsStockModel models:temps){
				stocks_name.add(models.getCode());
			}
        }
			stock_total = stocks_name.size();
			
			int tttt=0;
			for (int i = 0; i < stock_total; i++){
				List<SimpleStockModel> sasa=stockbl.GetSharesInfoByDate(start, end, stocks_name.get(i));
				if(sasa.size()!=0)
				   stocks.add(sasa);
				else
					tttt++;
			}
			stock_total-=tttt;
		

		List<List<Double>> deviation_degree = new ArrayList<List<Double>>();
		for (int i = 0; i < stocks.size(); i++) {
			deviation_degree.add(DeviationDegree(stocks.get(i).get(0).getName(), start, end, day_interval));
		}
		

		int day = stockbl.GetSharesInfoByDate(start, end, "000001").size();
		List<Double> StrProfit = new ArrayList<Double>();

		double origin = 0;
		for (int i = 0; i < num_stock; i++) {
			origin += stocks.get(i).get(0).getAdjClose();
		}
		origin /= num_stock;

		double money = 0;
		double buyStart = origin;
		double buyEnd = 0;

		List<Integer> kill_note = new ArrayList<Integer>();

		List<Integer> stock_note = new ArrayList<Integer>(num_stock);
		for (int i = 0; i < num_stock; i++)
			stock_note.add(i);
		for (int i = 0; i < day; i++) {
			double temp2 = 0;
			if (i % position_cycle == 0 && i != 0) {// 按每position_cycle天为周期调换仓位
				for (int j = 0; j < stock_note.size(); j++){
					List<SimpleStockModel> temppp=stocks.get(stock_note.get(j));
					if(temppp.size()-1>=i){
						temp2 += temppp.get(i).getAdjClose();
					}
					else{
						temp2 += temppp.get(temppp.size()-1).getAdjClose();
					}
				}
					
				temp2 /= stock_note.size();
				buyEnd = temp2;
				money += buyEnd - buyStart;

				stock_note.clear();
				stock_note = new ArrayList<Integer>();

				for (int m = 0; m < stock_total; m++) {
					for(int q=i;(q<i+position_cycle)&&q<day&&q<stocks.get(m).size();q++){
						if(stocks.get(m).get(q).getVolumn()==0)
							kill_note.add(m);
					}
				}

				for (int m = 0; m < num_stock; m++) {
					double maxValue = -100;
					int maxIndex = m;
					for (int n = 0; n < stock_total; n++) {
						int ttttt=deviation_degree.get(n).size()-1;
						if(ttttt>=i){
							if (deviation_degree.get(n).get(i) > maxValue && !stock_note.contains(n)&&!kill_note.contains(n)) {
								maxValue = deviation_degree.get(n).get(i);
								maxIndex = n;
							}
						}
					}
					if (!stock_note.contains(maxIndex))
						stock_note.add(maxIndex);
				}
				kill_note.clear();
				kill_note=new ArrayList<Integer>();

			}
			double temp1 = 0;
			for (int j = 0; j < stock_note.size(); j++) {
				List<SimpleStockModel> tempp=stocks.get(stock_note.get(j));
				if(tempp.size()-1>=i)
				   temp1 += tempp.get(i).getAdjClose();
				else{
					temp1 += tempp.get(tempp.size()-1).getAdjClose();
				}
			}
			temp1 /= stock_note.size();
			if (i % position_cycle == 0 && i != 0)
				buyStart = temp1;
			StrProfit.add((money + temp1) / origin - 1);
		}

		List<Double> Profit = new ArrayList<Double>();
		origin = 0;

		for (int i = 0; i < stock_total; i++) {
			origin += stocks.get(i).get(0).getAdjClose();
		}
		origin /= (double) stock_total;
		for (int i = 0; i < day; i++) {
			double temp1 = 0;
			for (int j = 0; j < stock_total; j++){
				if(stocks.get(j).size()-1>=i){
					temp1 += stocks.get(j).get(i).getAdjClose();
				}
				else{
					temp1 += stocks.get(j).get(stocks.get(j).size()-1).getAdjClose();
				}
			}
				
			temp1 /= (double) stock_total;
			Profit.add(temp1 / origin - 1);
		}

		List<List<Double>> pros = new ArrayList<List<Double>>(2);
		pros.add(Profit);
		pros.add(StrProfit);

		return pros;
	}
    
	
	
    
	
	public StrategyModel MeanReversion(List<Double> profit, List<Double> Strprofit, int cycle) {
		int day = profit.size();

		double AnnualReturn = Strprofit.get(day - 1) / day * 255;

		double BaseAnnualReturn = profit.get(day - 1) / day * 255;

		double MaxDrawdown = 0;
		for (int i = 0; i < day - 1; i++) {
			for (int j = i + 1; j < day; j++) {
				double temp = (Strprofit.get(i) - Strprofit.get(j)) / (Strprofit.get(i)+1);
				if (temp > MaxDrawdown)
					MaxDrawdown = temp;
			}
		}

		double strav = 0;
		double av = 0;
		double avxy = 0;
		for (int i = 0; i < day; i++) {
			strav += Strprofit.get(i);
			av += profit.get(i);
			avxy += (profit.get(i) * Strprofit.get(i));
		}
		strav /= day;
		av /= day;
		avxy /= day;
		double var = 0;
		double var1=0;
		for (int i = 0; i < day; i++) {
			var += Math.pow(profit.get(i) - av, 2);
			var1+= Math.pow(Strprofit.get(i)-strav,2);
		}
		var /= day;
		var1/=day;

		double Beta = (avxy - strav * av) / var;

		double Alpha = AnnualReturn - riskfree_rate - Beta * (profit.get(day - 1) - riskfree_rate);

		double SharpeRatio = (Strprofit.get(day - 1) - riskfree_rate) / Math.sqrt(var1);

		List<Double> postive = new ArrayList<Double>();
		List<Double> negative = new ArrayList<Double>();

		for (int i = cycle; i < Strprofit.size(); i += cycle) {
			double temp = Strprofit.get(i) - Strprofit.get(i - cycle);
			if (temp >= 0)
				postive.add(temp);
			else
				negative.add(temp);

		}

		return new StrategyModel(AnnualReturn, BaseAnnualReturn, MaxDrawdown, Beta, Alpha, SharpeRatio, postive, negative);
	}

	public List<Double> DeviationDegree(String nameORcode, String start, String end, int Dayinterval) {
		List<Double> res = new ArrayList<Double>();
		List<SimpleStockModel> list = stockbl.GetSharesInfoByDate(start, end, nameORcode);
		List<Double> MeanValue = MeanValueCal(list, Dayinterval);

		for (int i = 0; i < MeanValue.size(); i++) {
			res.add((MeanValue.get(i) - list.get(i).getAdjClose()) / MeanValue.get(i));
		}

		return res;
	}

	// 计算均值
			protected List<Double> MeanValueCal(List<SimpleStockModel> list, int Dayinterval) {
				List<Double> res = new ArrayList<Double>();
	            if(list.size()>=Dayinterval){
	    			for (int i = 0; i < Dayinterval - 1; i++) {
	    				double sum = 0;
	    				for (int j = 0; j < i + 1; j++){
	    					sum += list.get(j).getAdjClose();
	    				}
	    				res.add(sum / (i + 1));
	    			}

	    			for (int i = Dayinterval - 1; i < list.size(); i++) {
	    				double sum = 0;
	    				for (int j = i; j > i - Dayinterval; j--)
	    					sum += list.get(j).getAdjClose();
	    				res.add(sum / Dayinterval);
	    			}
	            }

	            else{
	            	for (int i = 0; i < list.size(); i++) {
	    				double sum = 0;
	    				for (int j = 0; j < i + 1; j++){
	    					sum += list.get(j).getAdjClose();
	    				}
	    				res.add(sum / (i + 1));
	    			}
	            }
				return res;
			}
	}