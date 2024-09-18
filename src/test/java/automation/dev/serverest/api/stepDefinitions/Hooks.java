package automation.dev.serverest.api.stepDefinitions;

import automation.dev.serverest.api.base.BaseTest;
import io.cucumber.java.Before;

public class Hooks extends BaseTest {

    @Before
    public void InicializeTest() {
        BaseTest.setupRestAssured();
    }
}