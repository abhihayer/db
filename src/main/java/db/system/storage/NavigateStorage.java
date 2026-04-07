package db.system.storage;

import java.util.Map;

public interface NavigateStorage extends Storage {
	
	public Map<String,String> currentContainer();
	public Map<String,String> currentContainerSet();
	public String currentCollection();
	
	public boolean isCheckContainer(Map<String,String> container);
	public boolean isCheckContainerSet(Map<String,String> containerSet);
	public boolean isCheckCollection(String collection);
	
	public String selectContainer(Map<String,String> container);
	public String selectContainerSet(Map<String,String> containerSet);
	public String selectCollection(String collection);
}
