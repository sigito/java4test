package generics.bridgeMethods;

import java.lang.reflect.Method;

/**
 * @author sigito
 */
public class GenericParametersOverride {
    static class A<T> {
        public T foo(T t) {
            return t;
        }
    }

    static class B extends A<Number> {

        @Override
        public Number foo(Number number) {
            System.out.println("Received number: " + number);
            return number;
        }

//        public Object foo(Object o) {
//            return foo((Number)o);
//        }
    }

    /**
     * This type of bridge methods are specific to generics.
     * This one is dangerous to use like raw types(see rawFail method);
     */
    public static void test() {
        // get methods in A
        Method[] declaredMethodsA = A.class.getDeclaredMethods();
        System.out.println(A.class.getName() + " methods:");
        for (Method method : declaredMethodsA)
            printMethodInfo(method);

        // get methods in B
        Method[] declaredMethodsB = B.class.getDeclaredMethods();
        System.out.println(B.class.getName() + " methods:");
        for (Method method : declaredMethodsB)
            printMethodInfo(method);

        rowFail();
    }

    /**
     * Be careful with using generics classes as raw types.
     */
    private static void rowFail() {
        System.out.println("Invoking on raw type.");
        try {
            A a = new B();
            Object foo = a.foo(new Object());
            System.out.println(foo);
        } catch (ClassCastException e) {
            System.out.println("Exception caught and it's OK;):");
            e.printStackTrace();
        }
    }

    private static void printMethodInfo(Method method) {
        System.out.println(method);
        System.out.println("Is synthetic: " + method.isSynthetic());
        System.out.println("Is bridge: " + method.isBridge());
    }
}
