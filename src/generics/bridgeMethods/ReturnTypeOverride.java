package generics.bridgeMethods;

import java.lang.reflect.Method;

/**
 * @author sigito
 */
public class ReturnTypeOverride {
    static class A {
        public Object getT() {
            return null;
        }
    }

    static class B extends A {
        @Override
        public String getT() {
            return null;
        }

//        public Object getT() {
//            return getT(); // invoke method with String return type.
//        }
    }

    /**
     * When manually override return type compiler adds bridge method as return type is a part of method signature
     * and by this way compiler implements covariant of return.
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
