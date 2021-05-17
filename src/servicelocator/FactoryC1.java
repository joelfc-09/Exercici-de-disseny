package servicelocator;

public class FactoryC1 implements Factory {
    public InterfaceC create (ServiceLocator sl)
        throws LocatorError {
        try {
            String s = (String) sl.getObject("s");
            return new ImplementationC1(s);
        } catch (ClassCastException ex) {
            throw new LocatorError();
        }
    }
}
