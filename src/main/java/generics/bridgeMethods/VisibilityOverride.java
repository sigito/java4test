package generics.bridgeMethods;

import java.lang.reflect.Method;

/**
 * @author sigito
 */
public class VisibilityOverride {
    static class A {
        String className;

        A() {
            className = "A";
        }

        public void foo() {
            System.out.println("In " + className);
        }
    }

    public static class B extends A {
        public B() {
            className = "B";
        }

//        public void foo() {
//            super.foo();
//        }
    }

    /**
     * Bridge method generated when changes visibility of class and so methods should be adapted to new visibility options.
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
    }

    private static void printMethodInfo(Method method) {
        System.out.println(method);
        System.out.println("Is synthetic: " + method.isSynthetic());
        System.out.println("Is bridge: " + method.isBridge());
    }
}
