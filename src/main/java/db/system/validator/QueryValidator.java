package db.system.validator;

import db.query.token.tokenInterface.Token;

public interface QueryValidator extends Validator {
	
	public boolean validate(Token[] tokens);
}
