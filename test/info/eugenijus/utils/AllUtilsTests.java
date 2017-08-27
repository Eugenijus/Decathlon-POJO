package info.eugenijus.utils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CSVParserTest.class, HTMLWriterTest.class, JSONWriterTest.class, SSVParserTest.class,
		TxtWriterTest.class, XMLWriterTest.class })
public class AllUtilsTests {

}
