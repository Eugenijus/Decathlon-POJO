package info.eugenijus.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final String DOT = "\\.";
	public static final String COMMA = ",";
	public static final String COLON = ":";
	public static final String SEMICOLON = ";";
	public static final String TEST_FOLDER = "test-data/";
	public static final String STYLE_FOLDER = "style/";
	public static final String TEST_XML_FOLDER = TEST_FOLDER + "xml/";
	public static final String TEST_XSD_FOLDER = TEST_FOLDER + "xsd/";
	public static final String TEST_JUNIT_FOLDER = TEST_FOLDER + "junit-test/";
	public static final String XSL_STYLESHEET = "style.xsl";
	
	public static final char DOT_CHAR = '.';
	public static final char COLON_CHAR = ':';
	public static final char TAB = '\t';
	
	public static final String TRACK_100M 	= "100m"; 
	public static final String TRACK_400M 	= "400m"; 
	public static final String TRACK_110M 	= "110m"; 
	public static final String TRACK_1500M 	= "1500m";

	public static final String FIELD_LONG_JUMP		= "Long";
	public static final String FIELD_POLE_JUMP 		= "Pole";
	public static final String FIELD_HIGH_JUMP 		= "High";
	public static final String FIELD_SHOT_THROW 	= "Shot";
	public static final String FIELD_DISCUS_THROW 	= "Discus";
	public static final String FIELD_JAVELIN_THROW 	= "Javelin";
	
	public static final Map<String, Float> COLUMN_A = createPointsTableColumnA();	
	public static final Map<String, Float> COLUMN_B = createPointsTableColumnB();	
	public static final Map<String, Float> COLUMN_C = createPointsTableColumnC();
	
	/**
	 * http://www.theregister.co.uk/Design/page/hub/ibm2016/#five
	 */
	private static Map<String, Float> createPointsTableColumnA() {
		Map<String, Float> result = new HashMap<String, Float>();
		result.put(TRACK_100M,	  		new Float(25.4347f));
		result.put(TRACK_110M,    		new Float(5.74352f));
		result.put(TRACK_400M,    		new Float(1.53775f));
		result.put(TRACK_1500M,   		new Float(0.03768f));
		result.put(FIELD_DISCUS_THROW,  new Float(12.91f));
		result.put(FIELD_JAVELIN_THROW, new Float(10.14f));
		result.put(FIELD_SHOT_THROW,    new Float(51.39f));
		result.put(FIELD_LONG_JUMP,     new Float(0.14354f));
		result.put(FIELD_HIGH_JUMP,     new Float(0.8465f));
		result.put(FIELD_POLE_JUMP,     new Float(0.2797f));
        return Collections.unmodifiableMap(result);
    }
	
	/**
	 * http://www.theregister.co.uk/Design/page/hub/ibm2016/#five
	 */
	private static Map<String, Float> createPointsTableColumnB() {
		Map<String, Float> result = new HashMap<String, Float>();
		result.put(TRACK_100M,	  		new Float(18f));
		result.put(TRACK_110M,    		new Float(28.5f));
		result.put(TRACK_400M,    		new Float(82f));
		result.put(TRACK_1500M,   		new Float(480f));
		result.put(FIELD_DISCUS_THROW,  new Float(4f));
		result.put(FIELD_JAVELIN_THROW, new Float(7f));
		result.put(FIELD_SHOT_THROW,    new Float(1.5f));
		result.put(FIELD_LONG_JUMP,     new Float(220f));
		result.put(FIELD_HIGH_JUMP,     new Float(75f));
		result.put(FIELD_POLE_JUMP,     new Float(100f));

        return Collections.unmodifiableMap(result);
	}
	
	/**
	 * http://www.theregister.co.uk/Design/page/hub/ibm2016/#five
	 */
	private static Map<String, Float> createPointsTableColumnC() {
		Map<String, Float> result = new HashMap<String, Float>();
		result.put(TRACK_100M,	  new Float(1.81f));
		result.put(TRACK_110M,    new Float(1.92f));
		result.put(TRACK_400M,    new Float(1.81f));
		result.put(TRACK_1500M,   new Float(1.85f));
		result.put(FIELD_DISCUS_THROW,  new Float(1.1f));
		result.put(FIELD_JAVELIN_THROW, new Float(1.08f));
		result.put(FIELD_SHOT_THROW,    new Float(1.05f));
		result.put(FIELD_LONG_JUMP,    new Float(1.4f ));
		result.put(FIELD_HIGH_JUMP,    new Float(1.42f));
		result.put(FIELD_POLE_JUMP,    new Float(1.35f));
        return Collections.unmodifiableMap(result);
	}
}
