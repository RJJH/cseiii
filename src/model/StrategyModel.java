package model;

import java.util.List;


//AnnualReturn       年化收益 [（投资内收益 / 本金）/ 投资天数] * 365 ×100%
//BaseAnnualReturn   基准年化收益
//MaxDrawdown        最大回撤 (在选定周期内任一历史时点往后推，产品净值走到最低点时的收益率回撤幅度的最大值)
//Beta               贝塔比率 (相当于业绩评价基准收益的总体波动性)
//Alpha              阿尔法比率(实际收益和按照Beta系数计算的期望收益之间的差额)
//SharpeRatio        夏普比率(代表投资人每多承担一分风险，可以拿到几分报酬)
//Postive_period     正周期数
//Negative_period    负周期数

public class StrategyModel {
	private double AnnualReturn;
	private double BaseAnnualReturn;
	private double MaxDrawdown;
	private double Beta;
	private double Alpha;
	private double SharpeRatio;

	private List<Double> Postive_period;
	private List<Double> Negative_period;

	public StrategyModel() {
		super();
	}

	public StrategyModel(double AnnualReturn, double BaseAnnualReturn, double MaxDrawdown, double Beta, double Alpha,
			double SharpeRatio,  List<Double> Postive_period,List<Double> Negative_period) {
		this.AnnualReturn = AnnualReturn;
		this.BaseAnnualReturn = BaseAnnualReturn;
		this.MaxDrawdown = MaxDrawdown;
		this.Beta = Beta;
		this.Alpha = Alpha;
		this.SharpeRatio = SharpeRatio;
		this.Postive_period = Postive_period;
		this.Negative_period = Negative_period;
	}

	public double getBaseAnnualReturn() {
		return BaseAnnualReturn;
	}

	public void setBaseAnnualReturn(double baseAnnualReturn) {
		BaseAnnualReturn = baseAnnualReturn;
	}

	public List<Double> getPostive_period() {
		return Postive_period;
	}

	public void setPostive_period(List<Double> postive_period) {
		Postive_period = postive_period;
	}

	public List<Double> getNegative_period() {
		return Negative_period;
	}

	public void setNegative_period(List<Double> negative_period) {
		Negative_period = negative_period;
	}

	public double getAnnualReturn() {
		return AnnualReturn;
	}

	public void setAnnualReturn(double annualReturn) {
		AnnualReturn = annualReturn;
	}

	public double getMaxDrawdown() {
		return MaxDrawdown;
	}

	public void setMaxDrawdown(double maxDrawdown) {
		MaxDrawdown = maxDrawdown;
	}

	public double getBeta() {
		return Beta;
	}

	public void setBeta(double beta) {
		Beta = beta;
	}

	public double getAlpha() {
		return Alpha;
	}

	public void setAlpha(double alpha) {
		Alpha = alpha;
	}

	public double getSharpeRatio() {
		return SharpeRatio;
	}

	public void setSharpeRatio(double sharpeRatio) {
		SharpeRatio = sharpeRatio;
	}
}
