package db.system.Processor;

import db.data.result.Result;
import db.data.result.ResultSet;
import db.query.Query;
import db.system.storage.Storage;

import java.util.Optional;

import db.data.Record;

public interface Processor {

	public <T extends Record> Optional<ResultSet<T>> processResultSet(Query query, Storage... storage);
	public <T extends Record> Optional<Result<T>> processResult(Query query, Storage... storage);
	
}