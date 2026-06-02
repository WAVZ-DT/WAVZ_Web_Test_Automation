# NewCompany Web Test Automation

Selenium + TestNG + Allure web test automation project.

## Project Structure

```
NewCompany_Web_Test_Automation/
├── pom.xml                              ← Maven dependencies & plugins
├── testng.xml                           ← TestNG suite configuration
├── TestData/
│   ├── Data/
│   │   └── login.json                   ← Login credentials (update before running)
│   ├── Data.xlsx                        ← Additional test data
│   └── ScreenShots/                     ← Auto-saved failure screenshots
└── src/
    ├── main/java/
    │   ├── Elements/                    ← Page locators (By objects)
    │   │   ├── LoginElements.java
    │   │   └── HomeElements.java
    │   └── Pages/                       ← Page action methods
    │       ├── PageBase.java            ← Shared Selenium helpers
    │       ├── LoginPage.java
    │       └── HomePage.java
    └── test/java/
        ├── Tests/
        │   ├── TestBase.java            ← Browser setup/teardown
        │   └── LoginTests.java
        └── Utils/
            ├── JsonReader.java          ← Read JSON test data
            ├── ExcelFileManager.java    ← Read Excel test data
            ├── Stable.java              ← Shared constants / URLs
            └── Helper.java             ← Random data, timestamps, etc.
```

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- Google Chrome + matching ChromeDriver (auto-managed by WebDriverManager)

### Setup Steps

1. **Update the application URL** in `src/test/java/Tests/TestBase.java`:
   ```java
   private static final String APP_URL = "https://your-actual-app-url.com";
   ```

2. **Update test credentials** in `TestData/Data/login.json`:
   ```json
   { "email": "testuser@company.com", "password": "TestPassword1!" }
   ```

3. **Add your page locators** in `src/main/java/Elements/` (replace the placeholder locators).

4. **Run the tests**:
   ```bash
   mvn clean test
   ```

5. **View the Allure report**:
   ```bash
   mvn allure:serve
   ```

## Adding New Pages

Follow the **Page Object Model** pattern:

1. Create `src/main/java/Elements/YourPageElements.java` — add `By` locators.
2. Create `src/main/java/Pages/YourPage.java` — extend `PageBase`, add action methods.
3. Create `src/test/java/Tests/YourPageTests.java` — extend `TestBase`, write `@Test` methods.
4. Register `YourPageTests` in `testng.xml`.
