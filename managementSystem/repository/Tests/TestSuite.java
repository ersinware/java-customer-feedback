package repository.Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CategoryRepositoryTest.class,
        FeedbackRepositoryTest.class,
        UserRepositoryTest.class,
        ActionsTests.class
})

public class TestSuite {
}
