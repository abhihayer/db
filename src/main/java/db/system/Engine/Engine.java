package db.system.Engine;

import db.data.result.ResultSet;
import db.query.Query;

import java.util.Optional;

import db.data.Record;

public interface Engine {
	
	public <T extends Record> Optional<ResultSet<T>> execute(Query query);
}
