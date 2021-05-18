package servicelocator2;

import java.util.HashMap;

public class SimpleServiceLocator<T> implements ServiceLocator {
    private HashMap<Class, Factory> serviceMap = new HashMap<>();
    private HashMap<Class, T> constantMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> void setService(Class<T> klass, Factory<T> factory) throws LocatorError {
        if (!constantMap.containsKey(klass)) {
            serviceMap.put(klass, factory);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void setConstant(Class<T> klass, T value) throws LocatorError {
        if (!serviceMap.containsKey(klass)) {
            constantMap.put(klass, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> klass) throws LocatorError {
            if (constantMap.containsKey(klass)) {
                return constantMap.get(klass);
            } else if (serviceMap.containsKey(klass)) {
                return serviceMap.get(klass).create(this);
    }
}
