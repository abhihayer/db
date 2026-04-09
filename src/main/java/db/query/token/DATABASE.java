package db.query.token;

import db.query.token.tokenInterface.Token;

public record DATABASE(
		String dbname, 
		String username, 
		String passward,
		
		Boolean isTokenTerminal
	) implements Token {
	
	@Override
	public Boolean hasTokenState() {
		return true;
	}
	
	@Override
	public String getToken() {

		return DATABASE.class.getSimpleName();
	}
	
}
