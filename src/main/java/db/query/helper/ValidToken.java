package db.query.helper;

import java.util.HashSet;
import java.util.Set;

import db.query.token.tokenInterface.Token;

public class ValidToken {
	
	Set<String> tokenSet = new HashSet<>();

	public boolean isTokenTypeAbsent(Class<? extends Token> cls) {
		return tokenSet.add(cls.getSimpleName());
	}
}