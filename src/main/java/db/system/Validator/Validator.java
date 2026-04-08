package db.system.Validator;

public interface Validator<T> {

	public Boolean validate(T[] input);
}
