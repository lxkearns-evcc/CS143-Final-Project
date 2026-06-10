package home_equity_simulator;

import java.util.Scanner;

/**
 * Handles the educational component of the application. 
 * Houses the standalone documentation menu and resource texts.
 */
public class Information {

    /**
     * Executes the isolated educational sub-menu loop.
     * * @param scanner The central application input stream passed from Main
     */
    public static void displayMenu(Scanner scanner) {
        boolean inInfoMenu = true;
        
        while (inInfoMenu) {
            System.out.println("\n================================================================================");
            System.out.println("                         SYSTEM INFORMATION CENTER                              ");
            System.out.println("================================================================================");
            System.out.println("1. Understanding the Disinvestment & Devaluation Penalty");
            System.out.println("2. Redlining");
            System.out.println("3. Compound Annual Growth Rate (CAGR) in Housing");
            System.out.println("4. Comparing Commercial vs. Progressive Lending Philosophy");
            System.out.println("5. App Lending Criteria");
            System.out.println("6. Definitions");
            System.out.println("7. Data Sources");
            System.out.println("8. Return to Main Options Menu");
            System.out.print("Select a topic to read (1-4): ");

            String infoChoice = scanner.nextLine().trim();
            System.out.println("\n--------------------------------------------------------------------------------");

            switch (infoChoice) {
                case "1":
                    System.out.println("TOPIC: DISINVESTMENT & THE DEVALUATION PENALTY\n");
                    System.out.println("In urban macroeconomics, the devaluation penalty describes the structural drag");
                    System.out.println("imposed on property values within historically redlined or systematically neglected");
                    System.out.println("neighborhoods. Decades of low municipal infrastructure spending, commercial flight,");
                    System.out.println("and low commercial lending volumes create hard capital deficits.");
                    System.out.println("According to data from the Brookings Institution, homes in structurally devalued");
                    System.out.println("neighborhoods are undervalued by an average of 23% compared to identical homes");
                    System.out.println("situated in highly invested zones, suppressing compounding generational equity.");
                    break;
                case "2":
                	System.out.println("TOPIC: REDLINING\n");
                	System.out.println("Redlining is the systematic, discriminatory practice of denying financial services,\n"
                			+ " such as mortgages and insurance, to residents of specific neighborhoods based on their\n"
                			+ "race or ethnicity. This exclusionary practice forces systemic inequalities by disinvesting\n"
                			+ " in marginalized communities and preventing residents from building generational wealth.");
                	break;

                case "3":
                    System.out.println("TOPIC: COMPOUND ANNUAL GROWTH RATE (CAGR)\n");
                    System.out.println("Housing market values compound exponentially over time rather than growing linearly.");
                    System.out.println("The CAGR represents the precise smoothed rate at which real estate assets grow");
                    System.out.println("annually over the data's lifetime. Even microscopic variances in annual appreciation");
                    System.out.println("rates ripple into catastrophic divides over a 20-year loop.");
                    System.out.println("For instance, a $500,000 baseline home appreciating at 3% finishes at roughly $903,000.");
                    System.out.println("The exact same asset appreciating at 6.5% finishes at over $1.76 million, leaving");
                    System.out.println("a massive $850,000 wealth chasm triggered purely by structural location variance.");
                    break;

                case "4":
                    System.out.println("TOPIC: INSTITUTIONAL UNDERWRITING PHILOSOPHIES\n");
                    System.out.println("This system models three distinct structural pathways to financial inclusion:\n");
                    System.out.println("* Standard Commercial Bank: Focuses heavily on gatekeeping. Enforces strict asset");
                    System.out.println("  and credit minimums (e.g., 640 credit floor) to insulate corporate capital.");
                    System.out.println("* Equitable First-Time Buyer: Focuses on optimization. Softens credit score cutoffs");
                    System.out.println("  and actively leverages existing liquid savings to maximize consumer safety boundaries.");
                    System.out.println("* Progressive Lender Tier: Focuses on proactive systemic intervention. Leverages aggressive");
                    System.out.println("  multipliers, introduces matching cash grants, and stabilizes growth minimums to heal");
                    System.out.println("  the historic opportunity gaps exposed by the Zillow datasets.");
                    break;
                case "5":
                	System.out.println("TOPIC: APP LENDING CRITERIA\n");
                	System.out.println("Commercial Lender:");
                	System.out.println("\tCredit Score < 640 = DENIED");
                	System.out.println("\tCredit Score > 640 Loan amount varies from 3.5 - 4.17 times income");
                	System.out.println("\tdepending on credit score");
                	System.out.println("First Time Buyer Program:");
                	System.out.println("\tCredit Score < 580 THEN loan amount = 3 * income + 1.5 * savings");
                	System.out.println("\tCredit Score > 580 & < 640 THEN loan amount = 4.5 * income + 2.0 * savings");
                	System.out.println("Progressive Lender:");
                	System.out.println("\tNo credit score limit loan amount = income * 5");
                	System.out.println("\tAdds a public grant up to $20,000 based on savings");
                	break;
                case "6":
                	System.out.println("TOPIC: DEFINITIONS\n");
                	System.out.println("Credit Score: This is a three-digit rating that shows lenders how responsibly you manage debt.\n"
                			+ "\tA higher score proves you pay bills on time and makes it easier to get approved for future loans with\n"
                			+ "\tlower interest rates.");
                	System.out.println("Loan: A loan is a specific amount of money you borrow from a lender that must be paid back over time.\n"
                			+ "\tYou are required to pay back the original amount plus interest, which is the lender's fee for letting you\n"
                			+ "\tborrow the money.\n");
                	System.out.println("Mortgage: This is a specialized loan used specifically to purchase real estate or a home.\n"
                			+ "\tThe property itself serves as collateral, meaning the lender can take the home if you fail to make\n"
                			+ "\tyour monthly payments.\n");
                	System.out.println("Equity: Equity represents the actual dollar value of the property that you own outright.\n"
                			+ "\tIt is calculated by taking the current market value of your home and subtracting whatever amount\n"
                			+ "\tyou still owe on your mortgage.\n");
                	System.out.println("Credit Score: tells mortgage lenders how risky it is to lend you hundreds of thousands\n"
                			+ "\tof dollars to buy a house. A higher score proves you handle debt responsibly, which unlocks lower\n"
                			+ "\tinterest rates that can save you tens of thousands of dollars over the life of the loan.");
                	break;
                case "7":
                	System.out.println("TOPIC: DATA SOURCES");
                	System.out.println("Housing data was sourced from Zillow Research Data https://www.zillow.com/research/data/");
                	System.out.println("Dataset used is ZHVI Single-Family Homes Time Series ($)");
                	System.out.println("Appreciation rates represent data avg annual appreciation from 2001 - 2026");
                	break;
                case "8":
                    System.out.println("Returning to main menu framework...");
                    inInfoMenu = false;
                    break;

                default:
                    System.out.println("Invalid selection. Please enter a number between 1 and 7.");
                    break;
            }
            System.out.println("--------------------------------------------------------------------------------");
            if (inInfoMenu) {
                System.out.println("Press [ENTER] to return to the Information Center directory...");
                scanner.nextLine();
            }
        }
    }
}