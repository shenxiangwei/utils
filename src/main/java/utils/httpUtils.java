package utils;

import com.mzlion.easyokhttp.HttpClient;
import com.mzlion.easyokhttp.response.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br>
 * 标题: <br>
 * 描述: <br>
 *
 * @author shenxiangwei
 * @time 2019/7/31 5:29 PM
 */
public class httpUtils {
    private static final Logger logger = LoggerFactory.getLogger(EncodeUtil.class);

    public static JSONArray post() {
        try {

            HttpResponse responseData = HttpClient
                    .post("www.baidu.com")
                    .header("auth-token", "value")
                    .queryString("tenantId", "value")
                    .execute();
            if (responseData.isSuccess() && responseData.getHttpCode() == HttpStatus.SC_OK) {
                JSONObject respJsonObj = JSONObject.parseObject(responseData.asString());
                if (respJsonObj.getString("code").equals("0")) {
                    return respJsonObj.getJSONArray("data");
                } else {
                    logger.error("post失败,返回:" + respJsonObj.toJSONString());
                }
            } else {
                logger.error("post失败[http_status=" + responseData.getHttpCode());
            }
        } catch (Exception e) {
            logger.error("post失败", e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
