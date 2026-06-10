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
                    Information.displayMenu(scanner);
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