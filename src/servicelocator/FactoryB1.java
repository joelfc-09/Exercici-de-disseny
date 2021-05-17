package servicelocator;

public class FactoryB1 implements Factory {
    public InterfaceB create (ServiceLocator sl)
        throws LocatorError {
        try {
            InterfaceD d = (InterfaceD) sl.getObject("D");
            return new ImplementationB1(d);
        } catch (ClassCastException ex) {
            throw new LocatorError();
        }
    }
}
