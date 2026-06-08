package home_equity_simulator;
/**
 * Defines the contract for polymorphic mortgage underwriting rulesets.
 */
public interface LendingProgram {
    
    /**
     * Calculates the maximum borrowable capital based on institutional criteria.
     * * @param user The applicant's financial profile
     * @return     The maximum approved loan amount in dollars
     */
    double calculateMaxLoan(User user);

    /**
     * Exposes the descriptive name of the program for reporting headers.
     * * @return The display name string
     */
    String getProgramName();
}