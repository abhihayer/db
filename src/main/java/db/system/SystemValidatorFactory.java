package db.system;

import db.system.Validator.Validator;
import db.system.Validator.QueryValidatorImpl;

public enum SystemValidatorFactory {

	QueryValidator;

	private final Validator queryValidator;
	
	public QueryValidator() {
		this.queryValidator =  new QueryValidatorImpl();
	}

	public Validator validator(){
		return this.queryValidator;
	}
}
