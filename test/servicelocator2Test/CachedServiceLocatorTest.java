package servicelocator2Test;

import org.junit.Test;
import servicelocator.*;
import servicelocator2.CachedServiceLocator;
import servicelocator2.LocatorError;
import servicelocator2.ServiceLocator;

import static org.junit.jupiter.api.Assertions.*;

public class CachedServiceLocatorTest {
    ServiceLocator chsl = new CachedServiceLocator();

    ImplementationD1 d1 = new ImplementationD1(1);
    ImplementationC1 c1 = new ImplementationC1("Un");
    ImplementationB1 b1 = new ImplementationB1(d1);
    ImplementationA1 a1 = new ImplementationA1(b1, c1);

    @Test
    public void setServiceFailTest() throws LocatorError {
        servicelocator2.Factory<InterfaceA> factory = new servicelocator2.FactoryA1();
        chsl.setService(InterfaceA.class, factory);

        try {
            chsl.setService(InterfaceA.class, factory);
        } catch (LocatorError le) {
            throw new LocatorError();
        }
    }

    @Test
    public void setConstantFailTest() throws LocatorError {
        chsl.setConstant(String.class, "4000 patates");

        try {
            chsl.setConstant(String.class, "4000 patates");
        } catch (LocatorError le) {
            throw new LocatorError();
        }
    }

    @Test
    public void getObject() throws LocatorError {
        servicelocator2.Factory<InterfaceB> factory = new servicelocator2.FactoryB1();
        chsl.setService(InterfaceB.class, factory);
        chsl.setConstant(InterfaceA.class, a1);
        //spsl.setConstant(InterfaceB.class, b1);
        chsl.setConstant(InterfaceC.class, c1);
        chsl.setConstant(InterfaceD.class, d1);

        assertNotEquals(servicelocator2.FactoryB1.class, chsl.getObject(InterfaceB.class));
    }

    @Test
    public void getFactory() throws LocatorError {
        servicelocator2.Factory<InterfaceC> factory = new servicelocator2.FactoryC1();
        chsl.setConstant(servicelocator2.Factory.class, factory);

        assertEquals(factory, chsl.getObject(servicelocator2.Factory.class));
    }

    @Test
    public void simpleCreation() throws LocatorError {
        servicelocator2.Factory<InterfaceD> factory = new servicelocator2.FactoryD1();
        chsl.setService(InterfaceD.class, factory);
        chsl.setConstant(int.class, 1);

        assertNotEquals(chsl.getObject(InterfaceD.class).hashCode(), chsl.getObject(InterfaceD.class).hashCode());
    }
}