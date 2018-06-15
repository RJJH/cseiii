package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import data.dataimpl;
import data.dataservice;

@Controller
public class PredictionController {
	dataservice dataservice = new dataimpl();
	
	@RequestMapping("forecast.spring")
	public String forecast(HttpSession session){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");                 //设置日期格式
		double[][] Matrix = Markov();
		double[] today = new double[4];
//		double pointtoday = dataservice.getPointByDate(df.format(new Date()));    //这是今日的指数,调用数据库获得
		double pointtoday = 3239.5;
		if(pointtoday<3081&&pointtoday>=3000){
			today[0]=1.0;
		}
		if(pointtoday<3161&&pointtoday>=3081){
			today[1]=1.0;
		}
		if(pointtoday<3241&&pointtoday>=3161){
			today[2]=1.0;
		}
		if(pointtoday<3321&&pointtoday>=3241){
			today[3]=1.0;
		}
		double[] result1 = cal(today,Matrix);
		double[] result2 = cal(result1,Matrix);
		double[] result3 = cal(result2,Matrix);
		double[] result4 = cal(result3,Matrix);
		double[] result5 = cal(result4,Matrix);
		double[] result6 = cal(result5,Matrix);
		double[] result7 = cal(result6,Matrix);
		double[] result8 = cal(result7,Matrix);
		double[] result9 = cal(result8,Matrix);
		double[] result10 = cal(result9,Matrix);
		
		session.setAttribute("day1", result1);
		session.setAttribute("day2", result2);
		session.setAttribute("day3", result3);
		session.setAttribute("day4", result4);
		session.setAttribute("day5", result5);
		session.setAttribute("day6", result6);
		session.setAttribute("day7", result7);
		session.setAttribute("day8", result8);
		session.setAttribute("day9", result9);
		session.setAttribute("day10", result10);
		
		
		return "Forecast.jsp";
	}
	
	public double[][] Markov(){
//		List<String> pointstemp = dataservice.getPointsByDuring("2017-03-01","2017-06-10");            //调用数据库的方法
		List<String> pointstemp = new ArrayList<String>();
		pointstemp.add("3020.5");
		pointstemp.add("3029.4");
		pointstemp.add("3085.5");
		pointstemp.add("3102.4");
		pointstemp.add("3150.2");
		pointstemp.add("3165.4");
		pointstemp.add("3200.5");
		pointstemp.add("3242.8");
		pointstemp.add("3305.7");
		List<Double> points = new ArrayList<Double>();
		for(String temp:pointstemp){
			points.add(Double.parseDouble(temp));
		}
		int[][] count = new int[4][4];       //设定四个区间为：3000~3080、3081~3160、3161~3240、3241~3320
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				count[i][j]=0;
			}
		}
		for(int i=0;i<points.size()-1;i++){
			if(points.get(i)<3081&&points.get(i)>=3000&&points.get(i+1)<3081&&points.get(i+1)>=3000){
				count[0][0]+=1;
			}
			if(points.get(i)<3081&&points.get(i)>=3000&&points.get(i+1)<3161&&points.get(i+1)>=3081){
				count[0][1]+=1;
			}
			if(points.get(i)<3081&&points.get(i)>=3000&&points.get(i+1)<3241&&points.get(i+1)>=3161){
				count[0][2]+=1;
			}
			if(points.get(i)<3081&&points.get(i)>=3000&&points.get(i+1)<3321&&points.get(i+1)>=3241){
				count[0][3]+=1;
			}
			if(points.get(i)<3161&&points.get(i)>=3081&&points.get(i+1)<3081&&points.get(i+1)>=3000){
				count[1][0]+=1;
			}
			if(points.get(i)<3161&&points.get(i)>=3081&&points.get(i+1)<3161&&points.get(i+1)>=3081){
				count[1][1]+=1;
			}
			if(points.get(i)<3161&&points.get(i)>=3081&&points.get(i+1)<3241&&points.get(i+1)>=3161){
				count[1][2]+=1;
			}
			if(points.get(i)<3161&&points.get(i)>=3081&&points.get(i+1)<3321&&points.get(i+1)>=3241){
				count[1][3]+=1;
			}
			if(points.get(i)<3241&&points.get(i)>=3161&&points.get(i+1)<3081&&points.get(i+1)>=3000){
				count[2][0]+=1;
			}
			if(points.get(i)<3241&&points.get(i)>=3161&&points.get(i+1)<3161&&points.get(i+1)>=3081){
				count[2][1]+=1;
			}
			if(points.get(i)<3241&&points.get(i)>=3161&&points.get(i+1)<3241&&points.get(i+1)>=3161){
				count[2][2]+=1;
			}
			if(points.get(i)<3241&&points.get(i)>=3161&&points.get(i+1)<3321&&points.get(i+1)>=3241){
				count[2][3]+=1;
			}
			if(points.get(i)<3321&&points.get(i)>=3241&&points.get(i+1)<3081&&points.get(i+1)>=3000){
				count[3][0]+=1;
			}
			if(points.get(i)<3321&&points.get(i)>=3241&&points.get(i+1)<3161&&points.get(i+1)>=3081){
				count[3][1]+=1;
			}
			if(points.get(i)<3321&&points.get(i)>=3241&&points.get(i+1)<3241&&points.get(i+1)>=3161){
				count[3][2]+=1;
			}
			if(points.get(i)<3321&&points.get(i)>=3241&&points.get(i+1)<3321&&points.get(i+1)>=3241){
				count[3][3]+=1;
			}
		}
		double[][] Marcov = new double[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				Marcov[i][j]= (double)(count[i][j]) / (double)(count[i][0]+count[i][1]+count[i][2]+count[i][3]);
			}
		}
		
		return Marcov;
		
	}
	public double[] cal(double[] today,double[][] Marcov){
		double[] res = new double[4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				res[i]+=today[j]*Marcov[j][i];
			}
		}
		return res;
	}
	

}
