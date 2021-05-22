package servicelocatorTest;

import org.junit.Test;
import servicelocator.*;

import static org.junit.jupiter.api.Assertions.*;

public class CachedServiceLocatorTest {
    SimpleServiceLocator spsl = new SimpleServiceLocator();

    ImplementationD1 d1 = new ImplementationD1(1);
    ImplementationC1 c1 = new ImplementationC1("Un");
    ImplementationB1 b1 = new ImplementationB1(d1);
    ImplementationA1 a1 = new ImplementationA1(b1, c1);

    @Test
    public void setServiceTest() throws LocatorError {
        Factory factory = new FactoryA1();
        spsl.setService("FactoryA1", factory);

        assertTrue(spsl.serviceMap.containsKey("FactoryA1"));
    }

    @Test
    public void setServiceFailTest() throws LocatorError {
        Factory factory = new FactoryA1();
        spsl.setService("FactoryA1", factory);

        try {
            spsl.setService("FactoryA1", factory);
        } catch (LocatorError le) {
            throw new LocatorError();
        }
    }

    @Test
    public void setConstantTest() throws LocatorError {
        spsl.setConstant("3000 patates", new Object());

        assertTrue(spsl.constantMap.containsKey("3000 patates"));
    }

    @Test
    public void setConstantFailTest() throws LocatorError {
        spsl.setConstant("4000 patates", new Object());

        try {
            spsl.setConstant("4000 patates", new Object());
        } catch (LocatorError le) {
            throw new LocatorError();
        }
    }

    @Test
    public void getObject() throws LocatorError {
        Factory factory = new FactoryB1();
        spsl.setService("FactoryB1", factory);
        spsl.setConstant("A", a1);
        spsl.setConstant("B", b1);
        spsl.setConstant("C", c1);
        spsl.setConstant("D", d1);

        assertNotEquals(FactoryB1.class, spsl.getObject("FactoryB1"));
    }

    @Test
    public void getFactory() throws LocatorError {
        Factory factory = new FactoryC1();
        spsl.setConstant("FactoryC1", factory);

        assertEquals(factory, spsl.getObject("FactoryC1"));
    }

    @Test
    public void simpleCreation() throws LocatorError {
        Factory factory = new FactoryD1();
        spsl.setService("FactoryD1", factory);
        spsl.setConstant("i", 1);

        assertEquals(spsl.getObject("FactoryD1").hashCode(), spsl.getObject("FactoryD1").hashCode());
    }
}