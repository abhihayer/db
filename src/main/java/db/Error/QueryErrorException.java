package db.Error;

import java.lang.RuntimeException;

public class QueryErrorException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5167389200132462486L;

	public QueryErrorException() {
		super("Query Error: can't build this query");
	}
}
