# Housing Equity & Wealth Simulator

A professional-grade, terminal-driven housing equity simulation engine written in Java. This platform models systemic wealth divides, institutional underwriting boundaries, and the long-term compounding implications of housing policies across dynamic, county-level real estate datasets. Housing data was sourced from Zillow Research Data https://www.zillow.com/research/data/ Dataset: ZHVI Single-Family Homes Time Series ($) appreciation rates represent data avg annual appreciation from 2001 - 2026. 

---
## Object Model
1. **User**
   * Represents a prospective homeowner
   * Data is strictly about the user, no other objects present
3. **LenderProgram** - Interface that each lender class implements
   * CommericalLender - handles commercial lending criteria
   * FirstTimeBuyer - handles first time buyer criteria
   * ProgressiveLender - handles progressive lender criteria
4. **Underwriting**
   * Service class that assists with updating User maximum loan approval
5. **ZipCode**
   * Represents housing information for a zip code
7. **ZipCodeLoader**
   * data loader that pulls information from a file to create ZipCode objects
   * returns ArrayList<ZipCode>
9. **AnalyticsEngine**
    * Pulls variables from all objects to create reports
    * maintains separation of concerns
11. **Information**
    * Stores information about the app, definitions, ancillary and data sources
12. **Main**
    * Drives the application through a CLI
   
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

## 📁 Project Notes

Because this project operates as a traditional Java application without Maven or Gradle, dependencies and build pipelines are mapped relative to the absolute root directory:
##🛠️ Prerequisites & Local Setup

THe project was not built with a framework. Unit testing is done with JUnit 5 standalone libraries.




