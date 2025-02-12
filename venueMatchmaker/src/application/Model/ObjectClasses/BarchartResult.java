package application.Model.ObjectClasses;

public class BarchartResult {
	private String title;
	private double cost;
	private double commission;
	
	public BarchartResult(String title, double cost, double commission) 
	{
		this.title = title;
		this.cost = cost;
		this.commission = commission;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}
}
