package test;


import home_equity_simulator.Main;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    /**
     * Helper utility to quickly spin up an isolated Scanner instance 
     * seeded with mocked keyboard entries.
     */
    private Scanner createMockScanner(String inputData) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
        return new Scanner(inputStream);
    }

    // =================================================================
    // UNIT TESTS FOR: promptForInt(Scanner scanner, String prompt, int min, int max)
    // =================================================================

    @Test
    public void testPromptForInt_ValidInputWithinBounds() {
        // Mock a simple input string terminating with a newline [ENTER]
        Scanner scanner = createMockScanner("720\n");
        
        int result = Main.promptForInt("Enter Credit Score: ", 300, 850,scanner);
        
        assertEquals(720, result, "Should accept valid integer values immediately.");
    }

    @Test
    public void testPromptForInt_OutOfBoundsFollowedByValidInput() {
        // Sequence: 250 (under min) -> 900 (over max) -> 650 (valid)
        Scanner scanner = createMockScanner("250\n900\n650\n");
        
        int result = Main.promptForInt( "Enter Credit Score: ", 300, 850,scanner);
        
        assertEquals(650, result, "Should continuously loop and prompt until data falls within constraints.");
    }

    @Test
    public void testPromptForInt_TypeMismatchFollowedByValidInput() {
        // Sequence: alphabetic text strings -> 800 (valid integer)
        Scanner scanner = createMockScanner("excellent\nbad_data\n800\n");
        
        int result = Main.promptForInt("Enter Credit Score: ", 300, 850,scanner);
        
        assertEquals(800, result, "Should gracefully catch NumberFormatException and maintain execution loop.");
    }

    // =================================================================
    // UNIT TESTS FOR: promptForDouble(Scanner scanner, String prompt)
    // =================================================================

    @Test
    public void testPromptForDouble_ValidDecimalInput() {
        Scanner scanner = createMockScanner("125000.75\n");
        
        double result = Main.promptForDouble("Enter Income: $",scanner);
        
        assertEquals(125000.75, result, 0.001, "Should cleanly parse standard decimal entries.");
    }

    @Test
    public void testPromptForDouble_TypeMismatchFollowedByValidDouble() {
        // Sequence: strings containing non-numeric characters -> 95000.00 (valid double)
        Scanner scanner = createMockScanner("$125,000\ncorrupted_text\n95000.00\n");
        
        double result = Main.promptForDouble("Enter Income: $",scanner);
        
        assertEquals(95000.00, result, 0.001, "Should skip malformed data until a clean parsable double is typed.");
    }
}