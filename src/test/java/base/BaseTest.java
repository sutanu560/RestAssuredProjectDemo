package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.LoggerUtil;

public class BaseTest {

    @BeforeSuite
    public void setup() {
        LoggerUtil.info("--- Test Suite Execution Started ---");
    }
    
    @AfterSuite
    public void tearDown() {
        LoggerUtil.info("--- Test Suite Execution Finished ---");
    }
}
