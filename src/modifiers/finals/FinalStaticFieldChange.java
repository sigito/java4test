package modifiers.finals;

/**
 * @author sigito
 */

import java.lang.reflect.Field;

public class FinalStaticFieldChange {
    /**
     * Static fields of type String or primitive would get inlined
     */
    private static final String stringValue = "original value";
    private static final Object objValue = stringValue;

    private static void changeStaticField(String name)
            throws NoSuchFieldException, IllegalAccessException {
        Field statFinField = FinalStaticFieldChange.class.getDeclaredField(name);
        statFinField.setAccessible(true);
        statFinField.set(null, "new Value");
    }

    /**
     * Static final fields cannot be modified via reflection instead of instance final fields.
     *
     * @see FinalFieldChange#test()
     */
    public static void test() {
        try {
            changeStaticField("stringValue");
            System.out.println("stringValue = " + stringValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Oops, we shouldn't receive it: " + e);
        }

        try {
            changeStaticField("objValue");
            System.out.println("objValue = " + objValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Oops, we shouldn't receive it: " + e);
        }
    }
}
