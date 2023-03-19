package com.idea.poise.tool.utils;

import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringJoiner;

/**
 * @className: StringUtils
 * @description:
 * @author: salad
 * @date: 2022/2/25
 **/
public class StringUtil extends org.springframework.util.StringUtils {

    public static boolean isBlank(final CharSequence cs) {
        return !hasText(cs);
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return hasText(cs);
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
            return ignoreCase ? str1.toString().equalsIgnoreCase(str2.toString()) : str1.equals(str2);
        }
    }

    /**
     * 驼峰命名转下划线分割命名
     */
    public static String humpToUnderline(@Nullable String para) {
        para = firstCharToLower(para);
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;

        for (int i = 0; i < para.length(); ++i) {
            if (Character.isUpperCase(para.charAt(i))) {
                sb.insert(i + temp, "_");
                ++temp;
            }
        }
        return sb.toString().toLowerCase();
    }


    public static String firstCharToLower(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] = (char) (arr[0] + 32);
            return new String(arr);
        } else {
            return str;
        }
    }

    public static String removeSuffix(CharSequence str, CharSequence suffix) {
        if (!Func.isEmpty(str) && !Func.isEmpty(suffix)) {
            String str2 = str.toString();
            return str2.endsWith(suffix.toString()) ? subPre(str2, str2.length() - suffix.length()) : str2;
        } else {
            return "";
        }
    }

    public static String subPre(CharSequence string, int toIndex) {
        return sub(string, 0, toIndex);
    }

    public static String sub(CharSequence str, int fromIndex, int toIndex) {
        if (str == null || "".contentEquals(str)) {
            return "";
        } else {
            int len = str.length();
            if (fromIndex < 0) {
                fromIndex += len;
                if (fromIndex < 0) {
                    fromIndex = 0;
                }
            } else if (fromIndex > len) {
                fromIndex = len;
            }

            if (toIndex < 0) {
                toIndex += len;
                if (toIndex < 0) {
                    toIndex = len;
                }
            } else if (toIndex > len) {
                toIndex = len;
            }

            if (toIndex < fromIndex) {
                int tmp = fromIndex;
                fromIndex = toIndex;
                toIndex = tmp;
            }

            return fromIndex == toIndex ? "" : str.toString().substring(fromIndex, toIndex);
        }
    }


    public static StringBuilder appendBuilder(StringBuilder sb, CharSequence... strs) {
        for (CharSequence str : strs) {
            sb.append(str);
        }
        return sb;
    }

    public static String join(Collection<?> coll, String delimiter,
                              String prefix, String suffix,
                              String emptyValue, boolean skipEmpty) {
        if (Func.isEmpty(coll)) {
            return emptyValue;
        }

        Iterator<?> iterator = coll.iterator();
        StringJoiner joiner = new StringJoiner(delimiter, prefix, suffix);

        if (null != emptyValue)
            joiner.setEmptyValue(emptyValue);

        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (skipEmpty) {
                if (null != next && isNotBlank(String.valueOf(next))) {
                    joiner.add(String.valueOf(next));
                }
            } else {
                joiner.add(String.valueOf(next));
            }
        }
        return joiner.toString();
    }

    public static String join(Collection<?> coll, String delimiter, boolean isSkipEmpty) {
        return join(coll, delimiter, "", "", "", isSkipEmpty);
    }

    public static String join(Collection<?> coll, String delimiter) {
        return join(coll, delimiter, false);
    }

    public static String join(Collection<?> coll) {
        return join(coll, ",");
    }

    public static <T> String join(T[] array) {
        return join(Collections.singletonList(array), ",");
    }


    public static String getColumn(String str, String keyword) {
        return humpToUnderline(removeSuffix(str, keyword));
    }

}
