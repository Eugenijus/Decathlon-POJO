package info.eugenijus.strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FieldFormulaTest.class, PlaceFormulaTest.class, TrackFormulaTest.class })
public class AllStrategyTests {
	//run this test and all test classes will run
}
