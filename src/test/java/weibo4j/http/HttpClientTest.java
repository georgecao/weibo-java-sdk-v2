package weibo4j.http;

import org.junit.Test;

public class HttpClientTest {
    @Test
    public void testHttpRequest() throws Exception {
        HttpClient client = new HttpClient();
        client.setToken("token");
        Response response = client.get("https://api.weibo.com/oauth2/authorize?client_id=342348223&redirect_uri=http%3A%2F%2Fwww.dajie.com%2Faccount%2Fsina%2Fcallback&response_type=code&state=895a0ef683ea5c0a011aaf410674da3c4a1fb502");
       System.out.println(response.toString());
    }
}
