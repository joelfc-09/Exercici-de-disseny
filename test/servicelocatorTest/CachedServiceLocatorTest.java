package servicelocatorTest;

import org.junit.Test;
import servicelocator.*;

import static org.junit.jupiter.api.Assertions.*;

public class CachedServiceLocatorTest {
    ServiceLocator chsl = new CachedServiceLocator();

    ImplementationD1 d1 = new ImplementationD1(1);
    ImplementationC1 c1 = new ImplementationC1("Un");
    ImplementationB1 b1 = new ImplementationB1(d1);
    ImplementationA1 a1 = new ImplementationA1(b1, c1);

    @Test
    public void setServiceFailTest() throws LocatorError {
        Factory factory = new FactoryA1();
        chsl.setService("FactoryA1", factory);

        try {
            chsl.setService("FactoryA1", factory);
        } catch (LocatorError le) {
            throw new LocatorError();
        }
    }

    @Test
    public void setConstantFailTest() throws LocatorError {
        chsl.setConstant("4000 patates", new Object());

        try {
            chsl.setConstant("4000 patates", new Object());
        } catch (LocatorError le) {
            throw new LocatorError();
        }
    }

    @Test
    public void getObject() throws LocatorError {
        Factory factory = new FactoryB1();
        chsl.setService("FactoryB1", factory);
        chsl.setConstant("A", a1);
        chsl.setConstant("B", b1);
        chsl.setConstant("C", c1);
        chsl.setConstant("D", d1);

        assertNotEquals(FactoryB1.class, chsl.getObject("FactoryB1"));
    }

    @Test
    public void getFactory() throws LocatorError {
        Factory factory = new FactoryC1();
        chsl.setConstant("FactoryC1", factory);

        assertEquals(factory, chsl.getObject("FactoryC1"));
    }

    @Test
    public void simpleCreation() throws LocatorError {
        Factory factory = new FactoryD1();
        chsl.setService("FactoryD1", factory);
        chsl.setConstant("i", 1);

        assertEquals(chsl.getObject("FactoryD1").hashCode(), chsl.getObject("FactoryD1").hashCode());
    }
}