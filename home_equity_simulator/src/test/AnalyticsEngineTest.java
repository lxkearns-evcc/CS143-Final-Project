package test;

import home_equity_simulator.User;
import home_equity_simulator.ZipCodeLoader;
import home_equity_simulator.ZipCode;
import home_equity_simulator.AnalyticsEngine;
import home_equity_simulator.CommercialLender;
import home_equity_simulator.FirstTimeBuyer;
import home_equity_simulator.LendingProgram;
import home_equity_simulator.ProgressiveLender;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnalyticsEngineTest {
	private static final String DATA_DIRECTORY_PATH = "test_data_files";
	private Scanner createMockScanner(String inputData) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
        return new Scanner(inputStream);
       
    }
	private int countMatches(String mainText, String subText) {
        if (mainText == null || subText == null || subText.isEmpty()) return 0;
        int count = 0;
        int index = 0;
        while ((index = mainText.indexOf(subText, index)) != -1) {
            count++;
            index += subText.length();
        }
        return count;
    }
	@Test
	public void testAnalyticsGenerateCityWideReport() {
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputBuffer));
		Path testFile = Path.of(DATA_DIRECTORY_PATH,"fake_data.csv");
		ArrayList<ZipCode> testRegistry = ZipCodeLoader.loadMarketData(testFile);
		User testUser = new User("test",100000,20000,640);
		AnalyticsEngine.generateCityWideReport(testUser, testRegistry);
		String testOutput = outputBuffer.toString();
		
		String target = testOutput.toString();
		int countState = countMatches(target, "ZZ");
		int countPricedOut = countMatches(target,"PRICED OUT");
		assertEquals(countState,8,"The report should contain exactly 8 instances of \"ZZ\"");
		assertEquals(countPricedOut,8,"The report should contain exactly 8 instances of \"PRICED OUT\"");
		assertTrue(target.contains("20-YEAR GENERATIONAL WEALTH & ACCESSIBILITY REPORT"),"The title should be 20-Year generational gap");
		System.setOut(new PrintStream(originalOut));
	}
	
	@Test
	public void testAnalyticsCompareTwoUsers() {
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputBuffer));
		Path testFile = Path.of(DATA_DIRECTORY_PATH,"fake_data.csv");
		ArrayList<ZipCode> testRegistry = ZipCodeLoader.loadMarketData(testFile);
		User testUser1 = new User("test",100000,20000,640);
		User testUser2 = new User("test",150000,20000,600);
		LendingProgram commercial = new CommercialLender();
	    LendingProgram equitable = new FirstTimeBuyer();
	    LendingProgram socialJustice = new ProgressiveLender();
	
	    ArrayList<LendingProgram> allPrograms = new ArrayList<>();
	    allPrograms.add(commercial);
	    allPrograms.add(equitable);
	    allPrograms.add(socialJustice);
		AnalyticsEngine.compareTwoUsers(testUser1,testUser2, testRegistry,allPrograms);
		String testOutput = outputBuffer.toString();
		
		String target = testOutput.toString();
		int countState = countMatches(target, "ZZ");
		int countPricedOut = countMatches(target,"PRICED OUT");
		assertEquals(countState,8,"The report should contain exactly 8 instances of \"ZZ\"");
		assertEquals(countPricedOut,8,"The report should contain exactly 8 instances of \"PRICED OUT\"");
		assertTrue(target.contains("20-YEAR GENERATIONAL WEALTH & ACCESSIBILITY REPORT"),"The title should be 20-Year generational gap");
		System.setOut(new PrintStream(originalOut));
	}

	}
