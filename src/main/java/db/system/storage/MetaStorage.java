package db.system.storage;

public interface MetaStorage extends Storage {

	void createMeta(String artifactName);
	void deleteMeta(String artifactName);
	
	void writeMetaData(String artifactName, String metaData);
	String readMetaData(String artifactName);
	String updateMetaData(String artifactName, String originalMetaDataProperty, String replaceMetaDataProperty);
}