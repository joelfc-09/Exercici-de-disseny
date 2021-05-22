package servicelocator;

import java.util.HashMap;

public class SimpleServiceLocator implements ServiceLocator {
    public final HashMap<String, Factory> serviceMap = new HashMap<>();
    public final HashMap<String, Object> constantMap = new HashMap<>();

    @Override
    public void setService(String name, Factory factory) throws LocatorError {
        if (!constantMap.containsKey(name)) {
            serviceMap.put(name, factory);
        } else {
            throw new LocatorError();
        }
    }

    @Override
    public void setConstant(String name, Object value) throws LocatorError {
        if (!serviceMap.containsKey(name)) {
            constantMap.put(name, value);
        } else {
            throw new LocatorError();
        }
    }

    @Override
    public Object getObject(String name) throws LocatorError {
        if (constantMap.containsKey(name)) {
            return constantMap.get(name);
        } else if (serviceMap.containsKey(name)) {
            return serviceMap.get(name).create(this);
        } else {
            throw new LocatorError();
        }
    }
}
