package home_equity_simulator;
import java.util.ArrayList; // Explicitly utilizing ArrayList typing
import java.util.Collections;

/**
 * The analytical core of the application. Processes collections of users, 
 * market zones, and lending programs to expose systemic equity gaps.
 */
public class AnalyticsEngine {

    /**
     * Evaluates a single user against all registered zip codes using their current 
     * underwriting approval status. Sorts results by wealth generation and prints 
     * a stark visual "Systemic Cut-Off Line."
     */
    public static void generateCityWideReport(User user, ArrayList<ZipCode> allZipCodes) {
        ArrayList<AreaReportCard> affordableTier = new ArrayList<>();
        ArrayList<AreaReportCard> pricedOutTier = new ArrayList<>();

        double totalPurchasingPower = user.getMaxApprovedLoanAmount() + user.getSavings();

        for (ZipCode zip : allZipCodes) {
            boolean affordable = zip.getAvgHomePrice() <= totalPurchasingPower;
            
            double projectedWealth = user.calculateWealthProjection(
                20, zip.getAvgAnnualAppreciation(), zip.getAvgHomePrice()
            );

            AreaReportCard card = new AreaReportCard(zip, projectedWealth, affordable);
            
            if (affordable) {
                affordableTier.add(card);
            } else {
                pricedOutTier.add(card);
            }
        }

        Collections.sort(affordableTier);
        Collections.sort(pricedOutTier);

        // Render Output with explicit Appreciation Rate Column
        System.out.println("\n=====================================================================================");
        System.out.printf("       20-YEAR GENERATIONAL WEALTH & ACCESSIBILITY REPORT FOR: %s\n", user.getName().toUpperCase());
        System.out.println("=====================================================================================");
        System.out.printf("%-10s | %-25s | %-11s | %-10s | %-18s\n", "ZIP CODE", "NEIGHBORHOOD / COUNTY", "HOME PRICE", "APPR. RATE", "20-YR WEALTH EST.");
        System.out.println("-------------------------------------------------------------------------------------");

        for (AreaReportCard card : affordableTier) {
            System.out.printf("%-10s | %-25s | $%,-10.0f | %-9.1f%% | $%,.2f\n", 
                card.zipCode.getZipCode(), 
                card.zipCode.getCountyName() + ", " + card.zipCode.getState(), 
                card.zipCode.getAvgHomePrice(), 
                card.zipCode.getAvgAnnualAppreciation() * 100, // Displays as e.g. 5.5%
                card.projectedWealth);
        }

        if (!pricedOutTier.isEmpty()) {
            System.out.println("\n========================== SYSTEMIC AFFORDABILITY CUT-OFF ==========================");
            System.out.println("⚠️  Your financial profile is structurally excluded from the following high-equity tiers:\n");
            
            for (AreaReportCard card : pricedOutTier) {
                System.out.printf("%-10s | %-25s | $%,-10.0f | %-9.1f%% | $%,.2f (PRICED OUT)\n", 
                    card.zipCode.getZipCode(), 
                    card.zipCode.getCountyName() + ", " + card.zipCode.getState(), 
                    card.zipCode.getAvgHomePrice(), 
                    card.zipCode.getAvgAnnualAppreciation() * 100,
                    card.projectedWealth);
            }
        }
        System.out.println("=====================================================================================\n");
    }

    /**
     * Compares two distinct user profiles across every individual lending ruleset.
     * Maps out the specific neighborhood appreciation rate that drove the calculation.
     */
    public static void compareTwoUsers(User user1, User user2, ArrayList<ZipCode> registry, ArrayList<LendingProgram> programs) {
        System.out.println("\n===============================================================================================");
        System.out.println("                 INTERGENERATIONAL WEALTH DISPARITY BY LENDING INSTITUTION                     ");
        System.out.println("===============================================================================================");
        System.out.printf("%-35s | %-24s | %-24s | %-15s\n", 
            "LENDING PROGRAM", 
            user1.getName().toUpperCase() + " WEALTH (RATE)", 
            user2.getName().toUpperCase() + " WEALTH (RATE)", 
            "OPPORTUNITY GAP");
        System.out.println("-----------------------------------------------------------------------------------------------");

        for (LendingProgram program : programs) {
            // Index 0 = Peak Wealth, Index 1 = Effective Appreciation Rate
            double[] details1 = getPeakWealthDetails(user1, registry, program);
            double[] details2 = getPeakWealthDetails(user2, registry, program);

            double delta = Math.abs(details2[0] - details1[0]);

            String user1Display = String.format("$%,.0f (%.1f%%)", details1[0], details1[1] * 100);
            String user2Display = String.format("$%,.0f (%.1f%%)", details2[0], details2[1] * 100);

            System.out.printf("%-35s | %-24s | %-24s | $%,-14.2f\n", 
                program.getProgramName(), 
                user1Display, 
                user2Display, 
                delta);
        }
        System.out.println("===============================================================================================\n");
    }

    private static double[] getPeakWealthDetails(User user, ArrayList<ZipCode> registry, LendingProgram program) {
        double peakWealth = user.getSavings() * Math.pow(1.0 + 0.02, 20); 
        double associatedRate = 0.02; // Default to baseline cash savings yield

        Underwriting.processApproval(user, program);
        double totalPurchasingPower = user.getMaxApprovedLoanAmount() + user.getSavings();

        for (ZipCode zip : registry) {
            if (zip.getAvgHomePrice() <= totalPurchasingPower) {
                double rate = zip.getAvgAnnualAppreciation();
                
                // Track dynamic policy adjustments for the ProgressiveLender structure
                if (program instanceof ProgressiveLender && rate < 0.055) {
                    rate = 0.055; 
                }

                double projected = user.calculateWealthProjection(20, rate, zip.getAvgHomePrice());
                if (projected > peakWealth) {
                    peakWealth = projected;
                    associatedRate = rate;
                }
            }
        }
        return new double[]{peakWealth, associatedRate};
    }


    /**
     * Evaluates a single applicant sequentially across all 3 lending programs, 
     * outputting a multi-column side-by-side market accessibility map including baseline rates.
     */
    public static void compareLendersForSingleUser(User user, ArrayList<ZipCode> registry, 
                                                   LendingProgram commercial, 
                                                   LendingProgram equitable, 
                                                   LendingProgram progressiveLender) {
        
        Underwriting.processApproval(user, commercial);
        double commBudget = user.getMaxApprovedLoanAmount() + user.getSavings();

        Underwriting.processApproval(user, equitable);
        double eqBudget = user.getMaxApprovedLoanAmount() + user.getSavings();

        Underwriting.processApproval(user, progressiveLender);
        double progressiveBudget = user.getMaxApprovedLoanAmount() + user.getSavings();

        System.out.println("\n=================================================================================================");
        System.out.printf("               INSTITUTIONAL LENDING IMPACT MATRIX FOR: %s\n", user.getName().toUpperCase());
        System.out.println("=================================================================================================");
        System.out.printf("%-8s | %-10s | %-9s | %-18s | %-18s | %-18s\n", "ZIP CODE", "PRICE", "BASE RATE", "COMMERCIAL BANK", "EQUITABLE FTB", "PROGRESSIVE LENDER");
        System.out.println("-------------------------------------------------------------------------------------------------");

        for (ZipCode zip : registry) {
            String commCell = (zip.getAvgHomePrice() <= commBudget) ? "ALLOWED" : "PRICED OUT";
            String eqCell = (zip.getAvgHomePrice() <= eqBudget) ? "ALLOWED" : "PRICED OUT";
            String progressiveCell = (zip.getAvgHomePrice() <= progressiveBudget) ? "ALLOWED" : "PRICED OUT";

            if (commCell.equals("ALLOWED")) {
                double w = user.calculateWealthProjection(20, zip.getAvgAnnualAppreciation(), zip.getAvgHomePrice());
                commCell = String.format("ALLOW ($%,.0fk)", w / 1000);
            }
            if (eqCell.equals("ALLOWED")) {
                double w = user.calculateWealthProjection(20, zip.getAvgAnnualAppreciation(), zip.getAvgHomePrice());
                eqCell = String.format("ALLOW ($%,.0fk)", w / 1000);
            }
            if (progressiveCell.equals("ALLOWED")) {
                double rate = Math.max(zip.getAvgAnnualAppreciation(), 0.055);
                double w = user.calculateWealthProjection(20, rate, zip.getAvgHomePrice());
                progressiveCell = String.format("ALLOW ($%,.0fk)*", w / 1000);
            }

            System.out.printf("%-8s | $%,-9.0f | %-8.1f%% | %-18s | %-18s | %-18s\n", 
                zip.getZipCode(), 
                zip.getAvgHomePrice(), 
                zip.getAvgAnnualAppreciation() * 100, 
                commCell, 
                eqCell, 
                progressiveCell);
        }
        System.out.println("=================================================================================================");
    }


    private static class AreaReportCard implements Comparable<AreaReportCard> {
        private final ZipCode zipCode;
        private final double projectedWealth;
        private final boolean isAffordable;

        public AreaReportCard(ZipCode zipCode, double projectedWealth, boolean isAffordable) {
            this.zipCode = zipCode;
            this.projectedWealth = projectedWealth;
            this.isAffordable = isAffordable;
        }

        @Override
        public int compareTo(AreaReportCard other) {
            return Double.compare(this.projectedWealth, other.projectedWealth);
        }
    }
}