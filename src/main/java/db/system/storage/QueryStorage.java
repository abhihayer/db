package db.system.storage;

import java.util.List;
import java.util.Map;

public interface QueryStorage extends Storage {

	Map<String, String> readRecord();
	List<Map<String, String>> readAllRecords();

	Integer updateRecord(Map<String, String> record);
	List<Map<String, String>> updateMultipleRecords(List<Map<String, String>> recordList);

	Integer writeRecord(Map<String, String> record);
	void writeAllRecords(List<Map<String, String>> records);
	
	void deleteRecords(List<Integer> deleteList);
}