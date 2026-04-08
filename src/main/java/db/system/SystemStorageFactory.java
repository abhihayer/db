package db.system;

import db.system.storage.Storage;

public enum SystemStorageFactory {

	Navigation,
	Structure,
	Meta,
	Query;
	
	public Navigation() {
		return null;
	}
}
