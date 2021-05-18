package servicelocator2;

import servicelocator.ImplementationA1;
import servicelocator.InterfaceA;
import servicelocator.InterfaceB;
import servicelocator.InterfaceC;

public class FactoryA1 implements Factory<InterfaceA> {
    public InterfaceA create (ServiceLocator sl)
        throws LocatorError {
        InterfaceB b = sl.getObject(InterfaceB.class);
        InterfaceC c = sl.getObject(InterfaceC.class);
        return new ImplementationA1(b, c);
    }
}
