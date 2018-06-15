package jobmain;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import data.MYSQLControl;
import navi.main.StockMain;
import model.StockModel;
/**
 * @author:南京大学 软件学院 陈羿宗
 * @email:151250019@smail.nju.edu.cn
 * @ 
 */
public class StockJobMain {

    public void go() throws Exception { 
        // 首先，必需要取得一个Scheduler的引用 
        SchedulerFactory sf = new StdSchedulerFactory(); 
        Scheduler sched = sf.getScheduler(); 
        //jobs可以在scheduled的sched.start()方法前被调用 
        JobDetail job = newJob(StockMain.class).withIdentity("stockjob", "stockgroup").build(); 
        //每周一到周五8点39开始执行job
        CronTrigger trigger = newTrigger().withIdentity("stocktrigger", "stockgroup").withSchedule(cronSchedule("0 15 16 ? * MON-FRI")).build(); 
        Date ft = sched.scheduleJob(job, trigger); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS"); 
        System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression()); 
        sched.start(); 
    } 
    public static void main(String[] args) throws Exception { 
        StockJobMain maingo = new StockJobMain(); 
        maingo.go(); 
//        List<ExtMarketOilStockModel> randomlist = MYSQLControl.getListInfoBySQL("select stock_id,stock_price,stock_change from stock_shena where date = (select date from stock_shena order by date desc limit 1) ",ExtMarketOilStockModel.class);
//        System.out.println(randomlist.get(0));
    } 

}