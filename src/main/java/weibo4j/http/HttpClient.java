package weibo4j.http;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DecompressingHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.model.*;
import weibo4j.util.ParamUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sinaWeibo
 */
public class HttpClient implements java.io.Serializable {

    private static final long serialVersionUID = -176092625883595547L;
    private static final int OK = 200;                        // OK: Success!
    private static final int NOT_MODIFIED = 304;            // Not Modified: There was no new data to return.
    private static final int BAD_REQUEST = 400;                // Bad Request: The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.
    private static final int NOT_AUTHORIZED = 401;            // Not Authorized: Authentication credentials were missing or incorrect.
    private static final int FORBIDDEN = 403;                // Forbidden: The request is understood, but it has been refused.  An accompanying error message will explain why.
    private static final int NOT_FOUND = 404;                // Not Found: The URI requested is invalid or the resource requested, such as a user, does not exists.
    private static final int NOT_ACCEPTABLE = 406;        // Not Acceptable: Returned by the Search API when an invalid format is specified in the request.
    private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server Error: Something is broken.  Please post to the group so the Weibo team can investigate.
    private static final int BAD_GATEWAY = 502;// Bad Gateway: Weibo is down or being upgraded.
    private static final int SERVICE_UNAVAILABLE = 503;// Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.
    private static final String ENCODING = "UTF-8";
    private static final Charset CHARSET = Charset.forName(ENCODING);

    private static final String PARAMETER_SEPARATOR = "&";
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String QUERY_STRING_SEPARATOR = "?";

    private String proxyHost = Configuration.getProxyHost();
    private int proxyPort = Configuration.getProxyPort();
    private String proxyAuthUser = Configuration.getProxyUser();
    private String proxyAuthPassword = Configuration.getProxyPassword();
    private String token;

    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * Sets proxy host. System property -Dsinat4j.http.proxyHost or
     * http.proxyHost overrides this attribute.
     *
     * @param proxyHost
     */
    public void setProxyHost(String proxyHost) {
        this.proxyHost = Configuration.getProxyHost(proxyHost);
    }

    public int getProxyPort() {
        return proxyPort;
    }

    /**
     * Sets proxy port. System property -Dsinat4j.http.proxyPort or
     * -Dhttp.proxyPort overrides this attribute.
     *
     * @param proxyPort
     */
    public void setProxyPort(int proxyPort) {
        this.proxyPort = Configuration.getProxyPort(proxyPort);
    }

    public String getProxyAuthUser() {
        return proxyAuthUser;
    }

    /**
     * Sets proxy authentication user. System property -Dsinat4j.http.proxyUser
     * overrides this attribute.
     *
     * @param proxyAuthUser
     */
    public void setProxyAuthUser(String proxyAuthUser) {
        this.proxyAuthUser = Configuration.getProxyUser(proxyAuthUser);
    }

    public String getProxyAuthPassword() {
        return proxyAuthPassword;
    }

    /**
     * Sets proxy authentication password. System property
     * -Dsinat4j.http.proxyPassword overrides this attribute.
     *
     * @param proxyAuthPassword
     */
    public void setProxyAuthPassword(String proxyAuthPassword) {
        this.proxyAuthPassword = Configuration
                .getProxyPassword(proxyAuthPassword);
    }

    public String setToken(String token) {
        this.token = token;
        return this.token;
    }

    private final static boolean DEBUG = Configuration.getDebug();
    private static final Logger LOG = LoggerFactory.getLogger(HttpClient.class);
    org.apache.http.client.HttpClient client = null;
    private PoolingClientConnectionManager manager;
    private int maxSize;

    public HttpClient() {
        // change timeout to 2s avoid block thread-pool (Tim)
        this(150, 2000, 2000, 1024 * 1024);
    }

    /**
     * Bypass self signed certificate once for all.
     */
    private void bypassSelfSignedCertificate() {
        TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                LOG.info("client:{}:{}", s, x509Certificates);
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                LOG.info("server:{}:{}", s, x509Certificates);
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            SSLContext context = SSLContext.getInstance(SSLSocketFactory.TLS);
            context.init(null, new TrustManager[]{tm}, null);
            manager.getSchemeRegistry().register(new Scheme("https", 443,
                    new SSLSocketFactory(context, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
            ));
        } catch (NoSuchAlgorithmException e) {
            LOG.warn("Cannot found TLS", e);
        } catch (KeyManagementException e) {
            LOG.warn("Key manager exception", e);
        }
    }

    public HttpClient(int maxConPerHost, int conTimeOutMs, int soTimeOutMs,
                      int maxSize) {
        manager = new PoolingClientConnectionManager(
                SchemeRegistryFactory.createDefault(),
                3,
                TimeUnit.MINUTES);
        manager.setDefaultMaxPerRoute(maxConPerHost);
        bypassSelfSignedCertificate();
        HttpParams clientParams = new SyncBasicHttpParams();
        // Connection Timeout
        HttpClientParams.setConnectionManagerTimeout(clientParams, conTimeOutMs);
        HttpConnectionParams.setConnectionTimeout(clientParams, conTimeOutMs);
        // Socket Timeout
        HttpConnectionParams.setSoTimeout(clientParams, soTimeOutMs);
        HttpConnectionParams.setSoKeepalive(clientParams, true);
        HttpProtocolParams.setContentCharset(clientParams, ENCODING);

        // 忽略cookie 避免 Cookie rejected 警告
        clientParams.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
        DefaultHttpClient.setDefaultHttpParams(clientParams);
        DefaultHttpClient backend = new DefaultHttpClient(manager, clientParams);
        backend.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler());
        // Support GZIP and ZLIB here.
        client = new DecompressingHttpClient(backend);
        this.maxSize = maxSize;
        // 支持proxy
        if (proxyHost != null && !proxyHost.equals("")) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            backend.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            if (proxyAuthUser != null && !proxyAuthUser.equals("")) {
                backend.getCredentialsProvider()
                        .setCredentials(new AuthScope(proxy),
                                new UsernamePasswordCredentials(proxyAuthUser, proxyAuthPassword));
                if (DEBUG) {
                    LOG.debug("Proxy AuthUser: {}", proxyAuthUser);
                    LOG.debug("Proxy AuthPassword: {}", proxyAuthPassword);
                }
            }
        }
    }

    /**
     * Handles HTTP GET request.
     *
     * @param url the url to visit
     * @return {@link Response}
     * @throws WeiboException API related exception.
     */
    public Response get(String url) throws WeiboException {
        return get(url, new HttpParameter[0]);
    }

    public Response get(String url, List<HttpParameter> params) throws WeiboException {
        return get(url, ParamUtils.convert(params));
    }

    public Response get(String url, HttpParameter[] params) throws WeiboException {
        if (DEBUG) {
            LOG.debug("Request:");
            LOG.debug("GET: {}", url);
        }
        if (!ParamUtils.isEmpty(params)) {
            url = appendQueryString(url, params);
        }
        HttpGet get = new HttpGet(url);
        return httpRequest(get);
    }

    public Response get(String url, HttpParameter[] params, Paging paging) throws WeiboException {
        List<HttpParameter> parameters = ParamUtils.convert(params);
        if (null != paging) {
            if (-1 != paging.getMaxId()) {
                parameters.add(new HttpParameter("max_id", paging.getMaxId()));
            }
            if (-1 != paging.getSinceId()) {
                parameters.add(new HttpParameter("since_id", paging.getSinceId()));
            }
            if (-1 != paging.getPage()) {
                parameters.add(new HttpParameter("page", paging.getPage()));
            }
            if (-1 != paging.getCount()) {
                if (url.contains("search")) {
                    // search api takes "rpp"
                    parameters.add(new HttpParameter("rpp", paging.getCount()));
                } else {
                    parameters.add(new HttpParameter("count", paging.getCount()));
                }
            }
        }
        return get(url, parameters);
    }

    /**
     * Handles HTTP DELETE request.
     *
     * @param url    URL to visit
     * @param params parameters, will be encoded.
     * @return HTTP response
     * @throws WeiboException API related exception.
     */
    public Response delete(String url, HttpParameter[] params) throws WeiboException {
        if (ParamUtils.isNotEmpty(params)) {
            url = appendQueryString(url, params);
        }
        HttpDelete deleteMethod = new HttpDelete(url);
        return httpRequest(deleteMethod);
    }

    public static String appendQueryString(String url, HttpParameter[] params) {
        String encodedParams = HttpClient.encodeParameters(params);
        if (!url.contains(QUERY_STRING_SEPARATOR)) {
            url += QUERY_STRING_SEPARATOR + encodedParams;
        } else {
            url += PARAMETER_SEPARATOR + encodedParams;
        }
        return url;
    }

    /**
     * Handles HTTP POST request.
     *
     * @param url    the url to visit.
     * @param params HTTP parameters.
     * @return {@link Response}
     * @throws WeiboException API related exception.
     * @see #post(String, weibo4j.model.HttpParameter[], Boolean)
     */
    public Response post(String url, HttpParameter[] params) throws WeiboException {
        return post(url, params, true);
    }

    public static List<NameValuePair> convert(HttpParameter[] params) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        if (null != params) {
            for (HttpParameter param : params) {
                pairs.add(new BasicNameValuePair(param.getName(), param.getValue()));
            }
        }
        return pairs;
    }

    public Response post(String url, List<NameValuePair> params) throws WeiboException {
        return post(url, params, true);
    }

    public Response post(String url, HttpParameter[] params, Boolean WithTokenHeader) throws WeiboException {
        return post(url, convert(params), WithTokenHeader);
    }

    public Response post(String url, List<NameValuePair> params, Boolean WithTokenHeader) throws WeiboException {
        if (DEBUG) {
            LOG.debug("Request:");
            LOG.debug("POST {}", url);
        }
        HttpPost postMethod = new HttpPost(url);
        postMethod.setEntity(new UrlEncodedFormEntity(params, CHARSET));
        if (WithTokenHeader) {
            return httpRequest(postMethod);
        } else {
            return httpRequest(postMethod, WithTokenHeader);
        }
    }

    /**
     * Upload image with multi-part request.
     *
     * @param url
     * @param params
     * @param item
     * @return
     * @throws WeiboException
     */
    public Response multiPartURL(String url, HttpParameter[] params, ImageItem item) throws WeiboException {
        HttpPost postMethod = new HttpPost(url);
        MultipartEntity entity = newMultipartEntity();
        try {
            if (ParamUtils.isNotEmpty(params)) {
                for (HttpParameter entry : params) {
                    entity.addPart(entry.getName(), new StringBody(entry.getValue(), CHARSET));
                }
            }
            //Check out ContentType
            entity.addPart(item.getName(), new ByteArrayBody(item.getContent(), item.getContentType(), item.getName()));
            postMethod.setEntity(entity);
            return httpRequest(postMethod);
        } catch (Exception ex) {
            throw new WeiboException(ex.getMessage(), ex, -1);
        }
    }

    private static MultipartEntity addStringParts(MultipartEntity entity, HttpParameter[] params) throws WeiboException {
        if (ParamUtils.isNotEmpty(params)) {
            for (HttpParameter entry : params) {
                try {
                    entity.addPart(entry.getName(), new StringBody(entry.getValue(), CHARSET));
                } catch (UnsupportedEncodingException e) {
                    throw new WeiboException(e.getMessage(), e, -1);
                }
            }
        }
        return entity;
    }

    private static MultipartEntity newMultipartEntity() {
        return new MultipartEntity(HttpMultipartMode.STRICT, null, CHARSET);
    }

    public Response multiPartURL(String fileParamName, String url,
                                 HttpParameter[] params, File file) throws WeiboException {
        HttpPost postMethod = new HttpPost(url);
        MultipartEntity entity = newMultipartEntity();
        addStringParts(entity, params);
        FileBody filePart = new FileBody(file, file.getName(), new MimetypesFileTypeMap().getContentType(file), ENCODING);
        entity.addPart(fileParamName, filePart);
        postMethod.setEntity(entity);
        return httpRequest(postMethod);
    }

    public Response httpRequest(HttpUriRequest method) throws WeiboException {
        return httpRequest(method, true);
    }

    public static String getHostAddress() {
        InetAddress ipAddress = null;
        try {
            ipAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            LOG.warn("Cannot get local host:", e);
        }
        return null == ipAddress ? "127.0.0.1" : ipAddress.getHostAddress();
    }

    private void setOAuthHeader() {
        if (token == null) {
            throw new IllegalStateException("Oauth2 token is not set!");
        }
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Authorization", "OAuth2 " + token));
        headers.add(new BasicHeader("API-RemoteIP", getHostAddress()));
        client.getParams().setParameter("http.default-headers", headers);
        if (DEBUG) {
            for (Header hd : headers) {
                LOG.debug("{}: {}", hd.getName(), hd.getValue());
            }
        }
    }

    public Response httpRequest(HttpUriRequest method, boolean withTokenHeader) throws WeiboException {
        int responseCode = -1;
        if (withTokenHeader) {
            setOAuthHeader();
        }
        try {
            HttpResponse httpResponse = client.execute(method);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            if (DEBUG) {
                Header[] resHeader = httpResponse.getAllHeaders();
                LOG.debug("Response:");
                LOG.debug("https StatusCode:{}", responseCode);
                for (Header header : resHeader) {
                    LOG.debug("{}: {}", header.getName(), header.getValue());
                }
            }
            Response response = new Response();
            response.setResponseAsString(EntityUtils.toString(httpResponse.getEntity()));
            if (DEBUG) {
                LOG.debug(response.toString());
            }
            if (responseCode != OK) {
                try {
                    throw new WeiboException(getCause(responseCode), response.asJSONObject(), responseCode);
                } catch (JSONException e) {
                    LOG.warn("Parsing json wrong", e);
                }
            }
            return response;
        } catch (IOException ioe) {
            throw new WeiboException(ioe.getMessage(), ioe, responseCode);
        }
    }

    private static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, ENCODING);
        } catch (UnsupportedEncodingException ex) {
            LOG.warn("Encode parameter {} wrong", value, ex);
        }
        return value;
    }

    /*
     * 对parameters进行encode处理
     */
    public static String encodeParameters(HttpParameter[] postParams) {
        StringBuilder buf = new StringBuilder();
        for (int j = 0; j < postParams.length; j++) {
            if (j != 0) {
                buf.append(PARAMETER_SEPARATOR);
            }
            buf.append(urlEncode(postParams[j].getName()))
                    .append(NAME_VALUE_SEPARATOR)
                    .append(urlEncode(postParams[j].getValue()));
        }
        return buf.toString();
    }

    private static String getCause(int statusCode) {
        String cause = null;
        switch (statusCode) {
            case NOT_MODIFIED:
                break;
            case BAD_REQUEST:
                cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
                break;
            case NOT_AUTHORIZED:
                cause = "Authentication credentials were missing or incorrect.";
                break;
            case FORBIDDEN:
                cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
                break;
            case NOT_FOUND:
                cause = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
                break;
            case NOT_ACCEPTABLE:
                cause = "Returned by the Search API when an invalid format is specified in the request.";
                break;
            case INTERNAL_SERVER_ERROR:
                cause = "Something is broken.  Please post to the group so the Weibo team can investigate.";
                break;
            case BAD_GATEWAY:
                cause = "Weibo is down or being upgraded.";
                break;
            case SERVICE_UNAVAILABLE:
                cause = "Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
                break;
            default:
                cause = "";
        }
        return statusCode + ": " + cause;
    }
}
