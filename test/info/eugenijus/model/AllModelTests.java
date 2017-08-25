package info.eugenijus.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * //run this test and all info.eugenijus.model.* test classes will run
 * @author Eugenijus
 */
@RunWith(Suite.class)
@SuiteClasses({ AthleteTest.class, CustomTimeTest.class, ResultTest.class })
public class AllModelTests {

}
