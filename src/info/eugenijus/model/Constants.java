package info.eugenijus.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final String DOT = "\\.";
	public static final String COMMA = ",";
	public static final String COLON = ":";
	public static final String SEMICOLON = ";";
	
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
		result.put("100m",	  new Float(25.4347f));
		result.put("110m",    new Float(5.74352f));
		result.put("400m",    new Float(1.53775f));
		result.put("1500m",   new Float(0.03768f));
		result.put("Discus",  new Float(12.91f));
		result.put("Javelin", new Float(10.14f));
		result.put("Shot",    new Float(51.39f));
		result.put("Long",    new Float(0.14354f));
		result.put("High",    new Float(0.8465f));
		result.put("Pole",    new Float(0.2797f));
        return Collections.unmodifiableMap(result);
    }
	
	/**
	 * http://www.theregister.co.uk/Design/page/hub/ibm2016/#five
	 */
	private static Map<String, Float> createPointsTableColumnB() {
		Map<String, Float> result = new HashMap<String, Float>();
		result.put("100m",	  new Float(18f));
		result.put("110m",    new Float(28.5f));
		result.put("400m",    new Float(82f));
		result.put("1500m",   new Float(480f));
		result.put("Discus",  new Float(4f));
		result.put("Javelin", new Float(7f));
		result.put("Shot",    new Float(1.5f));
		result.put("Long",    new Float(220f));
		result.put("High",    new Float(75f));
		result.put("Pole",    new Float(100f));

        return Collections.unmodifiableMap(result);
	}
	
	/**
	 * http://www.theregister.co.uk/Design/page/hub/ibm2016/#five
	 */
	private static Map<String, Float> createPointsTableColumnC() {
		Map<String, Float> result = new HashMap<String, Float>();
		result.put("100m",	  new Float(1.81f));
		result.put("110m",    new Float(1.92f));
		result.put("400m",    new Float(1.81f));
		result.put("1500m",   new Float(1.85f));
		result.put("Discus",  new Float(1.1f));
		result.put("Javelin", new Float(1.08f));
		result.put("Shot",    new Float(1.05f));
		result.put("Long",    new Float(1.4f ));
		result.put("High",    new Float(1.42f));
		result.put("Pole",    new Float(1.35f));
        return Collections.unmodifiableMap(result);
	}
}
