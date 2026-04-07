package db.system.storage;

public interface StorageStructure extends Storage {

	void createContainer(String container);
	void createContainerSet(String containerSet);
	void createCollection(String collection);

	void removeContainer(String container);
	void removeContainerSet(String containerSet);
	void removeCollection(String collection);

}