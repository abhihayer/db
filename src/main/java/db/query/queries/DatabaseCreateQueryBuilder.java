package db.query.queries;

import java.util.ArrayList;
import java.util.List;

import db.Error.DuplicateTokenException;
import db.Error.MissingTokenException;
import db.query.Query;
import db.query.QueryType;
import db.query.helper.ValidToken;
import db.query.token.*;
import db.query.token.tokenInterface.Token;

public class DatabaseCreateQueryBuilder{

	public static NestedQueryBuilder getNestedQueryBuilder() {
		
		return new NestedQueryBuilder();
	}
	
	public static class NestedQueryBuilder {
		List<Token> query = new ArrayList<>();
		ValidToken valid = new ValidToken();

		public NestedQueryBuilder root() {
			Token token = new ROOT();
			
			if(valid.isTokenTypeAbsent(ROOT.class)) {	
				this.query.add(token);
				return this;
			}
			
			throw new DuplicateTokenException(token);
		}
		
		public NestedQueryBuilder create() {
			Token token = new CREATE();
			
			if(valid.isTokenTypeAbsent(ROOT.class)) throw new MissingTokenException(ROOT.class, token);
			if(valid.isTokenTypeAbsent(CREATE.class)) {
				
				this.query.add(token);
				return this;
			}
			
			throw new DuplicateTokenException(token);
		}
		
		
		public NestedQueryBuilder database(String dbName, String username, String pass) {
			Token token = new DATABASE(dbName, username, pass);
			
			if(valid.isTokenTypeAbsent(ROOT.class)) throw new MissingTokenException(ROOT.class, token);
			if(valid.isTokenTypeAbsent(CREATE.class)) throw new MissingTokenException(CREATE.class, token);
			if(valid.isTokenTypeAbsent(DATABASE.class)) {
				
				this.query.add(token);
				return this;
			}
			
			throw new DuplicateTokenException(token);
		}

		public Query BuildQuery() {
			
			if(valid.isTokenTypeAbsent(ROOT.class)) throw new MissingTokenException(ROOT.class);
			if(valid.isTokenTypeAbsent(CREATE.class)) throw new MissingTokenException(CREATE.class);
			if(valid.isTokenTypeAbsent(DATABASE.class)) throw new MissingTokenException(DATABASE.class);
			
			return new Query(query, QueryType.SCHEMA_MUTATION);
		}
	}
}
