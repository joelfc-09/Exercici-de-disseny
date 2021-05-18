package servicelocator2;

import servicelocator.ImplementationD1;
import servicelocator.InterfaceD;

public class FactoryD1 implements Factory<InterfaceD> {
    public InterfaceD create (ServiceLocator sl)
        throws LocatorError {
        int i = sl.getObject(int.class);
        return new ImplementationD1(i);
    }
}
