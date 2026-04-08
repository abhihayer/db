package db.queryTest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import db.Error.WrongTokenFoundException;
import db.query.Query;
import db.query.queries.DatabaseCreateQuery;

public class QueryTest {

	@Test
	public void buildDbQuerySuccess() {
		Query dbCreateQuery = DatabaseCreateQuery.getNestedQueryBuilder().root().create().database("user", "abhi", "pass").BuildQuery();
				
		assertEquals(dbCreateQuery.getQuery().size(), 3);
	}
	
	@Test
	public void buildDbQueryFail1() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().create().database("user", "abhi", "pass").BuildQuery();
		}
		catch(WrongTokenFoundException e) {
			assertEquals(e.getMessage(), "Token Missing : ROOT before adding: CREATE");

		}
	}
	
	@Test
	public void buildDbQueryFail2() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().root().root().create().database("user", "abhi", "pass").BuildQuery();
		}
		catch(WrongTokenFoundException e) {
			assertEquals(e.getMessage(), "ERROR! Found duplicate token: ROOT");
		}
	}
	
	@Test
	public void buildDbQueryFail3() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().root().create().create().database("user", "abhi", "pass").BuildQuery();
		}
		catch(WrongTokenFoundException e) {
			assertEquals(e.getMessage(), "ERROR! Found duplicate token: CREATE");
			// db.Error.WrongTokenFoundException: ERROR! Found duplicate token: CREATE

		}
	}
	
	@Test
	public void buildDbQueryFail4() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().root().database("user", "abhi", "pass").BuildQuery();
		}
		catch(WrongTokenFoundException e) {
			assertEquals(e.getMessage(), "Token Missing : CREATE before adding: DATABASE");

		}
	}
	
	@Test
	public void buildDbQueryFail5() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().root().create().database("user", "abhi", "pass").database("user", "abhi", "pass").BuildQuery();
		}
		catch(WrongTokenFoundException e) {
			assertEquals(e.getMessage(), "ERROR! Found duplicate token: DATABASE");
		}
	}
	
	@Test
	public void buildDbQueryFail6() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().root().BuildQuery();
		}
		catch(WrongTokenFoundException e) {
			assertEquals(e.getMessage(), "Token Missing : CREATE before building query");
			
			//db.Error.WrongTokenFoundException: Token Missing : CREATE before building query
		}
	}
}
