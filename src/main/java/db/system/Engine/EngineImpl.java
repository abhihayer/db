package db.system.Engine;

import java.util.Optional;

import db.data.Record;
import db.data.result.ResultSet;
import db.query.Query;
import db.system.SystemProcessorFactory;
import db.system.Processor.Processor;
import db.system.storage.MetaStorage;

public class EngineImpl implements Engine {
	
	@Override
	public <T extends Record> Optional<ResultSet<T>> execute(Query query) {
		
		Processor processor = SystemProcessorFactory.INSTANCE.Processor();
		
		return Optional.empty();
	}

}
