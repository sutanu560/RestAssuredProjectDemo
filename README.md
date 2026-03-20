# RestAssured API Automation Framework

This is a professional API automation framework using **Java, RestAssured, and TestNG**. It follows industry best practices (Layered Architecture, Page Object Model approach for APIs) and demonstrates standard techniques for building a production-ready framework.

## 🚀 Features
- **Layered Architecture:** Clear separation of API layers, Models, Utilities, and Tests.
- **RestAssured + TestNG:** Robust combination for API testing and assertions.
- **Data-Driven Testing (DDT):** Read test data from Excel (`Apache POI`) and JSON.
- **Serialization & Deserialization:** Leverage POJO classes and Jackson.
- **Schema Validation:** Ensure response structures match pre-defined JSON schemas.
- **Reporting:** Integrated with Extent Reports for intuitive, modern HTML results.
- **Logging:** Log4j2 integration for extensive console and file logging.
- **CI/CD Ready:** Includes a GitHub Actions workflow to run tests automatically.

## 📁 Framework Structure
```
src/main/java
 ├── api          # API specific classes (Routes, SpecBuilder, Actions, etc.)
 ├── models       # POJO classes for Request/Response
 ├── utils        # Utilities: Excel, JSON, Config, Logging, Reporting
src/main/resources
 ├── config.properties
 ├── log4j2.xml
src/test/java
 ├── base         # BaseTest for setup/teardown hooks
 ├── tests        # TestNG test classes containing test scenarios
src/test/resources
 ├── schemas      # JSON schemas for validation
testdata
 ├── excel        # Excel files for DDT
 ├── json         # JSON files for payload test data
```

## 🛠 Prerequisites
- Java JDK 11+
- Maven 3.6+
- IDE (IntelliJ IDEA / Eclipse)

## ▶️ Setup & Execution

### 1. Clone the repository
```bash
git clone <repository_url>
cd RestAssuredDemoProject
```

### 2. Configure Environment
Update `src/main/resources/config.properties` if needed. Default targets `https://reqres.in`.

### 3. Run Tests via Maven
```bash
mvn clean test
```

### 4. View Reports
After execution, navigate to `target/ExtentReports/Report.html` and open it in any browser to see the complete test execution summary and logs.

## 💡 Best Practices Followed
- **Reusable API Calls:** All CRUD operations are abstracted out into specific API classes.
- **Independent Test Cases:** Every test is written cleanly, but dependencies to chain CRUD flows are also demonstrated.
- **Custom Extent Reporter Listener:** Extesses TestNG results natively without modifying test logic.
- **Environment Isolation:** Setup base URIs centrally and avoid hardcoded URLs in tests.
