package db.system.Engine;

import java.util.Optional;

import db.data.Record;
import db.data.result.ResultSet;
import db.query.Query;

public class EngineImpl implements Engine {
	
	
	@Override
	public <T extends Record> Optional<ResultSet<T>> execute(Query query) {
		
		return Optional.empty();
	}

}
