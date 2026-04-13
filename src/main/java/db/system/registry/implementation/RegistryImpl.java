package db.system.registry.implementation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import db.system.registry.Lifecycle;
import db.system.registry.Registry;

public class RegistryImpl implements Registry {

	private final Map<Class<?>, Object> registryMap;
	
	public RegistryImpl() {
		registryMap = new ConcurrentHashMap<>();
	}

	@Override
	public <T> T getRegisteredHandler(Class<T> handlerForClass) {
		Object outHandler = registryMap.get(handlerForClass);
		
		if(outHandler == null) {
			throw new IllegalStateException("No handler registered for type: " + handlerForClass.getName());
		}
		
		return handlerForClass.cast(outHandler);
	}

	@Override
	public <T> void addRegisteredHandler(Class<T> handlerForClass, T handlerObject) {
		registryMap.putIfAbsent(handlerForClass, handlerObject);
	}
	
    public void closeEcosystem() {
        for (Object plugin : registryMap.values()) {
            if (plugin instanceof Lifecycle) {
                ((Lifecycle) plugin).shutdown();
            }
        }
        registryMap.clear();
    }

}
