package application.Model.ObjectClasses;

public class SummaryPerJob {
    private int jobID;
    private String staff;
    private double overallCost;
    private String costString;
    private double commission;
    private String commissionString;

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

    public double getOverallCost() {
        return overallCost;
    }

    public void setOverallCost(double overallCost) {
        this.overallCost = overallCost;
        costString = "$" + overallCost;
    }

    public void addOverallCost(double overallCost) {
        this.overallCost += overallCost;
        costString = "$" + this.overallCost;
    }

    public String getCostString() {
        return costString;
    }

    public void setCostString(String costString) {
        this.costString = costString;
    }

    public String getCommissionString() {
        return commissionString;
    }

    public void setCommissionString(String commissionString) {
        this.commissionString = commissionString;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
        commissionString = "$" + commission;
    }

    public void addCommission(double commission) {
        this.commission += commission;
        commissionString = "$" + this.commission;
    }
}
