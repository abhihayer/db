package db.query.helper;

import java.util.List;

import db.query.token.tokenInterface.Token;
import db.system.Validator.Validator;

public class ValidToken {
	
	public static Boolean extractedCommonValidate(Token token, List<Token> query, Validator<Token> queryValidator) {
		
		Token[] tokens = new Token[] { 
				query.isEmpty() ? null : query.get(query.size()-1), 
				token
				};
		
		if(queryValidator.validate(tokens)) {
			return true;
		}
		
		return false;
	}
	
	public static Boolean extractedCommonValidate(Token token, Token prevToken, Validator<Token> queryValidator) {
		
		Token[] tokens = new Token[] { 
				prevToken, 
				token
				};
		
		if(queryValidator.validate(tokens)) {
			return true;
		}
		
		return false;
	}
}