package com.wscq.baseWeiChat.domain.util;

import de.ruedigermoeller.serialization.FSTConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 主动序列化
 *
 * Created by zs on 16/9/29.
 */
public class SerializeUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(SerializeUtils.class);
    private static FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();

    public static byte[] serializeObject(Object object) {
        return configuration.asByteArray((Serializable) object);
    }

    public static Object deserializeObject(byte[] buf) {
        if (buf == null || buf.length == 0) {
            return null;
        }
        return configuration.asObject(buf);
    }
}
