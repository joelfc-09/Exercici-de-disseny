package servicelocator2;

import java.util.HashMap;

public class CachedServiceLocator implements ServiceLocator{
    private final HashMap<Class, Factory> serviceMap = new HashMap<>();
    private final HashMap<Class, Boolean> usedService = new HashMap<>();
    private final HashMap<Class, Object> createdObjects = new HashMap<>();
    private final HashMap<Class, Object> constantMap = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> void setService(Class<T> klass, Factory<T> factory) throws LocatorError {
        if (!constantMap.containsKey(klass)) {
            serviceMap.put(klass, factory);
            usedService.put(klass, false);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void setConstant(Class<T> klass, T value) throws LocatorError {
        if (!serviceMap.containsKey(klass)) {
            constantMap.put(klass, value);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> klass) throws LocatorError {
        if (constantMap.containsKey(klass)) {
            return (T) constantMap.get(klass);
        } else if (serviceMap.containsKey(klass)) {
            if (!usedService.get(klass)) {
                createdObjects.put(klass, serviceMap.get(klass).create(this));
                usedService.put(klass, true);
            }
            return (T) createdObjects.get(klass);
        }
        return null;
    }
}
