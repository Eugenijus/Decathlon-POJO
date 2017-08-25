package info.eugenijus.strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * //run this test and all info.eugenijus.strategy.* test classes will run
 * @author Eugenijus
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ FieldFormulaTest.class, PlaceFormulaTest.class, TrackFormulaTest.class })
public class AllStrategyTests {
}
