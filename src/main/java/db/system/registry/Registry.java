package db.system.registry;

public interface Registry {

	public <T> T getRegisteredHandler(Class<T> handlerForClass);
	public <T> void addRegisteredHandler(Class<T> handlerForClass, T handlerClass);
}
