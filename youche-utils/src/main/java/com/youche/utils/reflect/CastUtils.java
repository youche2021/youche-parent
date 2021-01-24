package com.youche.utils.reflect;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;

public class CastUtils {

    public static class CastException extends RuntimeException {
        public CastException(Throwable cause) {
            super(cause);
        }
    }

    public static <T> T cast(Object value, Class<T> clazz) {
        try {
            return TypeUtils.cast(value, clazz, ParserConfig.getGlobalInstance());
        } catch (Exception e) {
            throw new CastException(e);
        }
    }
}
