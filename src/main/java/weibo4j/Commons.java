package weibo4j;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weibo4j.http.Response;
import weibo4j.model.HttpParameter;
import weibo4j.model.Lang;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

import java.util.*;

import static weibo4j.util.ParamUtils.*;

/**
 * Say something?
 * <pre>
 * User: George
 * Date: 12-9-26
 * Time: 16:18
 * </pre>
 */
public class Commons extends Weibo {
    private static final Logger LOG = LoggerFactory.getLogger(Commons.class);
    private static final boolean DEBUG = LOG.isDebugEnabled();
    private static final String CITY_API = "common/get_city.json";
    private static final String PROVINCE_API = "common/get_province.json";
    private static final String CODE_TO_LOCATION = "common/code_to_location.json";
    public static final String PROVINCE_KEY = "province";
    public static final String LANGUAGE_KEY = "language";
    public static final String CAPITAL_KEY = "capital";
    public static final String COUNTRY_KEY = "country";
    public static final String CODES_KEY = "codes";
    public static final String CHINA_COUNTRY_CODE = "001";
    public static final int CODE_LENGTH = 3;
    private static final long serialVersionUID = 5935967761952052364L;

    public Commons(String accessToken) {
        super(accessToken);
    }

    public Commons() {
    }


    public Map<String, String> codesToLocation(Collection<String> codes) throws WeiboException {
        return codesToLocation(joinWithComma(codes));
    }

    /**
     * Converts codes to locations.
     *
     * @param codes true 	string 	需要查询的地址编码，多个之间用逗号分隔。
     * @return code map to location.
     */
    public Map<String, String> codesToLocation(String codes) throws WeiboException {
        if (isEmpty(codes)) {
            return Collections.emptyMap();
        }
        Response response = client.get(WeiboConfig.getBaseUrl() + CODE_TO_LOCATION,
                new HttpParameter[]{
                        new HttpParameter(CODES_KEY, codes)
                });
        return jsonArrayToMap(response.asJSONArray());
    }

    public String getCountryName(String countryCode) throws WeiboException {
        String sb = padStartWithZero(countryCode, CODE_LENGTH);
        return codesToLocation(sb).get(sb);
    }

    public String getProvinceNameOfChina(String provinceCode) throws WeiboException {
        return getProvinceName(CHINA_COUNTRY_CODE, provinceCode);
    }

    public String getProvinceName(String countryCode, String provinceCode) throws WeiboException {
        String sb = padStartWithZero(countryCode, CODE_LENGTH) + padStartWithZero(provinceCode, CODE_LENGTH);
        return codesToLocation(sb).get(sb);
    }

    public String getCityNameOfChina(String provinceCode, String cityCode) throws WeiboException {
        return getCityName(CHINA_COUNTRY_CODE, provinceCode, cityCode);
    }

    public String getCityName(String countryCode, String provinceCode, String cityCode) throws WeiboException {
        String sb = padStartWithZero(countryCode, CODE_LENGTH) + padStartWithZero(provinceCode, CODE_LENGTH) + padStartWithZero(cityCode, CODE_LENGTH);
        return codesToLocation(sb).get(sb);
    }

    /**
     * Gets provinces of specified country.
     *
     * @param country  Required. string 	国家的国家代码。
     * @param capital  Optional. string 城市的首字母，a-z，可为空代表返回全部，默认为全部。
     * @param language Optional. string 返回的语言版本，zh-cn：简体中文、zh-tw：繁体中文、english：英文，默认为zh-cn。
     * @return json data.
     */
    public String getProvinces(String country, String capital, Lang language) throws WeiboException {
        List<HttpParameter> params = new ArrayList<HttpParameter>(3);
        addCountry(country, params);
        addCapital(capital, params);
        addLanguage(language, params);
        Response response = client.get(WeiboConfig.getBaseUrl() + PROVINCE_API, params);
        return response.asString();
    }

    public String getProvinces(String country) throws WeiboException {
        return getProvinces(country, null, null);
    }

    public String getProvincesStartWithCapital(String country, String capital) throws WeiboException {
        return getProvinces(country, capital, null);
    }

    public String getProvincesInLanguage(String country, Lang language) throws WeiboException {
        return getProvinces(country, null, language);
    }

    /**
     * Gets city of specified province.
     *
     * @param province Required. string 省份的省份代码。
     * @param capital  Optional. string 城市的首字母，a-z，可为空代表返回全部，默认为全部。
     * @param language Optional. string 返回的语言版本，zh-cn：简体中文、zh-tw：繁体中文、english：英文，默认为zh-cn。
     * @return json data.
     */
    public Map<String, String> getCities(String province, String capital, Lang language) throws WeiboException {
        List<HttpParameter> parameters = new ArrayList<HttpParameter>(3);
        addProvince(province, parameters);
        addCapital(capital, parameters);
        addLanguage(language, parameters);
        Response response = client.get(WeiboConfig.getBaseUrl() + CITY_API, parameters);
        return jsonArrayToMap(response.asJSONArray());
    }

    /**
     * Converts single key-value json array to map.
     *
     * @param array Single key-value json array
     * @return a map.
     */
    private static Map<String, String> jsonArrayToMap(JSONArray array) {
        if (null == array) {
            return Collections.EMPTY_MAP;
        }
        int length = array.length();
        Map<String, String> codeToNameMap = new HashMap<String, String>(length);
        for (int i = 0; i < length; i++) {
            JSONObject ele = array.optJSONObject(i);
            if (null != ele) {
                String code = (String) ele.keys().next();
                String name = ele.optString(code);
                if (isEmpty(code) || isEmpty(name)) {
                    continue;
                }
                codeToNameMap.put(code, name);
            }
        }
        return codeToNameMap;
    }


    private List<HttpParameter> addProvince(String province, List<HttpParameter> parameters) {
        return add(PROVINCE_KEY, province, true, parameters);
    }

    private List<HttpParameter> addCapital(String capital, List<HttpParameter> parameters) {
        return add(CAPITAL_KEY, capital, parameters);
    }

    private List<HttpParameter> addCountry(String country, List<HttpParameter> parameters) {
        return add(COUNTRY_KEY, country, true, parameters);
    }

    private List<HttpParameter> addLanguage(Lang language, List<HttpParameter> parameters) {
        return addLanguage(language, true, parameters);
    }

    private List<HttpParameter> addLanguage(Lang language, boolean convertToFullName, List<HttpParameter> parameters) {
        if (null == language) {
            return parameters;
        }
        // we need full name of EN in some api.
        if (convertToFullName && language == Lang.EN) {
            language = Lang.ENGLISH;
        }
        return add(LANGUAGE_KEY, language.getName(), parameters);
    }

    private List<HttpParameter> add(String key, String value, List<HttpParameter> parameters) {
        return add(key, value, false, parameters);
    }

    private List<HttpParameter> add(String key, String value, boolean required, List<HttpParameter> parameters) {
        if (!required && isEmpty(value)) {
            return parameters;
        }
        parameters.add(new HttpParameter(key, nullToEmpty(value)));
        return parameters;
    }

    public Map<String, String> getCities(String province) throws WeiboException {
        return getCities(province, null, null);
    }

    public Map<String, String> getCitiesStartWithCapital(String province, String capital) throws WeiboException {
        return getCities(province, capital, null);
    }

    public Map<String, String> getCitiesInLanguage(String province, Lang language) throws WeiboException {
        return getCities(province, null, language);
    }
}
