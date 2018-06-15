package navi.main;
/**
 * @author:南京大学 软件学院 陈羿宗
 * @email:151250019@smail.nju.edu.cn
 * @ 
 */
import java.util.ArrayList;
import java.util.List;
import org.quartz.Job; 
import org.quartz.JobExecutionContext; 
import org.quartz.JobExecutionException;


import data.MYSQLControl;
import model.StockModel;
import parse.StockParse;


public class StockMain implements Job{

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
    	List<String> url_hushenAlist=new ArrayList<String>();
        List<String> url_shangzhengAlist=new ArrayList<String>();
        List<String> url_shangzhengBlist=new ArrayList<String>();
        List<String> url_shenzhengAlist=new ArrayList<String>();
        List<String> url_shenzhengBlist=new ArrayList<String>();
        List<String> url_chuangyebanlist=new ArrayList<String>();
        List<String> url_zhongxiaobanlist=new ArrayList<String>();
        List<String> url_xingulist=new ArrayList<String>();
        List<String> url_shanghai_zhishulist=new ArrayList<String>();
        List<String> url_shenzhen_zhishulist=new ArrayList<String>();
        
        List<StockModel> hushenAstocks=new ArrayList<StockModel>();
        List<StockModel> shangzhengAstocks=new ArrayList<StockModel>();
        List<StockModel> shangzhengBstocks=new ArrayList<StockModel>();
        List<StockModel> shenzhengAstocks=new ArrayList<StockModel>();
        List<StockModel> shenzhengBstocks=new ArrayList<StockModel>();
        List<StockModel> chuangyebanstocks=new ArrayList<StockModel>();
        List<StockModel> zhongxiaobanstocks=new ArrayList<StockModel>();
        List<StockModel> xingustocks=new ArrayList<StockModel>();
        List<StockModel> shanghai_zhishustocks=new ArrayList<StockModel>();
        List<StockModel> shenzhen_zhishustocks=new ArrayList<StockModel>();
//沪深A相关股票169页，对应169个地址          
        for (int i = 1; i <169; i++) {
            String urli="http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._A&sty=FCOIATA&sortType=C&sortRule=-1&page="+i+"&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.6743148071691394";
            url_hushenAlist.add(urli);
        }
        for (int i = 0; i < url_hushenAlist.size(); i++) {
            //解析url
        	try {
				hushenAstocks=StockParse.parseurl(url_hushenAlist.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //存储每页的数据
            MYSQLControl.inserthushenAStocks(hushenAstocks);
        }
//上证A相关股票有68页，对应68个地址
        for (int i = 1; i <68; i++) {
            String urli="http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.2&sty=FCOIATA&sortType=C&sortRule=-1&page="+i+"&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.22445713612250984";
            url_shangzhengAlist.add(urli);
        }
        for (int i = 0; i < url_shangzhengAlist.size(); i++) {
            //解析url
        	try {
				shangzhengAstocks=StockParse.parseurl(url_shangzhengAlist.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //存储数据
            MYSQLControl.insertshangzhengAStocks(shangzhengAstocks);
        }
//上证B相关股票有3页，对应3个地址
        for (int i = 1; i <3; i++) {
            String urli="http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.3&sty=FCOIATA&sortType=C&sortRule=-1&page="+i+"&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.2063656421378255";
            url_shangzhengBlist.add(urli);
        }
        for (int i = 0; i < url_shangzhengBlist.size(); i++) {
            //解析url
        	try {
				shangzhengBstocks=StockParse.parseurl(url_shangzhengBlist.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //存储数据
            MYSQLControl.insertshangzhengBStocks(shangzhengBstocks);
        }
//深证A相关股票有102页，对应102个地址
        for (int i = 1; i <102; i++) {
            String urli="http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._SZAME&sty=FCOIATA&sortType=C&sortRule=-1&page="+i+"&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.9614652732852846";
            url_shenzhengAlist.add(urli);
        }
        for (int i = 0; i < url_shenzhengAlist.size(); i++) {
            //解析url
        	try {
				shenzhengAstocks=StockParse.parseurl(url_shenzhengAlist.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //存储数据
            MYSQLControl.insertshenzhengAStocks(shenzhengAstocks);
        }
//深证B相关股票有3页，对应3个地址
        for (int i = 1; i <3; i++) {
            String urli="http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.7&sty=FCOIATA&sortType=C&sortRule=-1&page="+i+"&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.35663661756552756";
            url_shenzhengBlist.add(urli);
        }
        for (int i = 0; i < url_shenzhengBlist.size(); i++) {
            //解析url
        	try {
				shenzhengBstocks=StockParse.parseurl(url_shenzhengBlist.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //存储数据
            MYSQLControl.insertshenzhengBStocks(shenzhengBstocks);
        }
//创业板相关股票有33页，对应33个地址
        for (int i = 1; i <33; i++) {
            String urli="http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.80&sty=FCOIATA&sortType=C&sortRule=-1&page="+i+"&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.33744075614959";
            url_chuangyebanlist.add(urli);
        }
        for (int i = 0; i < url_chuangyebanlist.size(); i++) {
            //解析url
        	try {
				chuangyebanstocks=StockParse.parseurl(url_chuangyebanlist.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //存储数据
            MYSQLControl.insertchuangyebanStocks(chuangyebanstocks);
        }
//中小板相关股票有44页，对应44个地址
        for (int i = 1; i <44; i++) {
            String urli="http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.13&sty=FCOIATA&sortType=C&sortRule=-1&page=2&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.3834844764787704";
            url_zhongxiaobanlist.add(urli);
        }
        for (int i = 0; i < url_zhongxiaobanlist.size(); i++) {
            //解析url
        	try {
				zhongxiaobanstocks=StockParse.parseurl(url_zhongxiaobanlist.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //存储数据
            MYSQLControl.insertzhongxiaobanStocks(zhongxiaobanstocks);
        }
//新股相关股票有23页，对应23个地址
        for (int i = 1; i <23; i++) {
            String urli="http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.BK05011&sty=FCOIATA&sortType=L&sortRule=-1&page="+i+"&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=44c9d251add88e27b65ed86506f6e5da&jsName=quote_123&_g=0.8296546540223062";
            url_xingulist.add(urli);
        }
        for (int i = 0; i < url_xingulist.size(); i++) {
            //解析url
        	try {
				xingustocks=StockParse.parseurl(url_xingulist.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //存储数据
            MYSQLControl.insertxinguStocks(xingustocks);
        }
//上海指数相关股票有15页，对应15个地址
        for (int i = 1; i <15; i++) {
            String urli="http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.1&sty=FCOIATA&sortType=C&sortRule=-1&page="+i+"&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.23964824178256094";
            url_shanghai_zhishulist.add(urli);
        }
        for (int i = 0; i < url_shanghai_zhishulist.size(); i++) {
            //解析url
        	try {
				shanghai_zhishustocks=StockParse.parseurl(url_shanghai_zhishulist.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //存储数据
            MYSQLControl.insertshanghai_zhishuStocks(shanghai_zhishustocks);
        }
//深圳指数相关股票有18页，对应18个地址
        for (int i = 1; i <18; i++) {
            String urli="http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C.5&sty=FCOIATA&sortType=C&sortRule=-1&page="+i+"&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.34745203563943505";
            url_shenzhen_zhishulist.add(urli);
        }
        for (int i = 0; i < url_shenzhen_zhishulist.size(); i++) {
            //解析url
        	try {
				shenzhen_zhishustocks=StockParse.parseurl(url_shenzhen_zhishulist.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //存储数据
            MYSQLControl.insertshenzhen_zhishuStocks(shenzhen_zhishustocks);
        }
    }

	

}
