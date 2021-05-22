package servicelocator2Test;

import org.junit.Test;
import servicelocator.*;

import servicelocator2.Factory;
import servicelocator2.FactoryA1;
import servicelocator2.FactoryB1;
import servicelocator2.FactoryC1;
import servicelocator2.FactoryD1;
import servicelocator2.LocatorError;
import servicelocator2.ServiceLocator;
import servicelocator2.SimpleServiceLocator;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleServiceLocatorTest {
    ServiceLocator spsl = new SimpleServiceLocator<String>();

    ImplementationD1 d1 = new ImplementationD1(1);
    ImplementationC1 c1 = new ImplementationC1("Un");
    ImplementationB1 b1 = new ImplementationB1(d1);
    ImplementationA1 a1 = new ImplementationA1(b1, c1);

    @Test
    public void setServiceFailTest() throws LocatorError {
        Factory<InterfaceA> factory = new FactoryA1();
        spsl.setService(InterfaceA.class, factory);

        try {
            spsl.setService(InterfaceA.class, factory);
        } catch (LocatorError le) {
            throw new LocatorError();
        }
    }

    @Test
    public void setConstantFailTest() throws LocatorError {
        spsl.setConstant(String.class, "4000 patates");

        try {
            spsl.setConstant(String.class, "4000 patates");
        } catch (LocatorError le) {
            throw new LocatorError();
        }
    }

    @Test
    public void getObject() throws LocatorError {
        Factory<InterfaceB> factory = new FactoryB1();
        spsl.setService(InterfaceB.class, factory);
        spsl.setConstant(InterfaceA.class, a1);
        //spsl.setConstant(InterfaceB.class, b1);
        spsl.setConstant(InterfaceC.class, c1);
        spsl.setConstant(InterfaceD.class, d1);

        assertNotEquals(FactoryB1.class, spsl.getObject(InterfaceB.class));
    }

    @Test
    public void getFactory() throws LocatorError {
        Factory<InterfaceC> factory = new FactoryC1();
        spsl.setConstant(Factory.class, factory);

        assertEquals(factory, spsl.getObject(Factory.class));
    }

    @Test
    public void simpleCreation() throws LocatorError {
        Factory<InterfaceD> factory = new FactoryD1();
        spsl.setService(InterfaceD.class, factory);
        spsl.setConstant(Factory.class, factory);

        assertEquals(spsl.getObject(Factory.class).hashCode(), spsl.getObject(Factory.class).hashCode());
    }
}