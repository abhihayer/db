package db.query.token;

import db.query.token.tokenInterface.Token;

public record ROOT(Boolean isTokenTerminal) implements Token{
	
	@Override
	public String getToken() {
		return ROOT.class.getSimpleName();
	}

	@Override
	public Boolean hasTokenState() {
		return false;
	}
}
