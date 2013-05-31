package modifiers.finals;

import java.lang.reflect.Field;

/**
 * @author sigito
 */
public class FinalFieldChange {
    private static void change(Person p, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field firstNameField = Person.class.getDeclaredField(name);
        firstNameField.setAccessible(true);
        firstNameField.set(p, value);
    }

    /**
     * We can change final fields in runtime with reflection
     * but only that which haven't been inlined (primitives and String not null are inlined by the compiler).
     *
     * @version 1.7.0_07-b10
     * @version 1.8.0-ea-b68
     */
    public static void test() {
        try {
            Person person = new Person("Heinz Kabutz", 32);
            System.out.println("Original state: " + person);
            change(person, "name", "Ng Keng Yap");
            change(person, "age", 27);
            change(person, "iq", 150);
            change(person, "country", "Malaysia");
            System.out.println("Reflection modified person: " + person);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static class Person {
        private final String name;
        private final int age;
        private final int iq = 110;
        private final Object country = "South Africa";

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return name + ", " + age + " of IQ=" + iq + " from " + country;
        }
    }
}
