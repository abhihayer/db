package db.system.registry;

public interface Registry {

	public <T> T getRegisteredHandler(Class<?> handlerForClass, Class<T> handlerClassInterface);
	public <T> void addRegisteredHandler(Class<?> handlerForClass, Class<T> handlerClassInterface, T handlerClassObject);
}
