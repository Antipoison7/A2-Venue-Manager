package application.Model.ObjectClasses;

public class SummaryPerClient {
    private String client;
    private double cost;
    private String costString;
    private double commission;
    private String commissionString;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
        costString = "$" + cost;
    }
    
    public void addCost(double cost) {
        this.cost += cost;
        costString = "$" + this.cost;
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
}
