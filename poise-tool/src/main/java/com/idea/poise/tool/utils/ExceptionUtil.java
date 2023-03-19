package com.idea.poise.tool.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

/**
 * 异常处理工具类
 */
public class ExceptionUtil {


    /**
     * 将CheckedException转换为UncheckedException.
     *
     * @param e Throwable
     * @return {RuntimeException}
     */
    public static RuntimeException unchecked(Throwable e) {
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
                || e instanceof NoSuchMethodException) {
            return new IllegalArgumentException(e);
        } else if (e instanceof InvocationTargetException) {
            return new RuntimeException(((InvocationTargetException) e).getTargetException());
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 代理异常解包
     *
     * @param wrapped 包装过得异常
     * @return 解包后的异常
     */
    public static Throwable unwrap(Throwable wrapped) {
        Throwable unwrapped = wrapped;
        while (true) {
            if (unwrapped instanceof InvocationTargetException) {
                unwrapped = ((InvocationTargetException) unwrapped).getTargetException();
            } else if (unwrapped instanceof UndeclaredThrowableException) {
                unwrapped = ((UndeclaredThrowableException) unwrapped).getUndeclaredThrowable();
            } else {
                return unwrapped;
            }
        }
    }

    /**
     * 获取指定包名为前缀的堆栈异常信息
     *
     * @param throwable     异常
     * @param packagePrefix 指定的包前缀
     * @return 堆栈信息
     */
    public static List<StackTraceElement> getStackTrace(Throwable throwable, String packagePrefix) {
        List<StackTraceElement> elementList = new ArrayList<>();
        StackTraceElement[] trace = throwable.getStackTrace();
        for (StackTraceElement e : trace) {
            if (e.getClassName().startsWith(packagePrefix)) {
                elementList.add(e);
            }
        }
        return elementList;
    }

    public static String getStackTraceAsStr(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    public static StackTraceElement getElement(Throwable throwable, int index) {
        StackTraceElement[] elements = throwable.getStackTrace();
        if (elements != null && elements.length > 0) {
            return elements[index];
        }
        return null;
    }


}
