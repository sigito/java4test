package generics;

import java.util.List;

/**
 * @author sigito
 */
public class GenericVararg {
    static abstract class A<T> {
        List<T> list;

        public abstract void add(T... args);

        public void addNotNull(T... args) {
            for (T arg : args) {
                if (arg != null) {
                    add(arg);
                }
            }
        }
    }

    static class B extends A<String> {

        @Override
        public void add(String... args) {
            for (String arg : args) {
                list.add(arg);
            }
        }
    }

    /**
     * Generics with varargs are dangerous as far as bridge method generated which casts input parameter to generic specific one
     * This leads to {@link ClassCastException} as default method passes {@code Object[]}
     * and in bridge method this argument is casted to {@code String[]}.
     */
    public static void test() {
        try {
            String[] strings = new String[]{"null", null, "3"};
            new B().addNotNull(strings);
        } catch (ClassCastException e) {
            System.out.println("Exception caught:");
            e.printStackTrace();
        }
    }
}
