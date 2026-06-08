package home_equity_simulator;

public class User {
	// Core Personal Demographics
	private final String name;

	// Core Financial Metrics (Used for Underwriting and Purchasing Power)
	private final double annualIncome;
	private final double savings;
	private final int creditScore;

	// Application State State (Mutated dynamically across different lending strategies)
	private double maxApprovedLoanAmount;

	/**
	 * Constructs a clean user profile. Maximum approved loan amount 
	 * defaults to 0.0 until explicitly processed by an underwriting module.
	 */
	public User(String name, double annualIncome, double savings, int creditScore) {
		this.name = name;
		this.annualIncome = annualIncome;
		this.savings = savings;
		this.creditScore = creditScore;
		this.maxApprovedLoanAmount = 0.0;
	}

	/**
	 * QUANTITATIVE METHOD: Generational Wealth Projection
	 * Models the compound growth of the household's net worth over a set timeline.
	 * It splits growth behaviors between the illiquid real estate asset and the 
	 * remaining liquid cash reserves.
	 *
	 * @param years             The duration of the simulation loop (e.g., 20)
	 * @param appreciationRate The effective compound annual growth rate (CAGR) of the neighborhood
	 * @param homePrice        The baseline purchase price of the property
	 * @return                  The projected cumulative net worth of the user asset portfolio
	 */
	public double calculateWealthProjection(int years, double appreciationRate, double homePrice) {
		// Compound growth calculation for the home equity value over time
		double projectedHomeEquity = homePrice * Math.pow(1.0 + appreciationRate, years);

		// Compound growth calculation assuming a conservative 2% baseline yield on cash assets
//		double projectedSavings = this.savings * Math.pow(1.0 + 0.02, years);

		return projectedHomeEquity;
	}

	public String getName() {
		return name;
	}

	public double getAnnualIncome() {
		return annualIncome;
	}

	public double getSavings() {
		return savings;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public double getMaxApprovedLoanAmount() {
		return maxApprovedLoanAmount;
	}

	/**
	 * Allows external institutional rulesets (LendingPrograms) to update 
	 * the financial boundary conditions of this profile based on their criteria.
	 */
	public void setMaxApprovedLoanAmount(double maxApprovedLoanAmount) {
		this.maxApprovedLoanAmount = maxApprovedLoanAmount;
	}
}
