package servicelocator2;

public class CachedServiceLocator implements ServiceLocator{
    @Override
    @SuppressWarnings("unchecked")
    public <T> void setService(Class<T> klass, Factory<T> factory) throws LocatorError {

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void setConstant(Class<T> klass, T value) throws LocatorError {

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> klass) throws LocatorError {
        return null;
    }
}
