package db.system;

import db.system.Validator.Validator;
import db.query.token.tokenInterface.Token;
import db.system.Validator.QueryValidatorImpl;

public enum SystemValidatorFactory {

	QueryValidator;

	private final Validator<Token> queryValidator;
	
	SystemValidatorFactory() {
		this.queryValidator =  new QueryValidatorImpl();
	}

	public Validator<Token> validator(){
		return this.queryValidator;
	}
}
