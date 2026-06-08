package home_equity_simulator;

public class ZipCode {
    private final String zipCode;
    private final String countyName;
    private final String state;
    private final double avgHomePrice;
    private final double avgAnnualAppreciation;

    public ZipCode(String zipCode, String countyName, String state, double avgHomePrice, double avgAnnualAppreciation) {
        this.zipCode = zipCode;
        this.countyName = countyName;
        this.state = state;
        this.avgHomePrice = avgHomePrice;
        this.avgAnnualAppreciation = avgAnnualAppreciation;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public String getState() {
        return state;
    }

    public double getAvgHomePrice() {
        return avgHomePrice;
    }

    public double getAvgAnnualAppreciation() {
        return avgAnnualAppreciation;
    }
	
}
