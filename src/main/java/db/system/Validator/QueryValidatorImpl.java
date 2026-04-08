package db.system.Validator;

import db.Error.WrongTokenFoundException;
import db.query.token.CREATE;
import db.query.token.DATABASE;
import db.query.token.ROOT;
import db.query.token.tokenInterface.Token;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class QueryValidatorImpl implements Validator<Token>{
	private final Map<String, Set<String>> graph = new HashMap<>();
	
	public QueryValidatorImpl() {
		init();
	}

	private void init() {
		
		// ROOT -> [CREATE]
	    graph.computeIfAbsent(ROOT.class.getSimpleName(), k -> new HashSet<>())
	         .add(CREATE.class.getSimpleName());
	    
	    // CREATE -> [DATABASE]
	    graph.computeIfAbsent(CREATE.class.getSimpleName(), k -> new HashSet<>())
	         .add(DATABASE.class.getSimpleName());
	    
	    // DATABASE -> [End of sequence, empty set]
	    graph.putIfAbsent(DATABASE.class.getSimpleName(), new HashSet<>());
	}

	@Override
	public Boolean validate(Token[] token) {
		
		Set<String> possibleTokens = null;
		
		if (token == null || token.length < 2) {
        	throw new IllegalArgumentException("Query must contain at least two tokens.");
    	}
    
		if (token[0] != null && token[1] != null) {
			possibleTokens = graph.get(token[0].getToken());
			
			if (possibleTokens != null && possibleTokens.contains(token[1].getToken())) {
				return true;
			}
		}
		
		throw new WrongTokenFoundException(token[0], token[1], possibleTokens);
	}
}
