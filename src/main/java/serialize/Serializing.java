package serialize;

import java.io.*;

/**
 * @author sigito
 */
public class Serializing {
    static class A {
        A() {
            System.out.println("A");
        }
    }

    static class B extends A implements Serializable {
        B() {
            System.out.println("B");
        }
    }

    static class C extends B {
        C() {
            System.out.println("C");
        }
    }

    // TODO Add description
    public static void test() {
        try {
            System.out.println("Creating C.");
            C c = new C();

            System.out.println("Serializing C");

            byte[] serializedC;
            try (
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos)
            ) {
                oos.writeObject(c);
                oos.flush();

                serializedC = baos.toByteArray();
            }

            System.out.println("Deserializing C");
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(serializedC))) {
                c = (C) ois.readObject();
            }
            System.out.println("Deserialized C");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
