package db.system.Processor;

import java.util.Optional;

import db.data.Record;
import db.data.result.Result;
import db.data.result.ResultSet;
import db.query.Query;
import db.system.storage.Storage;

public class ProcessorReadImpl implements Processor {

	@Override
	public <T extends Record> Optional<ResultSet<T>> processResultSet(Query query, Storage... storage) {
		return Optional.empty();
	}

	@Override
	public <T extends Record> Optional<Result<T>> processResult(Query query, Storage... storage) {
		return Optional.empty();
	}
}
