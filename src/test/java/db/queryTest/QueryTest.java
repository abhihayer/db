package db.queryTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import db.Error.WrongTokenFoundException;
import db.query.queries.DatabaseCreateQuery;
import db.system.validator.QueryValidator;
import db.system.validator.implementation.QueryValidatorImpl;

public class QueryTest {

    private QueryValidator validator;

    @BeforeMethod
    public void setUp() {
        // We inject the FSM Validator directly. No global Singletons required!
        this.validator = new QueryValidatorImpl(); 
    }

    @Test
    public void buildDbQuerySuccess() {
        DatabaseCreateQuery.getNestedQueryBuilder(validator)
                .create()
                .database("user", "abhi", "pass")
                .buildQuery(); // Assuming you fixed the uppercase 'B'
    }
    
    @Test
    public void buildDbQueryFail3() {
        WrongTokenFoundException e = expectThrows(WrongTokenFoundException.class, () -> {
            DatabaseCreateQuery.getNestedQueryBuilder(validator)
                    .create()
                    .create() // Duplicate create
                    .database("user", "abhi", "pass")
                    .buildQuery();
        });
        
        assertEquals(e.getMessage(), "Wrong token found: CREATE, possible tokens: [DATABASE]");
    }
    
    @Test
    public void buildDbQueryFail4() {
        WrongTokenFoundException e = expectThrows(WrongTokenFoundException.class, () -> {
            DatabaseCreateQuery.getNestedQueryBuilder(validator)
                    .database("user", "abhi", "pass") // Missing create
                    .buildQuery();
        });
        
        assertEquals(e.getMessage(), "Wrong token found: DATABASE, possible tokens: [CREATE]");
    }
    
    @Test
    public void buildDbQueryFail5() {
        WrongTokenFoundException e = expectThrows(WrongTokenFoundException.class, () -> {
            DatabaseCreateQuery.getNestedQueryBuilder(validator)
                    .create()
                    .database("user", "abhi", "pass")
                    .database("user", "abhi", "pass") // Duplicate database
                    .buildQuery();
        });
        
        assertEquals(e.getMessage(), "Wrong token found: DATABASE, possible tokens: []");
    }
    
    @Test
    public void buildDbQueryFail6() {
        // Assuming your builder throws QueryErrorException if the FSM doesn't reach a terminal state
    	IllegalArgumentException e = expectThrows(IllegalArgumentException.class, () -> {
            DatabaseCreateQuery.getNestedQueryBuilder(validator)
                    .buildQuery();
        });
        
        assertEquals(e.getMessage(), "Query tokens cannot be empty.");
    }
}