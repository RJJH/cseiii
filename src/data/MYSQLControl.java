package data;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import model.StockModel;

/**
 * @author:南京大学 软件学院 陈羿宗
 * @email:151250019@smail.nju.edu.cn
 * @ 
 */
public class MYSQLControl{
    static final Log logger = LogFactory.getLog(MYSQLControl.class);

    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/datacollection");

    static QueryRunner qr = new QueryRunner(ds);
    //第一类方法
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
   
    //此种数据库操作方法需要优化
    public static int inserthushenAStocks ( List<StockModel> hushenstocks ) {

        Object[][] params = new Object[hushenstocks.size()][17];
        int c = 0;  //success number of update
        int[] sum;
        for ( int i = 0; i < hushenstocks.size(); i++ ){
            params[i][0] = hushenstocks.get(i).getDate();
            params[i][1] = hushenstocks.get(i).getStock_id();
            params[i][2] = hushenstocks.get(i).getStock_name();
            params[i][3] = hushenstocks.get(i).getStock_price();
            params[i][4] = hushenstocks.get(i).getStock_change();
            params[i][5] = hushenstocks.get(i).getStock_range();
            params[i][6] = hushenstocks.get(i).getStock_amplitude();
            params[i][7] = hushenstocks.get(i).getStock_trading_number();
            params[i][8] = hushenstocks.get(i).getStock_trading_value();
            params[i][9] = hushenstocks.get(i).getStock_yesterdayfinish_price();
            params[i][10] = hushenstocks.get(i).getStock_todaystart_price();
            params[i][11] = hushenstocks.get(i).getStock_max_price();
            params[i][12] = hushenstocks.get(i).getStock_min_price();
            params[i][13] = hushenstocks.get(i).getStock_fiveminuate_change();
            params[i][14] = hushenstocks.get(i).getCraw_time();
            params[i][15] = null;
            params[i][16] = null;
        }

        QueryRunner qr = new QueryRunner(ds);
        try {
            sum = qr.batch("INSERT INTO `datacollection`.`stock_hushen` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params);
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("沪深A板块数据入库完毕");

        return c;

    }
    //此种数据库操作方法需要优化
    public static int insertshangzhengAStocks ( List<StockModel> shangAstocks ) {

        int c = 0;  //success number of update
        int[] sum;
        Object[][] params1 = new Object[shangAstocks.size()][17];
        int c1 = 0; //success number of update
        for ( int i = 0; i < shangAstocks.size(); i++ ){
            params1[i][0] = shangAstocks.get(i).getDate();
            params1[i][1] = shangAstocks.get(i).getStock_id();
            params1[i][2] = shangAstocks.get(i).getStock_name();
            params1[i][3] = shangAstocks.get(i).getStock_price();
            params1[i][4] = shangAstocks.get(i).getStock_change();
            params1[i][5] = shangAstocks.get(i).getStock_range();
            params1[i][6] = shangAstocks.get(i).getStock_amplitude();
            params1[i][7] = shangAstocks.get(i).getStock_trading_number();
            params1[i][8] = shangAstocks.get(i).getStock_trading_value();
            params1[i][9] = shangAstocks.get(i).getStock_yesterdayfinish_price();
            params1[i][10] = shangAstocks.get(i).getStock_todaystart_price();
            params1[i][11] = shangAstocks.get(i).getStock_max_price();
            params1[i][12] = shangAstocks.get(i).getStock_min_price();
            params1[i][13] = shangAstocks.get(i).getStock_fiveminuate_change();
            params1[i][14] = shangAstocks.get(i).getCraw_time();
            params1[i][15] = null;
            params1[i][16] = null;
        }
        QueryRunner qr = new QueryRunner(ds);
        try {
        //插入的数据表及数据
            sum = qr.batch("INSERT INTO `datacollection`.`stock_shangA` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params1);

        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("上证A板块数据入库完毕");

        return c;

    }
    //此种数据库操作方法需要优化
    public static int insertshangzhengBStocks ( List<StockModel> shangBstocks ) {

        int c = 0;  //success number of update
        int[] sum;
        Object[][] params1 = new Object[shangBstocks.size()][17];
        int c1 = 0; //success number of update
        for ( int i = 0; i < shangBstocks.size(); i++ ){
            params1[i][0] = shangBstocks.get(i).getDate();
            params1[i][1] = shangBstocks.get(i).getStock_id();
            params1[i][2] = shangBstocks.get(i).getStock_name();
            params1[i][3] = shangBstocks.get(i).getStock_price();
            params1[i][4] = shangBstocks.get(i).getStock_change();
            params1[i][5] = shangBstocks.get(i).getStock_range();
            params1[i][6] = shangBstocks.get(i).getStock_amplitude();
            params1[i][7] = shangBstocks.get(i).getStock_trading_number();
            params1[i][8] = shangBstocks.get(i).getStock_trading_value();
            params1[i][9] = shangBstocks.get(i).getStock_yesterdayfinish_price();
            params1[i][10] = shangBstocks.get(i).getStock_todaystart_price();
            params1[i][11] = shangBstocks.get(i).getStock_max_price();
            params1[i][12] = shangBstocks.get(i).getStock_min_price();
            params1[i][13] = shangBstocks.get(i).getStock_fiveminuate_change();
            params1[i][14] = shangBstocks.get(i).getCraw_time();
            params1[i][15] = null;
            params1[i][16] = null;
        }
        QueryRunner qr = new QueryRunner(ds);
        try {
        //插入的数据表及数据
            sum = qr.batch("INSERT INTO `datacollection`.`stock_shangB` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params1);

        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("上证B板块数据入库完毕");

        return c;

    }
    //此种数据库操作方法需要优化
    public static int insertshenzhengAStocks ( List<StockModel> shenAstocks ) {

        int c = 0;  //success number of update
        int[] sum;
        Object[][] params1 = new Object[shenAstocks.size()][17];
        int c1 = 0; //success number of update
        for ( int i = 0; i < shenAstocks.size(); i++ ){
            params1[i][0] = shenAstocks.get(i).getDate();
            params1[i][1] = shenAstocks.get(i).getStock_id();
            params1[i][2] = shenAstocks.get(i).getStock_name();
            params1[i][3] = shenAstocks.get(i).getStock_price();
            params1[i][4] = shenAstocks.get(i).getStock_change();
            params1[i][5] = shenAstocks.get(i).getStock_range();
            params1[i][6] = shenAstocks.get(i).getStock_amplitude();
            params1[i][7] = shenAstocks.get(i).getStock_trading_number();
            params1[i][8] = shenAstocks.get(i).getStock_trading_value();
            params1[i][9] = shenAstocks.get(i).getStock_yesterdayfinish_price();
            params1[i][10] = shenAstocks.get(i).getStock_todaystart_price();
            params1[i][11] = shenAstocks.get(i).getStock_max_price();
            params1[i][12] = shenAstocks.get(i).getStock_min_price();
            params1[i][13] = shenAstocks.get(i).getStock_fiveminuate_change();
            params1[i][14] = shenAstocks.get(i).getCraw_time();
            params1[i][15] = null;
            params1[i][16] = null;
        }
        QueryRunner qr = new QueryRunner(ds);
        try {
        //插入的数据表及数据
            sum = qr.batch("INSERT INTO `datacollection`.`stock_shenA` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params1);

        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("深证A板块数据入库完毕");

        return c;

    }
    //此种数据库操作方法需要优化
    public static int insertshenzhengBStocks ( List<StockModel> shenBstocks ) {

        int c = 0;  //success number of update
        int[] sum;
        Object[][] params1 = new Object[shenBstocks.size()][17];
        int c1 = 0; //success number of update
        for ( int i = 0; i < shenBstocks.size(); i++ ){
            params1[i][0] = shenBstocks.get(i).getDate();
            params1[i][1] = shenBstocks.get(i).getStock_id();
            params1[i][2] = shenBstocks.get(i).getStock_name();
            params1[i][3] = shenBstocks.get(i).getStock_price();
            params1[i][4] = shenBstocks.get(i).getStock_change();
            params1[i][5] = shenBstocks.get(i).getStock_range();
            params1[i][6] = shenBstocks.get(i).getStock_amplitude();
            params1[i][7] = shenBstocks.get(i).getStock_trading_number();
            params1[i][8] = shenBstocks.get(i).getStock_trading_value();
            params1[i][9] = shenBstocks.get(i).getStock_yesterdayfinish_price();
            params1[i][10] = shenBstocks.get(i).getStock_todaystart_price();
            params1[i][11] = shenBstocks.get(i).getStock_max_price();
            params1[i][12] = shenBstocks.get(i).getStock_min_price();
            params1[i][13] = shenBstocks.get(i).getStock_fiveminuate_change();
            params1[i][14] = shenBstocks.get(i).getCraw_time();
            params1[i][15] = null;
            params1[i][16] = null;
        }
        QueryRunner qr = new QueryRunner(ds);
        try {
        //插入的数据表及数据
            sum = qr.batch("INSERT INTO `datacollection`.`stock_shenB` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params1);

        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("深证B板块数据入库完毕");

        return c;

    }
    //此种数据库操作方法需要优化
    public static int insertchuangyebanStocks ( List<StockModel> chuangyestocks ) {

        int c = 0;  //success number of update
        int[] sum;
        Object[][] params1 = new Object[chuangyestocks.size()][17];
        int c1 = 0; //success number of update
        for ( int i = 0; i < chuangyestocks.size(); i++ ){
            params1[i][0] = chuangyestocks.get(i).getDate();
            params1[i][1] = chuangyestocks.get(i).getStock_id();
            params1[i][2] = chuangyestocks.get(i).getStock_name();
            params1[i][3] = chuangyestocks.get(i).getStock_price();
            params1[i][4] = chuangyestocks.get(i).getStock_change();
            params1[i][5] = chuangyestocks.get(i).getStock_range();
            params1[i][6] = chuangyestocks.get(i).getStock_amplitude();
            params1[i][7] = chuangyestocks.get(i).getStock_trading_number();
            params1[i][8] = chuangyestocks.get(i).getStock_trading_value();
            params1[i][9] = chuangyestocks.get(i).getStock_yesterdayfinish_price();
            params1[i][10] = chuangyestocks.get(i).getStock_todaystart_price();
            params1[i][11] = chuangyestocks.get(i).getStock_max_price();
            params1[i][12] = chuangyestocks.get(i).getStock_min_price();
            params1[i][13] = chuangyestocks.get(i).getStock_fiveminuate_change();
            params1[i][14] = chuangyestocks.get(i).getCraw_time();
            params1[i][15] = null;
            params1[i][16] = null;
        }
        QueryRunner qr = new QueryRunner(ds);
        try {
        //插入的数据表及数据
            sum = qr.batch("INSERT INTO `datacollection`.`stock_chuangye` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params1);

        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("创业板块数据入库完毕");

        return c;

    }
    //此种数据库操作方法需要优化
    public static int insertzhongxiaobanStocks ( List<StockModel> zhongxiaostocks ) {

        int c = 0;  //success number of update
        int[] sum;
        Object[][] params1 = new Object[zhongxiaostocks.size()][17];
        int c1 = 0; //success number of update
        for ( int i = 0; i < zhongxiaostocks.size(); i++ ){
            params1[i][0] = zhongxiaostocks.get(i).getDate();
            params1[i][1] = zhongxiaostocks.get(i).getStock_id();
            params1[i][2] = zhongxiaostocks.get(i).getStock_name();
            params1[i][3] = zhongxiaostocks.get(i).getStock_price();
            params1[i][4] = zhongxiaostocks.get(i).getStock_change();
            params1[i][5] = zhongxiaostocks.get(i).getStock_range();
            params1[i][6] = zhongxiaostocks.get(i).getStock_amplitude();
            params1[i][7] = zhongxiaostocks.get(i).getStock_trading_number();
            params1[i][8] = zhongxiaostocks.get(i).getStock_trading_value();
            params1[i][9] = zhongxiaostocks.get(i).getStock_yesterdayfinish_price();
            params1[i][10] = zhongxiaostocks.get(i).getStock_todaystart_price();
            params1[i][11] = zhongxiaostocks.get(i).getStock_max_price();
            params1[i][12] = zhongxiaostocks.get(i).getStock_min_price();
            params1[i][13] = zhongxiaostocks.get(i).getStock_fiveminuate_change();
            params1[i][14] = zhongxiaostocks.get(i).getCraw_time();
            params1[i][15] = null;
            params1[i][16] = null;
        }
        QueryRunner qr = new QueryRunner(ds);
        try {
        //插入的数据表及数据
            sum = qr.batch("INSERT INTO `datacollection`.`stock_zhongxiao` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params1);

        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("中小板块数据入库完毕");

        return c;
    }
    //此种数据库操作方法需要优化
    public static int insertxinguStocks ( List<StockModel> xingustocks ) {

        int c = 0;  //success number of update
        int[] sum;
        Object[][] params1 = new Object[xingustocks.size()][17];
        int c1 = 0; //success number of update
        for ( int i = 0; i < xingustocks.size(); i++ ){
            params1[i][0] = xingustocks.get(i).getDate();
            params1[i][1] = xingustocks.get(i).getStock_id();
            params1[i][2] = xingustocks.get(i).getStock_name();
            params1[i][3] = xingustocks.get(i).getStock_price();
            params1[i][4] = xingustocks.get(i).getStock_change();
            params1[i][5] = xingustocks.get(i).getStock_range();
            params1[i][6] = xingustocks.get(i).getStock_amplitude();
            params1[i][7] = xingustocks.get(i).getStock_trading_number();
            params1[i][8] = xingustocks.get(i).getStock_trading_value();
            params1[i][9] = xingustocks.get(i).getStock_yesterdayfinish_price();
            params1[i][10] = xingustocks.get(i).getStock_todaystart_price();
            params1[i][11] = xingustocks.get(i).getStock_max_price();
            params1[i][12] = xingustocks.get(i).getStock_min_price();
            params1[i][13] = xingustocks.get(i).getStock_fiveminuate_change();
            params1[i][14] = xingustocks.get(i).getCraw_time();
            params1[i][15] = null;
            params1[i][16] = null;
        }
        QueryRunner qr = new QueryRunner(ds);
        try {
        //插入的数据表及数据
            sum = qr.batch("INSERT INTO `datacollection`.`stock_xingu` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params1);

        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("新股板块数据入库完毕");

        return c;
    }
    //此种数据库操作方法需要优化
    public static int insertshanghai_zhishuStocks ( List<StockModel> shanghai_zhishustocks ) {

        int c = 0;  //success number of update
        int[] sum;
        Object[][] params1 = new Object[shanghai_zhishustocks.size()][17];
        int c1 = 0; //success number of update
        for ( int i = 0; i < shanghai_zhishustocks.size(); i++ ){
            params1[i][0] = shanghai_zhishustocks.get(i).getDate();
            params1[i][1] = shanghai_zhishustocks.get(i).getStock_id();
            params1[i][2] = shanghai_zhishustocks.get(i).getStock_name();
            params1[i][3] = shanghai_zhishustocks.get(i).getStock_price();
            params1[i][4] = shanghai_zhishustocks.get(i).getStock_change();
            params1[i][5] = shanghai_zhishustocks.get(i).getStock_range();
            params1[i][6] = shanghai_zhishustocks.get(i).getStock_amplitude();
            params1[i][7] = shanghai_zhishustocks.get(i).getStock_trading_number();
            params1[i][8] = shanghai_zhishustocks.get(i).getStock_trading_value();
            params1[i][9] = shanghai_zhishustocks.get(i).getStock_yesterdayfinish_price();
            params1[i][10] = shanghai_zhishustocks.get(i).getStock_todaystart_price();
            params1[i][11] = shanghai_zhishustocks.get(i).getStock_max_price();
            params1[i][12] = shanghai_zhishustocks.get(i).getStock_min_price();
            params1[i][13] = shanghai_zhishustocks.get(i).getStock_fiveminuate_change();
            params1[i][14] = shanghai_zhishustocks.get(i).getCraw_time();
            params1[i][15] = null;
            params1[i][16] = null;
        }
        QueryRunner qr = new QueryRunner(ds);
        try {
        //插入的数据表及数据
            sum = qr.batch("INSERT INTO `datacollection`.`stock_shanghai_zhishu` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params1);

        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("上海指数板块数据入库完毕");

        return c;
    }
    //此种数据库操作方法需要优化
    public static int insertshenzhen_zhishuStocks ( List<StockModel> shenzhen_zhishustocks ) {

        int c = 0;  //success number of update
        int[] sum;
        Object[][] params1 = new Object[shenzhen_zhishustocks.size()][17];
        int c1 = 0; //success number of update
        for ( int i = 0; i < shenzhen_zhishustocks.size(); i++ ){
            params1[i][0] = shenzhen_zhishustocks.get(i).getDate();
            params1[i][1] = shenzhen_zhishustocks.get(i).getStock_id();
            params1[i][2] = shenzhen_zhishustocks.get(i).getStock_name();
            params1[i][3] = shenzhen_zhishustocks.get(i).getStock_price();
            params1[i][4] = shenzhen_zhishustocks.get(i).getStock_change();
            params1[i][5] = shenzhen_zhishustocks.get(i).getStock_range();
            params1[i][6] = shenzhen_zhishustocks.get(i).getStock_amplitude();
            params1[i][7] = shenzhen_zhishustocks.get(i).getStock_trading_number();
            params1[i][8] = shenzhen_zhishustocks.get(i).getStock_trading_value();
            params1[i][9] = shenzhen_zhishustocks.get(i).getStock_yesterdayfinish_price();
            params1[i][10] = shenzhen_zhishustocks.get(i).getStock_todaystart_price();
            params1[i][11] = shenzhen_zhishustocks.get(i).getStock_max_price();
            params1[i][12] = shenzhen_zhishustocks.get(i).getStock_min_price();
            params1[i][13] = shenzhen_zhishustocks.get(i).getStock_fiveminuate_change();
            params1[i][14] = shenzhen_zhishustocks.get(i).getCraw_time();
            params1[i][15] = null;
            params1[i][16] = null;
        }
        QueryRunner qr = new QueryRunner(ds);
        try {
        //插入的数据表及数据
            sum = qr.batch("INSERT INTO `datacollection`.`stock_shenzhen_zhishu` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", params1);

        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("深圳指数板块数据入库完毕");

        return c;
    }
}