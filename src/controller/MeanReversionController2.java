package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import data.dataimpl;
import data.dataservice;
import model.AllYearsStockModel;
import model.PersonStockModel;
import model.SimpleStockModel;

@Controller
public class MeanReversionController2 {
	@Autowired
	HttpServletRequest request;
	
	private SimpleStockGet stockbl = new SimpleStockGet();
	
	dataservice dataservice=new dataimpl();
	
	@RequestMapping("strategy_2_2.spring")
	public String meanRev2(@RequestParam("stockpool")String stock_pool,  @RequestParam("chiyou")int position_cycle, @RequestParam("shuliang")int num_stock,
			@RequestParam("start")String start, @RequestParam("end")String end,HttpSession session){
		
		
		if(stock_pool.equals("沪股")){
			stock_pool = "stock_shang";
		}else if(stock_pool.equals("深股")){
			stock_pool = "stock_shen";
		}else if(stock_pool.equals("创业板")){
			stock_pool = "stock_chuangye";
		}else if(stock_pool.equals("自选股")){
			stock_pool="自选股";
		}
//		System.out.println(position_cycle);
		
		List<List<Double>> sources = DifferentTime(stock_pool, position_cycle, num_stock, start, end);
		List<Double> zhouqi = sources.get(0);            //作为两张图的横坐标
		List<Double> chaoeshouyi = sources.get(1);       //第一张图的纵坐标
		List<Double> win = sources.get(2);               //第二张图的纵坐标
		
	    session.setAttribute("zhouqi", zhouqi);
	    session.setAttribute("chaoeshouyi", chaoeshouyi);
	    session.setAttribute("Win", win);
		
		return "strategy_2_2.jsp";
	}
	
	//计算超额收益率和策略胜率
		public List<List<Double>> DifferentTime(String stock_pool, int day_interval, int num_stock, String start,
				String end) {
			HttpSession session = request.getSession();
			
			List<String> stocks_name = new ArrayList<String>();
			List<List<SimpleStockModel>> stocks = new ArrayList<List<SimpleStockModel>>();
			int stock_total;
            if(stock_pool.equals("自选股")){
            	List<PersonStockModel> templist1 = dataservice.getPersonByUserID(String.valueOf(session.getAttribute("email")).split("@")[0]);
            	for(PersonStockModel personmodel:templist1){
            		stocks_name.add(personmodel.getStock_id());
            	}
            }else{
				List<AllYearsStockModel> tempss = dataservice.getStockByStyle(stock_pool, "2017-05-24");
				for(AllYearsStockModel modelss:tempss){
					stocks_name.add(modelss.getCode());
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
			

			List<Double> overpro = new ArrayList<Double>();// 计算超额收益率
			List<Double> intervalss = new ArrayList<Double>();
			List<Double> win = new ArrayList<Double>();
			List<Double> temppro = new ArrayList<Double>();
			System.out.println(deviation_degree.size());
			int day = deviation_degree.get(0).size();
			List<Double> Profit = new ArrayList<Double>();
			double origin = 0;

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
			List<Integer> kill_note = new ArrayList<Integer>();

			int max = 80;
			if (day / 2 < 80)
				max = day / 2;
			for (int i = 2; i <= max; i += 2) {
				double originp = 0;
				for (int u = 0; u < num_stock; u++) {
					originp += stocks.get(u).get(0).getAdjClose();
				}
				originp /= num_stock;

				double moneyp = 0;
				double buyStartp = originp;
				double buyEndp = 0;

				List<Integer> stock_notep = new ArrayList<Integer>();
				for (int u = 0; u < day; u++) {
					double temp2p = 0;
					if (u % i == 0) {// 按每i天为周期调换仓位
						if (u != 0) {
							for (int j = 0; j < num_stock; j++){
								List<SimpleStockModel> temppp=stocks.get(stock_notep.get(j));
								if(temppp.size()-1>=u){
									temp2p += temppp.get(u).getAdjClose();
								}
								else{
									temp2p += temppp.get(temppp.size()-1).getAdjClose();
								}
							}
								
							temp2p /= num_stock;
							buyEndp = temp2p;
							moneyp += buyEndp - buyStartp;
						}

						stock_notep.clear();
						stock_notep = new ArrayList<Integer>();
						
						for (int m = 0; m < stock_notep.size(); m++) {
							for(int q=i;(q<m+i)&&q<day;q++){
								if(stocks.get(m).get(q).getVolumn()==0)
									kill_note.add(m);
							}
						}
						
						for (int m = 0; m < num_stock; m++) {
							double maxValue = -100;
							int maxIndex = m;
							for (int n = 0; n < stock_total; n++) {
								if (deviation_degree.get(n).get(m) > maxValue && !stock_notep.contains(n)&&!kill_note.contains(n)) {
									maxValue = deviation_degree.get(n).get(m);
									maxIndex = n;
								}
							}
							if (!stock_notep.contains(maxIndex))
								stock_notep.add(maxIndex);
						}

					}
					double temp10 = 0;
					for (int j = 0; j < stock_notep.size(); j++) {
						temp10 += stocks.get(stock_notep.get(j)).get(j).getAdjClose();
					}
					temp10 /= num_stock;
					if (u % i == 0 && u != 0)
						buyStartp = temp10;
					temppro.add((moneyp + temp10) / origin - 1);
				}
				intervalss.add(Double.valueOf(i));
				overpro.add((temppro.get(day - 1) - Profit.get(day - 1)));
				win.add((temppro.get(day - 1) + 1) / (temppro.get(day - 1) + Profit.get(day - 1) + 2));
				temppro.clear();
				temppro = new ArrayList<Double>();
			}

			List<List<Double>> res = new ArrayList<List<Double>>();
			res.add(intervalss);
			res.add(overpro);
			res.add(win);
			return res;
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
