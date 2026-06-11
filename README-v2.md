# Housing Equity & Wealth Simulator

A professional-grade, terminal-driven macroeconomic simulation engine written in Java. This platform models systemic wealth divides, institutional underwriting boundaries, and the long-term compounding implications of housing policies across dynamic, county-level real estate datasets.

Developed entirely on pure object-oriented design principles—without heavy third-party build frameworks—the application implements runtime polymorphism, structural encapsulation, strategy-driven policy enforcement, and decoupled test-driven verification pipelines.

---

## 🏛️ Core Architectural Overview

The system is engineered as a clean, multi-layered CLI utility, separated into dedicated functional boundaries to preserve the **Single Responsibility Principle (SRP)**:

1. **Domain Models (`home_equity_simulator`)**: Coordinates consumer capital boundaries (`User`) and parses localized structural metrics (`ZipCode`).
2. **Dynamic Data Engine (`ZipCodeLoader`)**: Implements dynamic dataset discovery, scanning the localized `/data` subdirectory for runtime market selection, and uses data-driven internal sorting to order listings by baseline home asset price.
3. **Polymorphic Lending Layer (`LendingProgram`)**: Employs the Strategy Pattern across three institutional archetypes:
   * `CommercialLender`: Enforces strict credit/asset gatekeeping to minimize corporate risk.
   * `FirstTimeBuyerProgram`: Optimizes consumer liquid asset leverage and relaxes constraints.
   * `ProgressiveLender` *(formerly SocialJusticeProgram)*: Introduces aggressive multiplier intervention, down-payment matching grants, and localized appreciation rate minimums.
4. **Analytical Processing Suite (`AnalyticsEngine`)**: Orchestrates multi-variable matrix operations, captures complex multi-user compounding disparities, tracks localized Compound Annual Growth Rate (CAGR) footprints, and maps strict "Systemic Affordability Cut-Off" boundaries.
5. **Decoupled Knowledge Layer (`InfoCenter`)**: A dedicated, text-heavy module executing an isolated loop to deliver contextual instruction on economic concepts such as the *Devaluation Penalty*.

---

## 📁 Project Directory Structure

Because this project operates as a traditional Java application without Maven or Gradle, dependencies and build pipelines are mapped relative to the absolute root directory:

Code output
README-v2.md generated successfully.

```text
my-equity-simulator/                  # Project Root Directory
├── data/                             # Dedicated Market Datasets Directory
│   ├── King_County.csv
│   └── Snohomish_County.csv
├── lib/                              # External Reference Libraries
│   └── junit-standalone.jar          # JUnit Platform Console Launcher Standalone JAR
└── src/                              # Source Code Tree
    ├── home_equity_simulator/        # Main Application Package Boundary
    │   ├── Main.java                 # Central Bootstrapper & CLI Orchestrator
    │   ├── AnalyticsEngine.java      # Matrix Multipliers & Report Synthesizer
    │   ├── User.java                 # Financial Profile & Compound Equations
    │   ├── ZipCode.java              # Regional Metric Immutable Data Shells
    │   ├── ZipCodeLoader.java        # I/O Stream Parser & File Scanner
    │   ├── LendingProgram.java       # Polymorphic Abstract Strategy Interface
    │   ├── CommercialLender.java     # Underwriting Concrete Strategy A
    │   ├── FirstTimeBuyerProgram.java# Underwriting Concrete Strategy B
    │   ├── ProgressiveLender.java    # Underwriting Concrete Strategy C
    │   ├── Underwriting.java         # Programmatic Gateway State Changer
    │   └── info/
    │       └── InfoCenter.java       # Structural Documentation Interface Loop
    ├── MainTest.java                 # Input Pipeline Boundary Validation Test Suite
    └── AnalyticsEngineTest.java      # Output-Capture Matcher Test Suite
🛠️ Prerequisites & Local Setup
1. Java Development Kit (JDK)
Ensure that you have JDK 11 or newer installed and configured within your environment variables:

Bash
java -version
2. Testing Framework Configuration (JUnit 5 Standalone)
To execute tests without an external build framework like Maven or Gradle, you must download the JUnit Platform Console Launcher Standalone JAR (v1.10.2 or newer recommended). This encapsulates the platform engine, Jupiter API, and console reporting mechanisms into a single deployable file.

Navigate to the Maven Central Standalone Launcher Repository.

Download the plain compilation .jar file.

Rename the downloaded file to junit-standalone.jar.

Place the file inside your local lib/ folder at the root of the project (lib/junit-standalone.jar).

🧪 Detailed Testing Architecture & JUnit 5 Integration
To thoroughly verify backend mechanics without third-party frameworks or an active GUI environment, the testing module implements advanced decoupling design patterns:

1. Dependency Injection Stream Testing (MainTest.java)
Instead of global stream manipulation (e.g., using System.setIn()), the application embraces a production-grade refactor where user input utilities accept a Scanner instance as a direct dependency parameter:

Isolation: This pattern allows unit tests to spin up self-contained ByteArrayInputStream strings containing simulated keyboard entry data, feeding a localized Scanner directly into Main.promptForInt() and Main.promptForDouble().

Stability: Eliminates global state leakage and prevents trailing test methods from catching unexpected NoSuchElementException crashes.

2. Console Output Interception & Verification (AnalyticsEngineTest.java)
Testing text-heavy spreadsheet reports generated by System.out.printf is performed via runtime stream routing:

Buffer Redirection: The test code dynamically swaps out System.out for an in-memory ByteArrayOutputStream buffer right before executing report methods.

Algorithmic String Evaluation: Once execution completes, the buffer converts to a standard Java String. The test suite runs a localized indexOf() loop (countMatches) to count instances of specific reporting output tags, verifying the structural accuracy of layout divisions like (PRICED OUT) and SYSTEMIC AFFORDABILITY CUT-OFF.

State Restitution: The actual console output channel is restored inside a strict finally block to guarantee terminal diagnostics survive an unexpected verification crash.

🚀 Compilation and Execution Guide
All commands must be executed from the absolute root directory (my-equity-simulator/) of your project.

1. Compile All Application Components and Unit Tests
The compilation command binds your source code files alongside the external JUnit JAR by leveraging the Java Classpath flag (-cp).

On Windows (CMD / PowerShell):

DOS
javac -cp ".;lib/junit-standalone.jar" src/home_equity_simulator/*.java src/home_equity_simulator/info/*.java src/*.java
On macOS / Linux:

Bash
javac -cp ".:lib/junit-standalone.jar" src/home_equity_simulator/*.java src/home_equity_simulator/info/*.java src/*.java
2. Launch the Main Interactive Simulation CLI
Run the compiled application bytecode by invoking the main class engine out of the compiled src/ directory tree:

Bash
java -cp src home_equity_simulator.Main
3. Execute the Automated Unit Test Suite via JUnit Launcher
Run the isolated test cases via JUnit’s native console launcher application. This tests both the input parsing filters (MainTest) and the stream-captured visual reporting tables (AnalyticsEngineTest).

On Windows (CMD / PowerShell):

DOS
java -cp ".;lib/junit-standalone.jar" org.junit.platform.console.ConsoleLauncher --select-package home_equity_simulator
On macOS / Linux:

Bash
java -cp ".:lib/junit-standalone.jar" org.junit.platform.console.ConsoleLauncher --select-package home_equity_simulator
