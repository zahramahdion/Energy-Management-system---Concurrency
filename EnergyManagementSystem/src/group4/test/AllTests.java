package group4.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    MultipleExceptionsHandlerTest.class,
    RethrowExceptionHandlerTest.class,
    ResourceManagementHandlerTest.class,
    ChainingExceptionHandlerTest.class
})
public class AllTests {
}
