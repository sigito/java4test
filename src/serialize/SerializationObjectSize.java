package serialize;

import serialize.util.Serializer;

import java.io.Serializable;

/**
 * @author sigito
 */
public class SerializationObjectSize {
    static class A implements Serializable {
        private int a = 12;
    }

    static class B implements Serializable {
        private int veryLongName = 23;
    }

    /**
     * Object serialization writes field names during serialization influencing on the size of serialized object.
     */
    public static void test() {
        try {
            A a = new A();
            B b = new B();

            byte[] aBytes = Serializer.toByteArray(a);
            byte[] bBytes = Serializer.toByteArray(b);

            System.out.println("A: " + aBytes.length);
            System.out.println("B: " + bBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
