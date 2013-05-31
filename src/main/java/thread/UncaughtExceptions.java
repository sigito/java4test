package thread;

/**
 * @author sigito
 */
public class UncaughtExceptions {

    static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(getName() + ": throwing RuntimeException.");
            throw new RuntimeException(getName() + ". Unhandled exception.");
        }
    }

    static class ToStringHandler implements Thread.UncaughtExceptionHandler {
        static final String DEFAULT_NAME = "DEFAULT NAME";

        String name;

        ToStringHandler() {
            this(DEFAULT_NAME);
        }

        ToStringHandler(String name) {
            this.name = name;
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(getClass().getName() + ": handling exception for " + t.getName() + " - " + e.getMessage());
        }
    }

    /**
     * java.lang.Thread.UncaughtExceptionHandler interface is used for defining method of handling exceptions in particular thread
     * or defining default for every.
     *
     * @see Thread.UncaughtExceptionHandler
     *
     * @see Thread#setDefaultUncaughtExceptionHandler(java.lang.Thread.UncaughtExceptionHandler)
     * @see Thread#setUncaughtExceptionHandler(java.lang.Thread.UncaughtExceptionHandler)
     */
    public static void test() {
        try {
            Thread.setDefaultUncaughtExceptionHandler(new ToStringHandler());

            MyThread myThread = new MyThread("Thread with default handler");
            myThread.start();
            myThread.join();

            myThread = new MyThread("Thread with self handler");
            myThread.setUncaughtExceptionHandler(new ToStringHandler("Self handler."));
            myThread.start();
            myThread.join();
        } catch (InterruptedException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
