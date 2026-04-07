package db.queryTest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import db.Error.DuplicateTokenException;
import db.Error.MissingTokenException;
import db.query.Query;
import db.query.queries.DatabaseCreateQueryBuilder;

public class QueryTest {

	@Test
	public void buildDbQuerySuccess() {
		Query dbCreateQuery = DatabaseCreateQueryBuilder.getNestedQueryBuilder().root().create().database("user", "abhi", "pass").BuildQuery();
				
		assertEquals(dbCreateQuery.getQuery().size(), 3);
	}
	
	@Test
	public void buildDbQueryFail1() {
		try {
			DatabaseCreateQueryBuilder.getNestedQueryBuilder().create().database("user", "abhi", "pass").BuildQuery();
		}
		catch(MissingTokenException e) {
			assertEquals(e.getMessage(), "Token Missing : ROOT before adding: CREATE");

		}
	}
	
	@Test
	public void buildDbQueryFail2() {
		try {
			DatabaseCreateQueryBuilder.getNestedQueryBuilder().root().root().create().database("user", "abhi", "pass").BuildQuery();
		}
		catch(DuplicateTokenException e) {
			assertEquals(e.getMessage(), "ERROR! Found duplicate token: ROOT");
		}
	}
	
	@Test
	public void buildDbQueryFail3() {
		try {
			DatabaseCreateQueryBuilder.getNestedQueryBuilder().root().create().create().database("user", "abhi", "pass").BuildQuery();
		}
		catch(DuplicateTokenException e) {
			assertEquals(e.getMessage(), "ERROR! Found duplicate token: CREATE");
			// db.Error.DuplicateTokenException: ERROR! Found duplicate token: CREATE

		}
	}
	
	@Test
	public void buildDbQueryFail4() {
		try {
			DatabaseCreateQueryBuilder.getNestedQueryBuilder().root().database("user", "abhi", "pass").BuildQuery();
		}
		catch(MissingTokenException e) {
			assertEquals(e.getMessage(), "Token Missing : CREATE before adding: DATABASE");

		}
	}
	
	@Test
	public void buildDbQueryFail5() {
		try {
			DatabaseCreateQueryBuilder.getNestedQueryBuilder().root().create().database("user", "abhi", "pass").database("user", "abhi", "pass").BuildQuery();
		}
		catch(DuplicateTokenException e) {
			assertEquals(e.getMessage(), "ERROR! Found duplicate token: DATABASE");
		}
	}
	
	@Test
	public void buildDbQueryFail6() {
		try {
			DatabaseCreateQueryBuilder.getNestedQueryBuilder().root().BuildQuery();
		}
		catch(MissingTokenException e) {
			assertEquals(e.getMessage(), "Token Missing : CREATE before building query");
			
			//db.Error.MissingTokenException: Token Missing : CREATE before building query
		}
	}
}
