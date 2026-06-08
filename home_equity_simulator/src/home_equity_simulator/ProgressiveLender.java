package home_equity_simulator;
/**
 * Models a public policy housing equity intervention tier.
 */
public class ProgressiveLender implements LendingProgram {
    
    @Override
    public double calculateMaxLoan(User user) {
        // High baseline multiplier treating housing access as a stable priority
        double baseCalculatedLoan = user.getAnnualIncome() * 5.0;
        
        // Public policy intervention: matches user savings 1-for-1 up to a cap of $20,000
        double publicEquityGrant = Math.min(user.getSavings(), 20000.0);
        
        return baseCalculatedLoan + publicEquityGrant;
    }

    @Override
    public String getProgramName() {
        return "Progressive Lender";
    }
}