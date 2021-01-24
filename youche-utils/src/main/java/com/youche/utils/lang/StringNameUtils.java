package com.youche.utils.lang;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 字符串命名工具类
 */
public class StringNameUtils {

    private static final char UNDERLINE = '_';

    /**
     * 驼峰转下划线
     * userId 转 user_id
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder();
        String v;
        for (int i = 0; i < len; i++) {
            v = String.valueOf(param.charAt(i));
            if (v.equals(v.toLowerCase())) {
                sb.append(Character.toLowerCase(param.charAt(i)));
            } else {
                if (i>0) {
                    sb.append(UNDERLINE).append(Character.toLowerCase(param.charAt(i)));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 字符串去下划线转驼峰
     * @example user_id 转 userId
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(Character.toLowerCase(param.charAt(i)));
            }
        }
        return sb.toString();
    }

    /**
     * 把map的key转换成驼峰命名
     * @param map
     * @return
     */
    public static Map<String, Object> toReplaceKeyLow(Map<String, Object> map) {
        Map re_map = new HashMap();
        if (re_map != null) {
            Iterator var2 = map.entrySet().iterator();

            while (var2.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) var2.next();
                re_map.put(underlineToCamel((String) entry.getKey()), map.get(entry.getKey()));
            }

            map.clear();
        }

        return re_map;
    }
}
