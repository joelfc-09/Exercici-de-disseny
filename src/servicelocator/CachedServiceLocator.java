package servicelocator;

import java.util.HashMap;

public class CachedServiceLocator implements ServiceLocator{
    private HashMap<String, Factory> serviceMap = new HashMap<>();
    private HashMap<String, Boolean> usedService = new HashMap<>();
    private HashMap<String, Object> createdObjects = new HashMap<>();
    private HashMap<String, Object> constantMap = new HashMap<>();

    @Override
    public void setService(String name, Factory factory) throws LocatorError {
        if (!constantMap.containsKey(name)) {
            serviceMap.put(name, factory);
            usedService.put(name, false);
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
            if (!usedService.get(name)) {
                createdObjects.put(name, serviceMap.get(name).create(this));
                usedService.put(name, true);
            }
            return createdObjects.get(name);
        } else {
            throw new LocatorError();
        }
    }
}
