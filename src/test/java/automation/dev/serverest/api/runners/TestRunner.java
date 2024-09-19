package automation.dev.serverest.api.runners;

import automation.dev.serverest.api.testCases.EditUserTest;
import automation.dev.serverest.api.testCases.GetUsersTest;
import automation.dev.serverest.api.testCases.LoginTest;
import automation.dev.serverest.api.testCases.RegisterTest;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        LoginTest.class,
        RegisterTest.class,
        EditUserTest.class,
        GetUsersTest.class
})
@SelectPackages("src/test/java/automation/dev/serverest/api/tests")
@IncludeTags("regression")
public class TestRunner { }