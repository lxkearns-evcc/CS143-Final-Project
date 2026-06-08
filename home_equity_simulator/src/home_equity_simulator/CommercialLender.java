package home_equity_simulator;

/**
 * Models a standard corporate banking establishment enforcing strict credit barriers.
 */
public class CommercialLender implements LendingProgram {
    
    @Override
    public double calculateMaxLoan(User user) {
        // Strict credit gatekeeper rule
        if (user.getCreditScore() < 640) {
            return 0.0;
        }
        // Base calculation completely ignores liquid savings leverage
        double creditAdjustment = (780-user.getCreditScore())/140/1.5;
        
        return user.getAnnualIncome() * (4.17-creditAdjustment);
    }

    @Override
    public String getProgramName() {
        return "Standard Commercial Bank";
    }
}
