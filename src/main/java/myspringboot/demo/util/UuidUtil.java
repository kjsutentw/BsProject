package myspringboot.demo.util;

import java.util.UUID;

/**
 * UUID工具类
 *
 */
public final class UuidUtil {
    private UuidUtil(){
        throw new RuntimeException("UuidUtil.class can't be instantiated");
    }

    /**
     * 获取随机的UUID字符串
     */
    public static String getRandomUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toLowerCase();
    }

    /**
     * 从UUID字符串中去除“-”分隔符
     */
    public static String removeSeparatorFromUuid(String uuid) {
        uuid = uuid.replaceAll("-","");
        return uuid;
    }

    /**
     * 获取不包含“-”分隔符的uuid字符串
     */
    public static String getRandomUuidWithoutSeparator() {
        String uuid = getRandomUuid();
        return removeSeparatorFromUuid(uuid).toUpperCase();
    }
}
