/*
Copyright (c) 2007-2009, Yusuke Yamamoto
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the Yusuke Yamamoto nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY Yusuke Yamamoto ``AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL Yusuke Yamamoto BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package weibo4j.model;

import org.json.JSONObject;
import weibo4j.http.Response;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static weibo4j.util.ParamUtils.isEmpty;

/**
 * Super class of Weibo Response objects.
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @see DirectMessage
 * @see weibo4j.model.Status
 * @see weibo4j.model.User
 */
public class WeiboResponse implements Serializable {
    private static Map<String, SimpleDateFormat> formatMap = new HashMap<String, SimpleDateFormat>();
    private static final long serialVersionUID = 3519962197957449562L;
    private transient int rateLimitLimit = -1;
    private transient int rateLimitRemaining = -1;
    private transient long rateLimitReset = -1;
    private static final boolean IS_DALVIK = Configuration.isDalvik();
    public static final String DATE_FORMAT = "EEE MMM d HH:mm:ss z yyyy";
    public static final String NULL_STRING = "null";
    public static final String EMPTY_STRING = "";

    public WeiboResponse() {
    }

    public WeiboResponse(Response res) {
        String limit = res.getResponseHeader("X-RateLimit-Limit");
        if (null != limit) {
            rateLimitLimit = Integer.parseInt(limit);
        }
        String remaining = res.getResponseHeader("X-RateLimit-Remaining");
        if (null != remaining) {
            rateLimitRemaining = Integer.parseInt(remaining);
        }
        String reset = res.getResponseHeader("X-RateLimit-Reset");
        if (null != reset) {
            rateLimitReset = Long.parseLong(reset);
        }
    }

    protected static String getString(String name, JSONObject json) {
        return getString(name, json, false);
    }

    protected static String getString(String name, JSONObject json, boolean decode) {
        String returnValue = json.optString(name);
        if (decode) {
            try {
                returnValue = URLDecoder.decode(returnValue, "UTF-8");
            } catch (UnsupportedEncodingException ignore) {
            }
        }
        return returnValue;
    }

    protected static Date parseDate(String str, String format) throws WeiboException {
        if (isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        // SimpleDateFormat is not thread safe
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            throw new WeiboException("Unexpected format(" + str + ") returned from sina.com.cn");
        }
    }

    private static interface Converter<T, V> {
        V cast(T from);
    }

    private static Converter<String, Long> string2Long = new Converter<String, Long>() {
        @Override
        public Long cast(String from) {
            return Long.valueOf(from);
        }
    };

    private static Converter<String, Boolean> string2Bool = new Converter<String, Boolean>() {
        @Override
        public Boolean cast(String from) {
            return Boolean.valueOf(from);
        }
    };
    private static Converter<String, Integer> string2Int = new Converter<String, Integer>() {
        @Override
        public Integer cast(String from) {
            return Integer.valueOf(from);
        }
    };

    private static <V> V getValue(String key, JSONObject json, Converter<String, V> converter, V defaultValue) {
        String value = json.optString(key);
        if (validate(value)) {
            return converter.cast(value);
        }
        return defaultValue;
    }

    protected static int getInt(String key, JSONObject json) {
        return getValue(key, json, string2Int, -1);
    }

    private static boolean validate(String value) {
        return !(isEmpty(value) || NULL_STRING.equals(value));
    }

    protected static long getLong(String key, JSONObject json) {
        return getValue(key, json, string2Long, -1L);
    }

    protected static boolean getBoolean(String key, JSONObject json) {
        return getValue(key, json, string2Bool, false);
    }

    public int getRateLimitLimit() {
        return rateLimitLimit;
    }

    public int getRateLimitRemaining() {
        return rateLimitRemaining;
    }

    public long getRateLimitReset() {
        return rateLimitReset;
    }
}
