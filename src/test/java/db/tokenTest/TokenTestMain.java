package db.tokenTest;

import db.query.token.tokenInterface.Token;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import db.query.token.*;

public class TokenTestMain {
	Token tokenRoot = new ROOT();
	Token tokenCreate = new CREATE();
	Token tokenDb = new DATABASE("user", "abhi", "pass");
	
	@Test
	public void getToken() {	
		assertEquals(tokenRoot.getToken(), "ROOT");
		assertEquals(tokenCreate.getToken(), "CREATE");
		assertEquals(tokenDb.getToken(), "DATABASE");
	}
	
	@Test
	public void getDBAttributes() {	
		assertEquals(tokenDb.getToken(), "DATABASE");
		assertEquals(((DATABASE)tokenDb).dbname(), "user");
		assertEquals(((DATABASE)tokenDb).username(), "abhi");
		assertEquals(((DATABASE)tokenDb).passward(), "pass");
	}
}
