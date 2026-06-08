package home_equity_simulator;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("                        HOUSING EQUITY & WEALTH SIMULATOR                       ");
        System.out.println("================================================================================");

        // 1. Dynamic Dataset Discovery Phase
        ArrayList<Path> availableFiles = ZipCodeLoader.detectAvailableDatasets();
        
        if (availableFiles.isEmpty()) {
            System.err.println("\nCritical Error: No regional .csv files detected in the root directory.");
            System.err.println("Please ensure data files (e.g., King_County.csv) are placed next to the src folder.");
            return;
        }

        System.out.println("\n[SYSTEM] market datasets detected. Please select a region to initialize:");
        for (int i = 0; i < availableFiles.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), availableFiles.get(i).getFileName());
        }
        
        int fileSelectionIndex = promptForInt("Select dataset index (1-" + availableFiles.size() + "): ", 1, availableFiles.size());
        Path chosenFileName = availableFiles.get(fileSelectionIndex - 1);
        
        // 2. Load the specific county chosen by the user
        ArrayList<ZipCode> regionalRegistry = ZipCodeLoader.loadMarketData(chosenFileName);
        System.out.println("\n[SUCCESS] Successfully initialized simulation environment with dataset: " + chosenFileName);
        System.out.println("Loaded " + regionalRegistry.size() + " unique regional market parameters.");
        System.out.println("================================================================================");

        // 3. Initialize Lending Strategies
        LendingProgram commercial = new CommercialLender();
        LendingProgram equitable = new FirstTimeBuyer();
        LendingProgram socialJustice = new ProgressiveLender();

        ArrayList<LendingProgram> allPrograms = new ArrayList<>();
        allPrograms.add(commercial);
        allPrograms.add(equitable);
        allPrograms.add(socialJustice);

        // 4. Main Simulation Loop
        boolean running = true;
        while (running) {
        	System.out.println("\nMAIN SIMULATION OPTIONS:");
            System.out.println("1. Run Single-User Lender Impact Matrix (Side-by-Side Comparison)");
            System.out.println("2. View City-Wide Accessibility Report (With Systemic Cut-Off Line)");
            System.out.println("3. Run Two-User Wealth Disparity Diagnostic (Systemic Opportunity Gap)");
            System.out.println("4. Access System Information Center (Read Conceptual Documentation)");
            System.out.println("5. Exit Simulation");
            System.out.print("Select an option (1-5): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    User singleUser = promptForUserProfile("");
                    AnalyticsEngine.compareLendersForSingleUser(
                        singleUser, regionalRegistry, commercial, equitable, socialJustice
                    );
                    break;

                case "2":
                    User standardUser = promptForUserProfile("");
                    System.out.println("\nSelect a lender to process your underwriting boundaries:");
                    System.out.println("1. Standard Commercial Bank");
                    System.out.println("2. Equitable First-Time Buyer Assistance");
                    System.out.println("3. Social Justice Intervention Tier");
                    System.out.print("Select (1-3): ");
                    
                    String lenderChoice = scanner.nextLine().trim();
                    LendingProgram selectedProgram = commercial;
                    if (lenderChoice.equals("2")) selectedProgram = equitable;
                    if (lenderChoice.equals("3")) selectedProgram = socialJustice;

                    Underwriting.processApproval(standardUser, selectedProgram);
                    AnalyticsEngine.generateCityWideReport(standardUser, regionalRegistry);
                    break;

                case "3":
                    System.out.println("\n--- CONFIGURING PROFILE 1 ---");
                    User user1 = promptForUserProfile("First ");
                    System.out.println("\n--- CONFIGURING PROFILE 2 ---");
                    User user2 = promptForUserProfile("Second ");
                    
                    AnalyticsEngine.compareTwoUsers(user1, user2, regionalRegistry, allPrograms);
                    break;

                case "4":
                    // Branch control out to the standalone educational information loop
                    handleInformationMenu();
                    break;

                case "5":
                    System.out.println("\nThank you for utilizing the housing equity simulator. Exiting baseline engine.");
                    running = false;
                    break;
                    	
                default:
                    System.out.println("Invalid selection. Please enter a number between 1 and 4.");
                    break;
            }
        }
        scanner.close();
    }
 // =================================================================
    // NEW COMPONENT: Standalone Educational Information Sub-Menu
    // =================================================================
    /**
     * Creates an isolated menu interface allowing users to explore the 
     * structural economic concepts backing the data workflows.
     */
    private static void handleInformationMenu() {
        boolean inInfoMenu = true;
        
        while (inInfoMenu) {
            System.out.println("\n================================================================================");
            System.out.println("                         SYSTEM INFORMATION CENTER                              ");
            System.out.println("================================================================================");
            System.out.println("1. Understanding the Disinvestment & Devaluation Penalty");
            System.out.println("2. Demystifying Compound Annual Growth Rate (CAGR) in Housing");
            System.out.println("3. Comparing Commercial vs. Progressive Lending Philosophy");
            System.out.println("4. Return to Main Options Menu");
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
                    System.out.println("TOPIC: COMPOUND ANNUAL GROWTH RATE (CAGR) & EXPONENTIAL DIVERGENCE\n");
                    System.out.println("Housing market values compound exponentially over time rather than growing linearly.");
                    System.out.println("The CAGR represents the precise smoothed rate at which real estate assets grow");
                    System.out.println("annually over the data's lifetime. Even microscopic variances in annual appreciation");
                    System.out.println("rates ripple into catastrophic divides over a 20-year loop.");
                    System.out.println("For instance, a $500,000 baseline home appreciating at 3% finishes at roughly $903,000.");
                    System.out.println("The exact same asset appreciating at 6.5% finishes at over $1.76 million, leaving");
                    System.out.println("a massive $850,000 wealth chasm triggered purely by structural location variance.");
                    break;

                case "3":
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

                case "4":
                    System.out.println("Returning to main menu framework...");
                    inInfoMenu = false;
                    break;

                default:
                    System.out.println("Invalid selection. Please enter a number between 1 and 4.");
                    break;
            }
            System.out.println("--------------------------------------------------------------------------------");
            if (inInfoMenu) {
                System.out.println("Press [ENTER] to return to the Information Center directory...");
                scanner.nextLine();
            }
        }
    }
    private static User promptForUserProfile(String prefix) {
        System.out.print("Enter " + prefix + "Applicant Name: ");
        String name = scanner.nextLine().trim();

        double income = promptForDouble("Enter Gross Annual Income: $");
        double savings = promptForDouble("Enter Capital Liquid Savings (Assets): $");
        int creditScore = promptForInt("Enter Credit Score (300-850): ", 300, 850);

        return new User(name, income, savings, creditScore);
    }

    private static double promptForDouble(String promptText) {
        while (true) {
            try {
                System.out.print(promptText);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric input. Please try again.");
            }
        }
    }

    private static int promptForInt(String promptText, int min, int max) {
        while (true) {
            try {
                System.out.print(promptText);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Input out of bounds. Please enter a value between %d and %d.\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry. Please enter a whole integer.");
            }
        }
    }
}