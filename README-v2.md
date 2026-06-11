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
```
my-equity-simulator/                  # Project Root Directory
├── data/                             # Dedicated Market Datasets Directory
│   ├── King_County.csv
│   └── etc.
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
data_files/                           # Raw data files and python script to process
```
##🛠️ Prerequisites & Local Setup

THe project was not built with a framework. Unit testing is done with JUnit 5 standalone libraries.




