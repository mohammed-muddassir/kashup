package com.koston;

public class AppThreadLocals
{
    private static final ThreadLocal<Long> currentUserId = new ThreadLocal<>();

    public static Long getCurrentUserId()
    {
        return currentUserId.get();
    }

    public static void setCurrentUserId(Long userId)
    {
        currentUserId.set(userId);
    }

    public static void clearCurrentUserId()
    {
        currentUserId.remove();
    }
}
