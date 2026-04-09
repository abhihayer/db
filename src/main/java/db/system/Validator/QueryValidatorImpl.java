package db.system.Validator;

import db.Error.WrongTokenFoundException;
import db.query.token.CREATE;
import db.query.token.DATABASE;
import db.query.token.ROOT;
import db.query.token.tokenInterface.Token;

import java.util.Map;
import java.util.Set;
import java.lang.System.Logger;
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
	    
	    System.out.println(graph);
	}

	@Override
	public Boolean validate(Token[] token) {
		
		Set<String> possibleTokens = new HashSet<>();
		possibleTokens.add(ROOT.class.getSimpleName()); // Default starting point: Root
		
		if (token == null || token.length != 2) {
        	throw new IllegalArgumentException("Query must contain two tokens.");
    	}
    
		if(token[0] == null && token[1] != null) {
			if(token[1].getToken() == ROOT.class.getSimpleName())
				return true;
			else
				throw new WrongTokenFoundException(token[1], token[0], possibleTokens);
		}
		
		if (token[0] != null && token[1] != null) {
			possibleTokens = graph.get(token[0].getToken());
			
			if (possibleTokens != null && possibleTokens.contains(token[1].getToken())) {
				return true;
			}
		}
		
		throw new WrongTokenFoundException(token[1], token[0], possibleTokens);
	}
}
