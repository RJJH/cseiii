package model;
/**
 * @author:南京大学 软件学院 陈羿宗
 * @email:151250019@smail.nju.edu.cn
 * @ 
 */
public class HistoryStockModel {
	private String user_id;
	private String stock_id;
	private String stock_name;
	private String craw_time;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getStock_id() {
		return stock_id;
	}
	public void setStock_id(String stock_id) {
		this.stock_id = stock_id;
	}
	public String getStock_name() {
		return stock_name;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}
	public String getCraw_time() {
		return craw_time;
	}
	public void setCraw_time(String craw_time) {
		this.craw_time = craw_time;
	}

}
