package home_equity_simulator;

/**
 * Models an alternative, community-oriented program built to minimize structural exclusion.
 */
public class FirstTimeBuyer implements LendingProgram {
    
    @Override
    public double calculateMaxLoan(User user) {
        // Bruised credit tier: scales risk terms down rather than issuing a flat denial
        if (user.getCreditScore() < 580) {
            return (user.getAnnualIncome() * 3.0) + (user.getSavings() * 1.5);
        }
        
        // Asset-leveraged standard tier
        return (user.getAnnualIncome() * 4.5) + (user.getSavings() * 2.0);
    }

    @Override
    public String getProgramName() {
        return "Equitable First-Time Buyer Assistance";
    }
}