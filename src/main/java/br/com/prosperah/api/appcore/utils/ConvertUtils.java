package br.com.prosperah.api.appcore.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

public class ConvertUtils {

    /**
     * Converts UUID to byte array.
     *
     * @param uuid containing POJO unique id
     * @return id in byte format to persist in database
     */
    public static byte[] ToBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    /**
     * Converts byte array to UUID.
     *
     * @param bytes containing persisted unique id
     * @return id in UUID format to operate in POJO
     */
    public static UUID toUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }

    /**
     * Converts the given string to a byte array.
     *
     * @param  string   the string to be converted to bytes
     * @return          the byte array representing the string
     */
    public static char[] convertStringToChars(String string) {
        return string.toCharArray();
    }


}

