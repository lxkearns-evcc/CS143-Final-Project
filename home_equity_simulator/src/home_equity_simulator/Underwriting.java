package home_equity_simulator;
/**
 * Service class that handles processing applications against chosen lending programs.
 */
public class Underwriting {

    /**
     * Evaluates a user profile against a specific lending model and modifies the 
     * user's approved loan boundary.
     * * @param user    The homebuyer applying for financing
     * @param program The institutional framework evaluating the applicant
     */
    public static void processApproval(User user, LendingProgram program) {
        double approvedLimit = program.calculateMaxLoan(user);
        user.setMaxApprovedLoanAmount(approvedLimit);
    }
}