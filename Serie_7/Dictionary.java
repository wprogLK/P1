import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Dictionary
{
	private HashMap<String, List<String>> map;
	private final boolean override = false;
	
	public Dictionary()
	{
		this.map = new HashMap<String, List<String>>();
	}
	
	public void addTranslations(String key, String[] values)
	{
		List<String> newValues = new ArrayList<String>(Arrays.asList(values));
		
		if (!map.containsKey(key) || override) 
			this.map.put(key, newValues);
		else
			(this.map.get(key)).addAll(newValues);	
	}

	public List<String> translate(String word) throws WordNotFoundException
	{
		if (!map.containsKey(word))
			throw new WordNotFoundException("Word not found!");
		
		return map.get(word) ;
	}
}
