package system;

/**
 * @author sigito
 */
public class ShuttingDown {
    private static final String ON_EXIT_MESSAGE = "Shutdown hook message.";

    private static void printShutdownMessage(String message, int seqNumber) {
        System.out.println("#" + seqNumber + ": " + message);
    }

    /**
     * You can specify what steps should be done on JVM exit.
     * This can be specified by {@link Runtime#addShutdownHook(Thread)} method, where passed thread contains logical step to be done.
     * Note that these threads don't have execution order, so don't add concurrent interaction.
     * Also at the point threads are executed some if not most services will be unavailable, so don't rely on them.
     * Be sure not your code is quick to prevent freezing of the shutdown process.
     * The good reason to use {@link Runtime#addShutdownHook(Thread)} is to ensure that resources are cleaned up properly and no more.
     * Don't use them as usual coding practice.
     */
    public static void test() {
        System.out.println("(ShuttingDown.test()) NOTE: Test will print '" + ON_EXIT_MESSAGE + "' with seq number of the hook on exit of JVM.");
        for (int i = 0; i < 5; i++) {
            final int seqNumber = i;
            // todo
//            Runtime.getRuntime().addShutdownHook(new Thread(() -> printShutdownMessage(ON_EXIT_MESSAGE, seqNumber)));
        }
    }
}
