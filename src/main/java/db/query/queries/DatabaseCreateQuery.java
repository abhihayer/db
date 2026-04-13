package db.query.queries;

import java.util.ArrayList;
import java.util.List;

import db.Error.QueryErrorException;
import db.query.Query;
import db.query.QueryType;
import db.query.token.*;
import db.query.token.tokenInterface.Token;
import db.system.validator.QueryValidator;

public class DatabaseCreateQuery {

    public static NestedQueryBuilder getNestedQueryBuilder(QueryValidator validator) {
        return new NestedQueryBuilder(validator);
    }
    
    public static class NestedQueryBuilder {
        private final List<Token> query = new ArrayList<>();
        private final QueryValidator validator;

        // Constructor Injection
        public NestedQueryBuilder(QueryValidator validator) {
            this.validator = validator;
        }
        
        public NestedQueryBuilder create() {
            query.add(new CREATE());
            return this;
        }
        
        public NestedQueryBuilder database(String dbName, String username, String pass) {
            query.add(new DATABASE(dbName, username, pass));
            return this;
        }

        public Query buildQuery() {
        	
            Token[] tokenArray = query.toArray(new Token[0]);
            
            
            boolean isValid = validator.validate(tokenArray);
            
            if (!isValid) {
                throw new QueryErrorException("Query failed FSM syntax validation.");
            }
            
            return new Query(query, QueryType.SCHEMA_MUTATION);
        }
    }
}