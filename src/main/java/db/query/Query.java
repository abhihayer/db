package db.query;

import java.util.List;

import db.query.token.tokenInterface.Token;

public record Query(List<Token> query, QueryType queryType) {

	public List<Token> getQuery() {
		return query;	
	}
}
