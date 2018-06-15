package data;

import java.sql.SQLException;
import java.util.List;

import model.AllYearsStockModel;
import model.HistoryStockModel;
import model.PersonStockModel;
import model.StockModel;
import model.TigerModel;
import model.UserModel;
import model.ZhishuModel;

public interface dataservice {
	//用户添加（注册）功能
	 public  int insertuser(List<UserModel> user) throws SQLException;
	//数据库自选股添加功能
	 public int insertpersonstock(PersonStockModel personstock);
	//数据库历史股票添加功能
	 public int inserthistorystock(HistoryStockModel historystock);
	//数据库自选股删除功能
	 public int deletpersonstock(String user_id,String stock_id);
	//数据库历史数据删除功能
	 public int delethistorystock(String user_id,String stock_id);
	//根据userid查询自选股列表
     public List<PersonStockModel> getPersonByUserID (String user_id);
    //根据userid查询历史查询列表
     public List<HistoryStockModel> getHistoryByUserID (String user_id);
    //根据userid查询usermodel
     public UserModel getUserByUserID(String user_id);
    //根据stockid查询公司新闻
     public String[][] getStockGSXWByStockID(String stock_id);
     //根据stockid查询公司公告
     public String[][] getStockGSGGByStockID(String stock_id) ;
     //根据股票ID查询股票
     public List<StockModel> getStockByStockID (String stock_id);
     //根据股票名称查询股票
     public List<StockModel> getStockByStockName (String stock_name);
     //根据日期查询股票
     public List<AllYearsStockModel> getStockByDate (String date);
     //根据股票类型查询股票
     public List<AllYearsStockModel> getStockByStyle (String style,String date);
     //根据 时间段 和 股票ID查询股票
     public List<AllYearsStockModel> getStockByDuringAndStockID (String start_time,String end_time,String stock_id);
     //根据 时间段 和 股票名称查询股票
     //public List<AllYearsStockModel> getStockByDuringAndStockName (String start_time,String end_time,String stock_name);
   //龙虎榜数据
     public  List<TigerModel> getTigerStyle();
   //股票列表
     public List<StockModel> getAllStocks(String style,String date);
   //查询时间的上海指数
     public List<ZhishuModel> getShangzhi(String date);
   //查询时间段的上海指数
     public List<ZhishuModel> getDuringShangzhi(String start_time,String end_time);
     //查询时间的深证成指
     public List<ZhishuModel> getShenzhi(String date);
     //查询时间段的深证成指
     public List<ZhishuModel> getDuringShenzhi(String start_time,String end_time);
}
