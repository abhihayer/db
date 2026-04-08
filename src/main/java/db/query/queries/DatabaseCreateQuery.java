package db.query.queries;

import java.util.ArrayList;
import java.util.List;

import db.Error.QueryErrorException;
import db.query.Query;
import db.query.QueryType;
import db.query.helper.ValidToken;
import db.query.token.*;
import db.query.token.tokenInterface.Token;
import db.system.SystemValidatorFactory;
import db.system.Validator.Validator;

public class DatabaseCreateQuery{

	public static NestedQueryBuilder getNestedQueryBuilder() {
		
		return new NestedQueryBuilder();
	}
	
	public static class NestedQueryBuilder {
		List<Token> query = new ArrayList<>();
		Validator<Token> queryValidator = SystemValidatorFactory.QueryValidator.validator();

		public NestedQueryBuilder root() {
			Token token = new ROOT();
			
			if(addValidatedToken(token)) return this;
			
			throw new QueryErrorException();
		
		}
		
		public NestedQueryBuilder create() {
			Token token = new CREATE();
			
			if(addValidatedToken(token)) return this;
			
			throw new QueryErrorException();
		}
		
		
		public NestedQueryBuilder database(String dbName, String username, String pass) {
			Token token = new DATABASE(dbName, username, pass);
			
			if(addValidatedToken(token)) return this;
			
			throw new QueryErrorException();
		}

		private Boolean addValidatedToken(Token token) {
			if(ValidToken.extractedCommonValidate(token, query, queryValidator)) {
				query.add(token);
				return true;
			}
			return false;
		}

		public Query BuildQuery() {
			for(Token token: query) {
				if(! ValidToken.extractedCommonValidate(token, query, queryValidator)) 
					throw new QueryErrorException();
			}
			
			return new Query(query, QueryType.SCHEMA_MUTATION);
		}
	}
}
