package com.winphone.xjwrj.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author jack
 */
public class SessionCacheUtil {

    private static final String LOGIN_USER_ID = "loginUserId";
    private static final String LOCATION_URL = "locationURL";

    public static void setLoginUserId(Long userId) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            attributes.setAttribute(LOGIN_USER_ID, userId, RequestAttributes.SCOPE_SESSION);
        }
    }

    public static Long getLoginUserId() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return (Long) attributes.getAttribute(LOGIN_USER_ID, RequestAttributes.SCOPE_SESSION);
        }
        return null;
    }

    public static void setLocationURL(String locationURL) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            attributes.setAttribute(LOCATION_URL, locationURL, RequestAttributes.SCOPE_SESSION);
        }
    }

    public static String getLocationURL() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return (String) attributes.getAttribute(LOCATION_URL, RequestAttributes.SCOPE_SESSION);
        }
        return null;
    }
}
