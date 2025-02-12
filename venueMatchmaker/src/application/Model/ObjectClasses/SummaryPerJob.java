package application.Model.ObjectClasses;

public class SummaryPerJob {
	private int jobID;
	private String staff;
	private double overallCost;
	private double commission;

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getOverallCost() {
		return "$" + overallCost;
	}

	public void setOverallCost(double overallCost) {
		this.overallCost = overallCost;
	}
	
	public void addOverallCost(double overallCost) 
	{
		this.overallCost += overallCost;
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
