package data;
/**
 * @author:南京大学 软件学院 陈羿宗
 * @email:151250019@smail.nju.edu.cn
 * @ 
 */
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class MyDataSource {

    public static DataSource getDataSource(String connectURI){

        BasicDataSource ds = new BasicDataSource();
         //MySQL的jdbc驱动
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");              //所要连接的数据库名
        ds.setPassword("");                //MySQL的登陆密码
        ds.setUrl(connectURI);

        return ds;

    }

}