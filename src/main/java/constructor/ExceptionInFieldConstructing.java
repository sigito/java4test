package constructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author sigito
 */
public class ExceptionInFieldConstructing {
    private static class FieldExceptionClass {
        /**
         * This field throws an exception while instantiating.
         */
        private InputStream iThrowExceptionWhileInstantiating = new FileInputStream("random unexisted file name");

        /**
         * Constructor with explicit throws statement for the field {@code iThrowExceptionWhileInstantiating}.
         *
         * @throws FileNotFoundException from field initialized not in constructor.
         */
        private FieldExceptionClass() throws FileNotFoundException {
        }
    }

    /**
     * If we instantiate instance field with it's declaration,
     * then if while instantiating this field exception might be thrown we should explicitly catch it in constructor.
     */
    public static void test() {
        try {
            new FieldExceptionClass();
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught as supposed: " + e.getMessage());
        }
    }
}
