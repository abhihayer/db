package db.system;

import db.system.Engine.Engine;
import db.system.Engine.EngineImpl;

public enum SystemEngineFactory {

    // 1. THE INSTANCE
    // The JVM guarantees this constant is initialized exactly ONCE 
    // the very first time this enum is referenced in the code.
    INSTANCE; 
    
    // 2. THE STATE
    // This is where we cache the actual Engine so it is never recreated.
    // Marking it 'final' ensures true immutability.
    private final Engine engine;

    // 3. THE CONSTRUCTOR
    // Enum constructors are implicitly private. The JVM calls this 
    // exactly once when it initializes the 'INSTANCE' constant above.
    SystemEngineFactory() {
        // We use the 'new' keyword here, and ONLY here.
        this.engine = new EngineImpl();
    }

    // 4. THE ACCESSOR
    // This method just returns the cached reference. 
    // It is perfectly thread-safe and extremely fast (O(1) memory lookup).
    public Engine getEngine() {
        return this.engine;
    }
}
