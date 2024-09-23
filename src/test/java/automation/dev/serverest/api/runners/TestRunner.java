package automation.dev.serverest.api.runners;

import automation.dev.serverest.api.usecases.EditUserTest;
import automation.dev.serverest.api.usecases.GetUserTest;
import automation.dev.serverest.api.usecases.LoginTest;
import automation.dev.serverest.api.usecases.RegisterTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeTags("edituserRegression")
@DisplayName("Automação de Serviços - DevServe")
@SelectPackages("src.test.java.automation.dev.serverest.api.tests")
@SelectClasses({
        LoginTest.class,
        RegisterTest.class,
        EditUserTest.class,
        GetUserTest.class
})
public class TestRunner { }