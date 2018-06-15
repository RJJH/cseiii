package model;

public class SimpleStockModel {
	private String name;
	private double AdjClose;
	private long volumn;
	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public SimpleStockModel(String name,double AdjClose,long volumn,String date){
		this.name=name;
		this.AdjClose=AdjClose;
		this.volumn=volumn;
		this.date=date;
	}

	public long getVolumn() {
		return volumn;
	}

	public void setVolumn(long volumn) {
		this.volumn = volumn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAdjClose() {
		return AdjClose;
	}

	public void setAdjClose(double adjClose) {
		AdjClose = adjClose;
	}
	
}
