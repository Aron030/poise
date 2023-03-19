package com.idea.poise.tool.utils;


import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;

/**
 * @className: Func
 * @description: 炒鸡好用的瑞士军刀工具类 (>_<)
 * @author: salad
 * @date: 2022/5/28
 **/
public class Func {

    /**
     * 对象组中是否存在 Empty Object
     *
     * @param os 对象组
     * @return boolean
     */
    public static boolean hasEmpty(Object... os) {
        for (Object o : os) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof Optional) {
            return !((Optional<?>) obj).isPresent();
        } else if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        } else {
            return obj instanceof Map && ((Map) obj).isEmpty();
        }
    }




    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static List<String> toList(String values, String separator) {
        if (isBlank(values)) {
            return Collections.emptyList();
        }
        return Arrays.asList(values.split(separator));
    }

    public static List<String> toList(String values) {
        return toList(values, StringPool.COMMA);
    }

    public static String[] toArray(String values, String separator) {
        if (isBlank(values)) {
            return new String[0];
        }
        return values.split(separator);
    }

    public static String[] toArray(String values) {
        return toArray(values, StringPool.COMMA);
    }

    public static String join(Collection<?> coll, String delimiter,
                              String prefix, String suffix,
                              String emptyValue, boolean skipEmpty) {
        return StringUtil.join(coll, delimiter, prefix, suffix, emptyValue, skipEmpty);
    }

    public static String join(Collection<?> coll, String delimiter, boolean isSkipEmpty) {
        return join(coll, delimiter, StringPool.EMPTY, StringPool.EMPTY, StringPool.EMPTY, isSkipEmpty);
    }

    public static String append(CharSequence src, boolean skipEmpty, CharSequence... append) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(src);
        for (CharSequence one : append) {
            if (skipEmpty) {
                if (isNotBlank(one)) {
                    stringBuilder.append(one);
                }
            } else {
                stringBuilder.append(one);
            }
        }
        return stringBuilder.toString();
    }

    public static String append(CharSequence src, CharSequence... append) {
        return append(src, true, append);
    }

    public static String join(Collection<?> coll, String delimiter) {
        return join(coll, delimiter, false);
    }

    public static String join(Collection<?> coll) {
        return join(coll, StringPool.COMMA);
    }

    public static <T> String join(T[] array) {
        return join(Collections.singletonList(array), StringPool.COMMA);
    }

    public static boolean isBlank(final CharSequence cs) {
        return !StringUtil.hasText(cs);
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return StringUtil.hasText(cs);
    }

    public static boolean eq(final CharSequence v1, final CharSequence v2) {
        return eq(v1, v2, false);
    }

    public static boolean eqIgnoreCase(final CharSequence v1, final CharSequence v2) {
        return eq(v1, v2, true);
    }

    public static boolean eq(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (null == str1) {
            return str2 == null;
        } else if (null == str2) {
            return false;
        } else {
            return ignoreCase ? str1.toString()
                    .equalsIgnoreCase(str2.toString()) : str1.equals(str2);
        }
    }

    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(dateTime);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return formatDateTime(dateTime, DateUtil.PATTERN_DATETIME);
    }

    public static String formatDate(LocalDate date, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(date);
    }



    public static <T> T getValue(T src, T alternativeVal) {
        return isEmpty(src) ? alternativeVal : src;
    }

    public static <T> T getValue(T src, Predicate<T> filter, T alternativeVal) {
        return filter.test(src) ? alternativeVal : src;
    }

}
