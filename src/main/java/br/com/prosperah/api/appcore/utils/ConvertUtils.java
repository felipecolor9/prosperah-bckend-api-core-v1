package br.com.prosperah.api.appcore.utils;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

public class ConvertUtils {

    /**
     * Converts UUID to byte array.
     *
     * @param uuid containing POJO unique id
     * @return id in byte format to persist in database
     */
    public static byte[] convertUUIDToBytes(UUID uuid) {
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
    public static UUID convertBytesToUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }


}

