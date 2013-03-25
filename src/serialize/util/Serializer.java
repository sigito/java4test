package serialize.util;

import java.io.*;

/**
 * @author sigito
 */
public class Serializer {
    /**
     * Serializes passed object through default serialization and returns bytes of serialized object.
     *
     * @param o object to serialize.
     * @return bytes representing serialized object.
     * @throws IOException if an I/O error occurs while serializing an object.
     */
    public static byte[] toByteArray(Object o) throws IOException {
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)
        ) {
            oos.writeObject(o);
            oos.flush();

            return baos.toByteArray();
        }
    }

    /**
     * Deserializes object from passed bytes.
     *
     * @param bytes serialized object bytes.
     * @param clazz resulting object class type.
     * @throws IOException if an I/O error occurs while deserializing an object.
     * @throws ClassNotFoundException thrown class of object represented in byte array not found.
     * @throws ClassCastException if deserialized object is not of type {@code clazz}
     */
    public static <T> T fromByteArray(byte[] bytes, Class<T> clazz) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return clazz.cast(ois.readObject());
        }
    }
}
