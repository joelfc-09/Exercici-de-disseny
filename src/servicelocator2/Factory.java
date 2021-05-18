package servicelocator2;

public interface Factory<T> {
    T create(ServiceLocator sl) throws LocatorError;
}
