package weibo4j;

import org.json.JSONException;
import org.json.JSONObject;
import weibo4j.http.AccessToken;
import weibo4j.http.BASE64Encoder;
import weibo4j.model.HttpParameter;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class OAuth extends Weibo {

    private static final long serialVersionUID = -6826226585351456652L;

    public OAuth(String accessToken) {
        this.accessToken = accessToken;
    }

    public OAuth() {
    }

    //针对站内应用处理SignedRequest获取access token
    public String accessToken;
    public String userId;

    public String getToken() {
        return accessToken;
    }

    /*
      * 解析站内应用post的SignedRequest split为part1和part2两部分
      */
    public String parseSignedRequest(String signedRequest) throws IOException,
            InvalidKeyException, NoSuchAlgorithmException {
        String[] t = signedRequest.split("\\.", 2);
        // 为了和 url encode/decode 不冲突，base64url 编码方式会将
        // '+'，'/'转换成'-'，'_'，并且去掉结尾的'='。 因此解码之前需要还原到默认的base64编码，结尾的'='可以用以下算法还原
        int padding = (4 - t[0].length() % 4);
        for (int i = 0; i < padding; i++)
            t[0] += "=";
        String part1 = t[0].replace("-", "+").replace("_", "/");
        SecretKey key = new SecretKeySpec(WeiboConfig.getClientSecret().getBytes(), "hmacSHA256");
        Mac m;
        m = Mac.getInstance("hmacSHA256");
        m.init(key);
        m.update(t[1].getBytes());
        String part1Expect = BASE64Encoder.encode(m.doFinal());
        sun.misc.BASE64Decoder decode = new sun.misc.BASE64Decoder();
        String s = new String(decode.decodeBuffer(t[1]));
        if (part1.equals(part1Expect)) {
            return ts(s);
        } else {
            return null;
        }
    }

    /*
      * 处理解析后的json解析
      */
    public String ts(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            accessToken = jsonObject.getString("oauth_token");
            userId = jsonObject.getString("user_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    /*----------------------------Oauth接口--------------------------------------*/

    public AccessToken getAccessTokenByCode(String code) throws WeiboException {
        return getAccessTokenByCode(code, WeiboConfig.getRedirectUrl());
    }

    public AccessToken getAccessTokenByCode(String code, String redirectUrl) throws WeiboException {
        return new AccessToken(client.post(
                WeiboConfig.getValue("accessTokenURL"),
                new HttpParameter[]{
                        new HttpParameter("client_id", WeiboConfig
                                .getClientId()),
                        new HttpParameter("client_secret", WeiboConfig
                                .getClientSecret()),
                        new HttpParameter("grant_type", "authorization_code"),
                        new HttpParameter("code", code),
                        new HttpParameter("redirect_uri", redirectUrl)}, false));
    }

    public AccessToken getAccessTokenByUserCredential(String username, String password) throws WeiboException {
        return new AccessToken(client.post(
                WeiboConfig.getAccessTokenURL(),
                new HttpParameter[]{
                        new HttpParameter("client_id", WeiboConfig
                                .getClientId()),
                        new HttpParameter("client_secret", WeiboConfig
                                .getClientSecret()),
                        new HttpParameter("grant_type", "password"),
                        new HttpParameter("username", username),
                        new HttpParameter("password", password)}, false));
    }

    public String authorize(String responseType) throws WeiboException {
        return authorize(responseType, WeiboConfig.getRedirectUrl());
    }

    public String authorize(String responseType, String redirectUrl) throws WeiboException {
        return WeiboConfig.getValue("authorizeURL").trim() + "?client_id="
                + WeiboConfig.getClientId().trim() + "&redirect_uri="
                + redirectUrl.trim()
                + "&response_type=" + responseType;
    }
}
