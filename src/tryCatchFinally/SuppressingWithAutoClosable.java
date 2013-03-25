package tryCatchFinally;

import java.util.Arrays;

/**
 * @author sigito
 */
public class SuppressingWithAutoClosable {
    private static class AlwaysThrowingClosable implements AutoCloseable {
        public void throwSomething() throws MyExtraException {
            throw new MyExtraException("You asked to throw this.");
        }

        @Override
        public void close() throws MyExtraException {
            throw new MyExtraException("Closing failed");
        }
    }

    private static class MyExtraException extends Exception {
        private MyExtraException(String message) {
            super(message);
        }
    }

    private static void tryWithResources() {
        System.out.println("Using tryWithResources.");
        try (AlwaysThrowingClosable a = new AlwaysThrowingClosable()) {
            a.throwSomething();
        } catch (MyExtraException e) {
            System.out.println("Caught exception: " + e.getMessage());
            System.out.println("Suppressed ones: " + Arrays.toString(e.getSuppressed()));
        }
        System.out.println("End of tryWithResources.");
    }

    private static void simpleTry() {
        System.out.println("Using old style try.");
        try {
            AlwaysThrowingClosable a = new AlwaysThrowingClosable();
            try {
                a.throwSomething();
            } finally {
                a.close();
            }
        } catch (MyExtraException e) {
            System.out.println("Caught exception: " + e.getMessage());
            System.out.println("Suppressed ones: " + Arrays.toString(e.getSuppressed()));
        }
        System.out.println("End of old style try.");
    }

    /**
     * Difference in try and try with resources is that with simple try you will receive the last exception occurred,
     * while all previous exceptions will be forgotten forever.
     * In case of try with resources you will catch first thrown exception,
     * and with use of {@link Exception#getSuppressed()} access to all other suppressed exception.
     *
     * @see Exception#addSuppressed(Throwable)
     * @see Exception#getSuppressed()
     */
    public static void test() {
        simpleTry();
        tryWithResources();
    }
}
