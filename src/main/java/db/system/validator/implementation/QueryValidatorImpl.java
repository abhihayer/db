package db.system.validator.implementation;

import db.Error.WrongTokenFoundException;
import db.query.token.CREATE;
import db.query.token.DATABASE;
import db.query.token.ROOT;
import db.query.token.tokenInterface.Token;
import db.system.validator.QueryValidator;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class QueryValidatorImpl implements QueryValidator {
    
    private final Map<String, Set<String>> syntaxGraph;
    
    public QueryValidatorImpl() {
        this.syntaxGraph = new HashMap<>();
        initGrammar();
    }

    private void initGrammar() {
    	
        syntaxGraph.computeIfAbsent(ROOT.class.getSimpleName(), k -> new HashSet<>())
                   .add(CREATE.class.getSimpleName());
        
        syntaxGraph.computeIfAbsent(CREATE.class.getSimpleName(), k -> new HashSet<>())
                   .add(DATABASE.class.getSimpleName());
        
        syntaxGraph.putIfAbsent(DATABASE.class.getSimpleName(), new HashSet<>());
    }

    @Override
    public boolean validate(Token[] tokens){
        if (tokens == null || tokens.length == 0) {
            throw new IllegalArgumentException("Query tokens cannot be empty.");
        }
        
        String currentState = ROOT.class.getSimpleName();
        

        for (int i = 0; i < tokens.length; i++) {
            Token currentToken = tokens[i];
            String nextTokenName = currentToken.getToken();
            
            Set<String> allowedNextTokens = syntaxGraph.get(currentState);

            if (allowedNextTokens == null || !allowedNextTokens.contains(nextTokenName)) {
                throw new WrongTokenFoundException(currentToken, allowedNextTokens);
            }

            currentState = nextTokenName;
        }

        return true;
    }
}