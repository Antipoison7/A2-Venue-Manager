package application.Model.ObjectClasses;

public class SummaryPerClient {
	private String client;
	private double cost;
	private double commission;

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getCost() {
		return "$" + cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void addCost(double cost) 
	{
		this.cost += cost;
	}

	public String getCommission() {
		return "$" + commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	public void addCommission(double commission) 
	{
		this.commission += commission;
	}
}
