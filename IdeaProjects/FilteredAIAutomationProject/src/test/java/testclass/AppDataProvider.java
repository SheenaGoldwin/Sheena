package testclass;

import org.testng.annotations.DataProvider;

public class AppDataProvider {
    @DataProvider(name = "test3")
    public Object[][] createData3() {
        return new Object[][] {
                { "merlin1@gmail.com" }
        };
    }
}