package model;
/**
 * @author:南京大学 软件学院 陈羿宗
 * @email:151250019@smail.nju.edu.cn
 * @ 
 */
/*
 *date                      日期
 *open;                     开盘价
 *close;                    收盘价
 *high;                     最高价
 *low;                      最低价
 *volume                    成交量
 *code;                     股票代码
 */
public class AllYearsStockModel {
	    private String date;
	    private String open;
	    private String close;
	    private String high;
	    private String low;
	    private String volume;
	    private String code;
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getOpen() {
			return open;
		}
		public void setOpen(String open) {
			this.open = open;
		}
		public String getClose() {
			return close;
		}
		public void setClose(String close) {
			this.close = close;
		}
		public String getHigh() {
			return high;
		}
		public void setHigh(String high) {
			this.high = high;
		}
		public String getLow() {
			return low;
		}
		public void setLow(String low) {
			this.low = low;
		}
		public String getVolume() {
			return volume;
		}
		public void setVolume(String volume) {
			this.volume = volume;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
	   
	   
}
