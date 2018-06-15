package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import data.dataimpl;
import data.dataservice;
import model.StockModel;

@Controller
public class AllstocksController {
	dataservice data = new dataimpl();
	@RequestMapping("hushen.spring")
	public String hushen(HttpSession session){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = sdf.format(new Date());
		List<StockModel> a = new ArrayList<StockModel>();
		a = data.getAllStocks("stock_hushen", date);
		return "allstock.html";
	}
	@RequestMapping("shanga.spring")
	public String shanga(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		List<StockModel> a = new ArrayList<StockModel>();
		a = data.getAllStocks("stock_shanga", date);
		return "allstock.html";
	}
	@RequestMapping("shangb.spring")
	public String shangb(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		List<StockModel> a = new ArrayList<StockModel>();
		a = data.getAllStocks("stock_shangb", date);
		return "allstock.html";
	}
	@RequestMapping("shena.spring")
	public String shena(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		List<StockModel> a = new ArrayList<StockModel>();
		a = data.getAllStocks("stock_shena", date);
		return "allstock.html";
	}
	@RequestMapping("shenb.spring")
	public String shenb(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		List<StockModel> a = new ArrayList<StockModel>();
		a = data.getAllStocks("stock_shenb", date);
		return "allstock.html";
	}
	@RequestMapping("shangzhi.spring")
	public String shangzhi(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		List<StockModel> a = new ArrayList<StockModel>();
		a = data.getAllStocks("stock_shangzhi", date);
		return "allstock.html";
	}
	@RequestMapping("shenzhi.spring")
	public String shenzhi(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		List<StockModel> a = new ArrayList<StockModel>();
		a = data.getAllStocks("stock_shenzhi", date);
		return "allstock.html";
	}
	@RequestMapping("chuangye.spring")
	public String chuangye(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		List<StockModel> a = new ArrayList<StockModel>();
		a = data.getAllStocks("stock_chuangye", date);
		return "allstock.html";
	}
	@RequestMapping("zhongxiao.spring")
	public String zhongxiao(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		List<StockModel> a = new ArrayList<StockModel>();
		a = data.getAllStocks("stock_zhongxiao", date);
		return "allstock.html";
	}
	@RequestMapping("new.spring")
	public String xingu(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		List<StockModel> a = new ArrayList<StockModel>();
		a = data.getAllStocks("stock_new", date);
		return "allstock.html";
	}
	@RequestMapping("fengxian.spring")
	public String fengxian(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());
		List<StockModel> a = new ArrayList<StockModel>();
		a = data.getAllStocks("stock_fengxian", date);
		return "allstock.html";
	}

}
