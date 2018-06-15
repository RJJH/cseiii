package data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;

import model.AllYearsStockModel;
import model.HistoryStockModel;
import model.PersonStockModel;
import model.StockModel;
import model.TigerModel;
import model.UserModel;
import model.ZhishuModel;


public class dataimpl implements dataservice{
	static final Log logger = LogFactory.getLog(dataimpl.class);
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/datacollection?characterEncoding=UTF-8");
    static QueryRunner qr = new QueryRunner(ds);
    //第一类方法
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
    //用户添加（注册）功能
    public int insertuser(List<UserModel> user) throws SQLException{
    	int c=0;
    	Object[][] params = new Object[user.size()][3];
    	 int[] sum;
         for ( int i = 0; i < user.size(); i++ ){
             params[i][0] = user.get(i).getUser_name();
             params[i][1] = user.get(i).getUser_id();
             params[i][2] = user.get(i).getUser_password();
         }
    	
         QueryRunner qr = new QueryRunner(ds);
        
             sum = qr.batch("INSERT INTO `datacollection`.`user` VALUES (?,?,?)", params);
       
         System.out.println("用户信息加入成功");
		return c;	
    }
    //数据库自选股添加功能
    public int insertpersonstock(PersonStockModel personstock){
    	int c=0;
    	Object[] params = new Object[3];
   	    int sum;
    	params[0] = personstock.getUser_id();
    	params[1] = personstock.getStock_id();
    	params[2] = personstock.getStock_name();
    	
         QueryRunner qr = new QueryRunner(ds);
         try {
             sum = qr.update("INSERT INTO `datacollection`.`stock_person` VALUES (?,?,?)", params);
         } catch (SQLException e) {
             System.out.println(e);
         }
         System.out.println("自选股加入成功");
		return c;	
    }
    //数据库历史股票添加功能
    public int inserthistorystock(HistoryStockModel historystock){
    	int c=0;
    	Object[] params = new Object[4];
    	 int sum;
    	params[0] = historystock.getUser_id();
    	params[1] = historystock.getStock_id();
    	params[2] = historystock.getStock_name();
    	params[3] = historystock.getCraw_time();
         QueryRunner qr = new QueryRunner(ds);
         try {
             sum = qr.update("INSERT INTO `datacollection`.`stock_history` VALUES (?,?,?,?)", params);
         } catch (SQLException e) {
             System.out.println(e);
         }
         System.out.println("历史数据加入成功");
		return c;	
    }
    //数据库自选股删除功能
    public int deletpersonstock(String user_id,String stock_id){
		int c=0;
		 String sql = "delete from stock_person where user_id='" + user_id + "'" + "and stock_id='"+stock_id+"'";
		 int sum;
		 QueryRunner qr = new QueryRunner(ds);
         try {
             sum = qr.update(sql);
         } catch (SQLException e) {
             System.out.println(e);
         }
         System.out.println("自选股数据删除成功");
    	return c;
    	
    }
    //数据库历史数据删除功能
    public int delethistorystock(String user_id,String stock_id){
		int c=0;
		 String sql = "delete from stock_history where user_id='" + user_id + "'" + "and stock_id='"+stock_id+"'";
		 int sum;
		 QueryRunner qr = new QueryRunner(ds);
         try {
             sum = qr.update(sql);
         } catch (SQLException e) {
             System.out.println(e);
         }
         System.out.println("历史数据删除成功");
    	return c;
    	
    }
    
    //根据股票ID查询股票(测试通过)
    public List<StockModel> getStockByStockID (String stock_id){
    	String sql = "select date,stock_id,stock_name,stock_price,stock_change,stock_range,stock_amplitude,stock_trading_number,stock_trading_value,stock_yesterdayfinish_price,stock_todaystart_price,stock_max_price,stock_min_price from stock_hushen where stock_id='"+stock_id+"'";
    	QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler<List<StockModel>> h = new BeanListHandler<StockModel>(StockModel.class);
        List<StockModel> stock = null;
        try {
            stock = qr.query(sql, h);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }
    //根据 开始时间 和 股票ID查询股票
    @SuppressWarnings("null")
    @Cacheable(value = "stockduring")
	public List<AllYearsStockModel> getStockByDuringAndStockID (String start_time,String end_time,String stock_id){    
    	String sql = "select date,open,close,high,low,volume,code from newdata where code='"+stock_id+"' and date between '"+start_time+"'and'"+end_time+"'";
    	QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler<List<AllYearsStockModel>> h = new BeanListHandler<AllYearsStockModel>(AllYearsStockModel.class);
        List<AllYearsStockModel> stock = null;     
        try {
            stock = qr.query(sql, h);             
        } catch (SQLException e) {
            e.printStackTrace();
        }       
        return stock;
    }
//    //根据 开始时间 和 股票名称查询股票
//    public List<AllYearsStockModel> getStockByDuringAndStockName (String start_time,String end_time,String stock_name){
//    	 List<AllYearsStockModel> temp = new ArrayList<AllYearsStockModel>();
//         //时间段在一个年份里
//                                
//     	String sql = "select tradeDate,secID,secShortName,openPrice,highestPrice,lowestPrice,closePrice,actPreClosePrice,turnoverVol,chgPct from table where stock_name='"+stock_name+"'";
//     	QueryRunner qr = new QueryRunner(ds);
//         ResultSetHandler<List<AllYearsStockModel>> h = new BeanListHandler<AllYearsStockModel>(AllYearsStockModel.class);
//         List<AllYearsStockModel> stock = null;
//        
//         try {
//             stock = qr.query(sql, h);
//             for(int i = 0;i<stock.size();i++){           	
//             	if(stock.get(i).getDate().equals(start_time)){
//             		for(int j = i;j<stock.size();j++){
//             			if(stock.get(j).getDate().equals(end_time)){
//             				temp.add(stock.get(j));
//             				break;
//             			}else{
//             				temp.add(stock.get(j));
//             			}
//             		}
//             	}
//             }     
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         
//         return temp;
//    }
    //根据股票名称查询股票(测试通过)
    @Cacheable(value = "stockname")
    public List<StockModel> getStockByStockName (String stock_name){
    	String sql = "select date,stock_id,stock_name,stock_price,stock_change,stock_range,stock_amplitude,stock_trading_number,stock_trading_value,stock_yesterdayfinish_price,stock_todaystart_price,stock_max_price,stock_min_price from stock_hushen where stock_name='"+stock_name+"'";
    	QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler<List<StockModel>> h = new BeanListHandler<StockModel>(StockModel.class);
        List<StockModel> stock = null;
        try {
            stock = qr.query(sql, h);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }
    //根据日期查询股票(测试通过)
    @Cacheable(value = "stockdate")
    public List<AllYearsStockModel> getStockByDate (String date){
    	String sql = "select date,open,close,high,low,volume,code from newdata where date='"+date+"'";
    	QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler<List<AllYearsStockModel>> h = new BeanListHandler<AllYearsStockModel>(AllYearsStockModel.class);
        List<AllYearsStockModel> stock = null;
        try {
            stock = qr.query(sql, h);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }
    //根据股票类型查询股票(测试通过)
    @Cacheable(value = "stockstyle")
    public List<AllYearsStockModel> getStockByStyle (String style,String date){
    	String s = "";
    	if(style.equals("stock_shang")){
    		s = "600";
    	}else if(style.equals("stock_shen")){
    		s = "000";
    	}else if(style.equals("stock_chuangye")){
    		s = "300";
    	}
    	String sql = "select date,open,close,high,low,volume,code from newdata where date='"+date+"' and LEFT(newdata.code,3)='"+s+"'";
    	QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler<List<AllYearsStockModel>> h = new BeanListHandler<AllYearsStockModel>(AllYearsStockModel.class);
        List<AllYearsStockModel> stock = null;
        try {
            stock = qr.query(sql, h);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }

    //股票列表
    public List<StockModel> getAllStocks(String style,String date){
    	String sql = "select date,stock_id,stock_name,stock_price,stock_change,stock_range,stock_amplitude,stock_trading_number,stock_trading_value,stock_yesterdayfinish_price,stock_todaystart_price,stock_max_price,stock_min_price from "+style+" where date='"+date+"'";
    	QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler<List<StockModel>> h = new BeanListHandler<StockModel>(StockModel.class);
        List<StockModel> stock = null;
        try {
            stock = qr.query(sql, h);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
		return stock;
    	
    }
    //根据userid查询自选股列表（测试通过）
    @Cacheable(value = "person")
    public List<PersonStockModel> getPersonByUserID (String user_id){
    	String sql = "select distinct stock_id,stock_name from stock_person where user_id='"+user_id+"'";
    	QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler<List<PersonStockModel>> h = new BeanListHandler<PersonStockModel>(PersonStockModel.class);
        List<PersonStockModel> person = null;
        try {
            person = qr.query(sql, h);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
    //根据userid查询历史查询列表（测试通过）
    @Cacheable(value = "history")
    public List<HistoryStockModel> getHistoryByUserID (String user_id){
    	String sql = "select stock_id,stock_name,craw_time from stock_history where user_id='"+user_id+"'";
    	QueryRunner qr = new QueryRunner(ds);
        ResultSetHandler<List<HistoryStockModel>> h = new BeanListHandler<HistoryStockModel>(HistoryStockModel.class);
        List<HistoryStockModel> history = new ArrayList<HistoryStockModel>();
        try {
            history = qr.query(sql, h);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
	//龙虎榜数据
    @Cacheable(value = "tiger")
    public  List<TigerModel> getTigerStyle(){
  	String sql = "select code,name,date,style,start_time,end_time,days,amount,value,stockchange from dragon_tiger";
  	QueryRunner qr = new QueryRunner(ds);
    ResultSetHandler<List<TigerModel>> h = new BeanListHandler<TigerModel>(TigerModel.class);
    List<TigerModel> tiger = new ArrayList<TigerModel>();
    try {
        tiger = qr.query(sql, h);
    } catch (SQLException e) {
        e.printStackTrace();
    }
  	  
  	return tiger;
  	
  }
    //查询时间的上海指数
    public List<ZhishuModel> getShangzhi(String date){
    	String sql = "select b from table000001 where a='"+date+"'";
    	QueryRunner qr = new QueryRunner(ds);
    	 List<ZhishuModel> x = new ArrayList<ZhishuModel>();
    	 ResultSetHandler<List<ZhishuModel>> h = new BeanListHandler<ZhishuModel>(ZhishuModel.class);
    	    
    	    try {
    	        x = qr.query(sql, h);
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    
		return x;
    }
    //查询时间段的上海指数
    public List<ZhishuModel> getDuringShangzhi(String start_time,String end_time){
    	String sql = "select a,b from table000001";
    	QueryRunner qr = new QueryRunner(ds);
    	 List<ZhishuModel> x = new ArrayList<ZhishuModel>();
    	 List<ZhishuModel> temp = new ArrayList<ZhishuModel>();
    	 ResultSetHandler<List<ZhishuModel>> h = new BeanListHandler<ZhishuModel>(ZhishuModel.class);
    	
    	    try {
    	        x = qr.query(sql, h);
    	        for(int i = 0;i<x.size();i++){         
                	if(x.get(i).getA().equals(end_time)){
                		for(int j = i;j<x.size();j++){
                			if(x.get(j).getA().equals(start_time)){
                				temp.add(x.get(j));
                				break;
                			}else{
                				temp.add(x.get(j));
                			}
                		}
                	
                	}
                }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    
		return temp;
    }
    //查询时间的深证成指
    public List<ZhishuModel> getShenzhi(String date){
    	String sql = "select b from table399001 where a='"+date+"'";
    	QueryRunner qr = new QueryRunner(ds);
    	 List<ZhishuModel> x = new ArrayList<ZhishuModel>();
    	 ResultSetHandler<List<ZhishuModel>> h = new BeanListHandler<ZhishuModel>(ZhishuModel.class);
    	    
    	    try {
    	        x = qr.query(sql, h);
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    
		return x;
    }
    //查询时间段的深证成指
    public List<ZhishuModel> getDuringShenzhi(String start_time,String end_time){
    	String sql = "select a,b from table399001";
    	QueryRunner qr = new QueryRunner(ds);
    	 List<ZhishuModel> x = new ArrayList<ZhishuModel>();
    	 List<ZhishuModel> temp = new ArrayList<ZhishuModel>();
    	 ResultSetHandler<List<ZhishuModel>> h = new BeanListHandler<ZhishuModel>(ZhishuModel.class);
    	
    	    try {
    	        x = qr.query(sql, h);
    	        for(int i = 0;i<x.size();i++){         
                	if(x.get(i).getA().equals(end_time)){
                		for(int j = i;j<x.size();j++){
                			if(x.get(j).getA().equals(start_time)){
                				temp.add(x.get(j));
                				break;
                			}else{
                				temp.add(x.get(j));
                			}
                		}
                	
                	}
                }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    
		return temp;
    }
    //根据userid查询usermodel(测试通过)
    @Cacheable(value = "userbyid")
    public UserModel getUserByUserID(String user_id){
    	String sql = "select user_name,user_password from user where user_id='"+user_id+"'";
    	QueryRunner qr = new QueryRunner(ds);
    	ResultSetHandler<UserModel> h = new BeanHandler<UserModel>(UserModel.class);
    	UserModel user = null;
    	try {
			user = qr.query(sql, h);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
    	
    }
	@Override
	//根据stockid查询 公司新闻
		/*
		 * 返回二维数组格式为   URL  TITLE  DATE
		 *                   URL  TITLE  DATE
		 *                   URL  TITLE  DATE
		 * 
		 */
	@Cacheable(value = "news")
	public String[][] getStockGSXWByStockID(String stock_id) {
			// TODO Auto-generated method stub
			String[][] gsxw = new String[8][3];
	        String url="http://stockpage.10jqka.com.cn/"+stock_id+"/";
	        try {
	            Document doc=Jsoup.connect(url).timeout(10000).get();//get all infomation from url website
	            Elements ListDiv = doc.select("[class=news_list stat][stat=f10_spqk_gsxw]");
	           
	            int gsxw_size=0;
	            int gsxw_date_size=0;
	            for (Element div :ListDiv) {
	                 Elements links = div.getElementsByTag("a");
	                 Elements linkdate = div.getElementsByTag("em");
	                // System.out.println(links);
	                 for (Element link : links) {               	 
	                     String linkHref = link.attr("href").trim();
		                 String linkText = link.text();             
	                     gsxw[gsxw_size][0] = linkHref;
	                     gsxw[gsxw_size][1] = linkText;
	                     gsxw_size++;                  
	                 }  
	                 for (Element link2 : linkdate) {	                	 
	                     String linkDate = link2.text();               
	                     gsxw[gsxw_date_size][2] = linkDate;
	                     gsxw_date_size++;  
	                 }
	             }
//	            for(int k = 0;k<gsxw_size;k++){
//		        	for(int m = 0;m<3;m++){
//		        		  System.out.println(gsxw[k][m]);
//		        	}
//		        }
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	      
			return gsxw;
		}
	@Override
	//根据stockid查询公司公告
		/*
		 * 返回二维数组格式为   URL  TITLE  DATE
		 *                   URL  TITLE  DATE
		 *                   URL  TITLE  DATE
		 * 
		 */
	@Cacheable(value = "declare")
		public String[][] getStockGSGGByStockID(String stock_id) {
			// TODO Auto-generated method stub
		
			String[][] gsgg = new String[8][3];
	        String url="http://stockpage.10jqka.com.cn/"+stock_id+"/";
	        try {
	            Document doc=Jsoup.connect(url).timeout(10000).get();//get all infomation from url website
	            
	            Elements ListDiv= doc.select("[class=news_list stat][stat=f10_spqk_gsgg]");
	            //System.out.println(ListDiv);
	            int gsgg_size=0;
	            int gsgg_date_size=0;
	            for (Element div :ListDiv) {
	                 Elements links = div.getElementsByTag("a");
	                 Elements linkdate = div.getElementsByTag("em");
	                // System.out.println(links);
	                 for (Element link : links) {               	 
	                     String linkHref = link.attr("href").trim();
		                 String linkText = link.text();             
	                     gsgg[gsgg_size][0] = linkHref;
	                     gsgg[gsgg_size][1] = linkText;
	                     gsgg_size++;                  
	                 }  
	                 for (Element link2 : linkdate) {	                	 
	                     String linkDate = link2.text();               
	                     gsgg[gsgg_date_size][2] = linkDate;
	                     gsgg_date_size++;  
	                 }
	             }
//	            for(int k = 0;k<gsgg_size;k++){
//		        	for(int m = 0;m<3;m++){
//		        		  System.out.println(gsgg[k][m]);
//		        	}
//		        }
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	      
			return gsgg;
		}



}
