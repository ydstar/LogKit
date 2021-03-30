package com.log.kit.util;

/**
 * Author: 信仰年轻
 * Date: 2020-09-08 15:20
 * Email: hydznsqk@163.com
 * Des: LogKit的堆栈工具类
 */
public class StackTraceUtil {

    /**
     * Get the real stack trace and then crop it with a max depth.
     *
     * @param stackTrace the full stack trace
     * @param maxDepth   the max depth of real stack trace that will be cropped, 0 means no limitation
     * @return the cropped real stack trace
     */
    public static StackTraceElement[] getCroppedRealStackTrack(StackTraceElement[] stackTrace, String ignorePackage, int maxDepth) {
        return cropStackTrace(getRealStackTrack(stackTrace, ignorePackage), maxDepth);
    }

    /**
     * Get the real stack trace, all elements that come from XLog library would be dropped.
     *
     * @param stackTrace the full stack trace
     * @return the real stack trace, all elements come from system and library user
     */
    private static StackTraceElement[] getRealStackTrack(StackTraceElement[] stackTrace, String ignorePackage) {
        int ignoreDepth = 0;
        int allDepth = stackTrace.length;
        String className;
        for (int i = allDepth - 1; i >= 0; i--) {
            className = stackTrace[i].getClassName();
            if (ignorePackage != null && className.startsWith(ignorePackage)) {
                ignoreDepth = i + 1;
                break;
            }
        }
        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(stackTrace, ignoreDepth, realStack, 0, realDepth);
        return realStack;
    }

    /**
     * Crop the stack trace with a max depth.
     *
     * @param callStack the original stack trace
     * @param maxDepth  the max depth of real stack trace that will be cropped,
     *                  0 means no limitation
     * @return the cropped stack trace
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack, int maxDepth) {
        int realDepth = callStack.length;
        if (maxDepth > 0) {
            realDepth = Math.min(maxDepth, realDepth);
        }
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(callStack, 0, realStack, 0, realDepth);
        return realStack;
    }
}
