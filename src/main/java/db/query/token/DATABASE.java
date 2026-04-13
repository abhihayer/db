package db.query.token;

import db.query.token.tokenInterface.Token;

public record DATABASE(
		String dbname, 
		String username, 
		String passward
		
	) implements Token {
	
	@Override
	public String getToken() {

		return DATABASE.class.getSimpleName();
	}
	
}
