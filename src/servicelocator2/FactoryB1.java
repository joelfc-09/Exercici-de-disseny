package servicelocator2;

import servicelocator.ImplementationB1;
import servicelocator.InterfaceB;
import servicelocator.InterfaceD;

public class FactoryB1 implements Factory<InterfaceB> {
    public InterfaceB create (ServiceLocator sl)
        throws LocatorError {
        InterfaceD d = sl.getObject(InterfaceD.class);
        return new ImplementationB1(d);
    }
}
