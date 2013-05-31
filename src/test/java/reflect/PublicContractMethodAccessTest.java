package reflect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Reflecting methods from private classes with public interface contracts.
 * <p/>
 * More at: <a href="http://it-routings.blogspot.com/2013/05/accessing-method-of-private-member-with.html">Accessing method of private member with reflection</a>
 *
 * @author sigito
 * @version 1.7.0_21
 */
@RunWith(JUnit4.class)
public class PublicContractMethodAccessTest {
    private static final int ACTUAL_SIZE = 4;

    private List<String> namesAsList;
    private List<String> namesByAdd;

    @Before
    public void initNames() {
        namesAsList = Arrays.asList("Anna", "Nick", "Pedro", "Michael");

        namesByAdd = new ArrayList<>();
        namesByAdd.add("Anna");
        namesByAdd.add("Nick");
        namesByAdd.add("Pedro");
        namesByAdd.add("Michael");
    }

    @Test
    public void testGetSize() {
        int size = namesAsList.size();
        assertEquals(ACTUAL_SIZE, size);

        size = namesByAdd.size();
        assertEquals(ACTUAL_SIZE, size);
    }

    @Test
    public void testPublicGetReflectedSize() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method sizeMethod = namesByAdd.getClass().getMethod("size");
        int reflectedSize = (int) sizeMethod.invoke(namesByAdd);
        assertEquals(ACTUAL_SIZE, reflectedSize);
    }

    @Test(expected = IllegalAccessException.class)
    public void testGetReflectedSize() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method sizeMethod = namesAsList.getClass().getMethod("size");
        sizeMethod.invoke(namesAsList);
    }

    @Test
    public void testGetReflectedSizeFromList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<List> listClass = List.class;
        Method sizeMethod = listClass.getMethod("size");
        int reflectedSize = (int) sizeMethod.invoke(namesAsList);
        assertEquals(ACTUAL_SIZE, reflectedSize);
    }

    @Test
    public void testForcedGetReflectedSize() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method sizeMethod = namesAsList.getClass().getMethod("size");
        sizeMethod.setAccessible(true);
        int reflectedSize = (int) sizeMethod.invoke(namesAsList);
        assertEquals(ACTUAL_SIZE, reflectedSize);
    }

    @After
    public void cleanUp() {
        namesAsList = null;
        namesByAdd = null;
    }
}
