package db.system.Validator;

import db.Error.WrongTokenFoundException;

public interface Validator<T> {

	public Boolean validate(T[] input);
}
