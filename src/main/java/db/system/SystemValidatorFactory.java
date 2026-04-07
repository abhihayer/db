package db.system;

import db.system.Validator.Validator;
import db.system.Validator.ValidatorImpl;

public enum SystemValidatorFactory {

	Validator;
	
	public static Validator Validator() {
		return new ValidatorImpl();
	}
}
