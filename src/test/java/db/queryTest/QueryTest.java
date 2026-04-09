package db.queryTest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import db.Error.QueryErrorException;
import db.Error.WrongTokenFoundException;
import db.query.queries.DatabaseCreateQuery;

public class QueryTest {

	@Test
	public void buildDbQuerySuccess() {
		DatabaseCreateQuery.getNestedQueryBuilder().root().create().database("user", "abhi", "pass").BuildQuery();
	}
	
	@Test
	public void buildDbQueryFail1() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().create().database("user", "abhi", "pass").BuildQuery();
		}
		catch(WrongTokenFoundException e) {
			assertEquals(e.getMessage(), "Wrong token found: CREATE after token: null, possible tokens: [ROOT]");

		}
	}
	
	@Test
	public void buildDbQueryFail2() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().root().root().create().database("user", "abhi", "pass").BuildQuery();
		}
		catch(WrongTokenFoundException e) {
			assertEquals(e.getMessage(), "Wrong token found: ROOT after token: ROOT, possible tokens: [CREATE]");
		}
	}
	
	@Test
	public void buildDbQueryFail3() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().root().create().create().database("user", "abhi", "pass").BuildQuery();
		}
		catch(WrongTokenFoundException e) {
			assertEquals(e.getMessage(), "Wrong token found: CREATE after token: CREATE, possible tokens: [DATABASE]");
		}
	}
	
	@Test
	public void buildDbQueryFail4() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().root().database("user", "abhi", "pass").BuildQuery();
		}
		catch(WrongTokenFoundException e) {
			assertEquals(e.getMessage(), "Wrong token found: DATABASE after token: ROOT, possible tokens: [CREATE]");

		}
	}
	
	@Test
	public void buildDbQueryFail5() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().root().create().database("user", "abhi", "pass").database("user", "abhi", "pass").BuildQuery();
		}
		catch(WrongTokenFoundException e) {
			assertEquals(e.getMessage(), "Wrong token found: DATABASE after token: DATABASE, possible tokens: []");
		}
	}
	
	@Test
	public void buildDbQueryFail6() {
		try {
			DatabaseCreateQuery.getNestedQueryBuilder().root().BuildQuery();
		}
		catch(QueryErrorException e) {
			assertEquals(e.getMessage(), "Query Error: can't build this query");
		}
	}
}
