package automation.dev.serverest.api.runners;

import automation.dev.serverest.api.testCases.LoginTests;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ LoginTests.class })
@SelectPackages("src/test/java/automation/dev/serverest/api/tests")
@IncludeTags("regression")
public class TestRunner { }