package db.system;

import db.system.Processor.Processor;
import db.system.Processor.ProcessorImpl;

public enum SystemProcessorFactory {

	INSTANCE;
	
	private final Processor processor;
	
	SystemProcessorFactory() {
		this.processor = new ProcessorImpl();		
	}
	
	public Processor Processor() {
		return this.processor;
	}
}
