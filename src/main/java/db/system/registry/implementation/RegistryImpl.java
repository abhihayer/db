package db.system.registry.implementation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import db.system.registry.Lifecycle;
import db.system.registry.Registry;
/*
 * 
 * Add : handlerForClass, the class for which we need object
 * 		 handlerObject, the actual object implementation
 * 
 * Get: handlerForClass, the class for which we need object
 * 		interfaceClass, the base interface of the implementation
 * 
 * */
public class RegistryImpl implements Registry {

	private final Map<Set<Class<?>>, Object> registryMap;
	
	public RegistryImpl() {
		registryMap = new ConcurrentHashMap<>();
	}

	@Override
	public <T> T getRegisteredHandler(Class<?> handlerForClass, Class<T> interfaceClass) {
		Set<Class<?>> inputSet = new HashSet<>();
		inputSet.add(handlerForClass); 
		inputSet.add(interfaceClass);
		
		Object outHandler = registryMap.get(inputSet);
		
		if(outHandler == null) {
			throw new IllegalStateException("No handler registered for type: " + handlerForClass.getName());
		}
		
		return interfaceClass.cast(outHandler);
	}

	@Override
	public <T> void addRegisteredHandler(Class<?> handlerForClass, Class<T> handlerClassInterface, T handlerObject) {
		Set<Class<?>> inputSet = new HashSet<>();
		
		inputSet.add(handlerForClass); 
		inputSet.add(handlerClassInterface);

		registryMap.putIfAbsent(inputSet, handlerObject);
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
