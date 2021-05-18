package servicelocator2;

import servicelocator.ImplementationC1;
import servicelocator.InterfaceC;

public class FactoryC1 implements Factory<InterfaceC> {
    public InterfaceC create (ServiceLocator sl)
        throws LocatorError{
        String s = sl.getObject(String.class);
        return new ImplementationC1(s);
    }
}
