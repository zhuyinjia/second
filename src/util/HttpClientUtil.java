package util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClientUtil {
    private RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();

    private static HttpClientUtil instance = null;

    private HttpClientUtil() {
    }

    public static HttpClientUtil getInstance() {
        if (instance == null) {
            instance = new HttpClientUtil();
        }
        return instance;
    }

    /**
     * 發送 post請求
     *
     * @param httpUrl 地址
     * @param authUser
     * @param headerMap
     */
    public String sendHttpPost(String httpUrl, String[] authUser, Map<String, String> headerMap) {
        HttpPost httpPost = new HttpPost(httpUrl);// 創建httpPost
        return sendHttpPost(httpPost, authUser, headerMap);
    }

    /**
     * 發送 post請求
     *
     * @param httpUrl     地址
     * @param requestBody
     * @param authUser
     * @param headerMap
     */
    public String sendHttpPost(String httpUrl, String requestBody, String[] authUser, Map<String, String> headerMap) {
        HttpPost httpPost = new HttpPost(httpUrl);// 創建httpPost
        try {
            //設置參數
            StringEntity stringEntity = new StringEntity(requestBody, "UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost, authUser, headerMap);
    }

    /**
     * 發送 post請求
     *
     * @param httpUrl 地址
     * @param maps    參數
     * @param requestBody
     * @param authUser
     * @param headerMap
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps, String requestBody, String[] authUser, Map<String, String> headerMap) {
        HttpPost httpPost = new HttpPost(httpUrl);// 創建httpPost
        // 創建參數隊列
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost, authUser, headerMap);
    }


    /**
     * 發送 post請求（帶文件）
     *
     * @param httpUrl   地址
     * @param maps      參數
     * @param fileLists 文件
     * @param authUser
     * @param headerMap
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists, String requestBody, String[] authUser, Map<String, String> headerMap) {
        HttpPost httpPost = new HttpPost(httpUrl);// 創建httpPost
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        for (String key : maps.keySet()) {
            meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
        }
        for (File file : fileLists) {
            FileBody fileBody = new FileBody(file);
            meBuilder.addPart("files", fileBody);
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost, authUser, headerMap);
    }

    /**
     * 發送 post請求
     *
     * @param httpPost
     * @param authUser
     * @param headerMap
     * @return
     */
    private String sendHttpPost(HttpPost httpPost, String[] authUser, Map<String, String> headerMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 創建默認的httpClient實例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Content-type", "application/json");
            if (null != authUser) {
                //username:password--->訪問的用戶名，密碼,並使用base64進行加密，將加密的字節信息轉化為string類型
                String encoding = DatatypeConverter.printBase64Binary(String.valueOf(authUser[0] + ":" + authUser[1]).getBytes("UTF-8"));
                httpPost.setHeader("Authorization", "Basic " + encoding);
            }
            if (null != headerMap) {
                // 設置http request header
                for (String key : headerMap.keySet()) {
                    httpPost.setHeader(key, headerMap.get(key));
                }
            }
            // 執行請求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
            } else {
                responseContent = String.valueOf(response.getStatusLine().getStatusCode());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 關閉連接，釋放資源
            try {
                httpClient.close();
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * 發送 put請求Https
     *
     * @param httpUrl
     * @param requestBody
     * @param headerMap
     */
    public String sendHttpPut(String httpUrl, String requestBody, Map<String, String> headerMap) {
        HttpPut httpPut = new HttpPut(httpUrl);// 創建httpPut
        try {
            //設置參數
            StringEntity stringEntity = new StringEntity(requestBody, "UTF-8");
            httpPut.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPut(httpPut, null, headerMap);
    }

    /**
     * 發送 put請求Https
     *
     * @param httpUrl
     * @param requestBody
     * @param authUser
     */
    public String sendHttpPut(String httpUrl, String requestBody, String[] authUser) {
        HttpPut httpPut = new HttpPut(httpUrl);// 創建httpPut
        try {
            //設置參數
            StringEntity stringEntity = new StringEntity(requestBody, "UTF-8");
            httpPut.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPut(httpPut, authUser, null);
    }

    /**
     * 發送 put請求Https
     *
     * @param httpUrl     地址
     * @param requestBody
     */
    public String sendHttpPut(String httpUrl, String requestBody) {
        HttpPut httpPut = new HttpPut(httpUrl);// 創建httpPut
        try {
            //設置參數
            StringEntity stringEntity = new StringEntity(requestBody, "UTF-8");
            httpPut.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPut(httpPut, null, null);
    }

    /**
     * 發送 put請求Https
     *
     * @param httpPut
     * @param authUser
     * @param headerMap
     *
     * @return
     */
    private String sendHttpPut(HttpPut httpPut, String[] authUser, Map<String, String> headerMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 創建默認的httpClient實例.
            httpClient = HttpClients.createDefault();
            httpPut.setConfig(requestConfig);
            httpPut.setHeader("Content-type", "application/json");
            if (null != authUser) {
                //username:password--->訪問的用戶名，密碼,並使用base64進行加密，將加密的字節信息轉化為string類型
                String encoding = DatatypeConverter.printBase64Binary(String.valueOf(authUser[0] + ":" + authUser[1]).getBytes("UTF-8"));
                httpPut.setHeader("Authorization", "Basic " + encoding);
            }
            if (null != headerMap) {
                // 設置http request header
                for (String key : headerMap.keySet()) {
                    httpPut.setHeader(key, headerMap.get(key));
                }
            }
            // 執行請求
            response = httpClient.execute(httpPut);
            entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
            } else {
                responseContent = String.valueOf(response.getStatusLine().getStatusCode());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 關閉連接，釋放資源
            try {
                httpClient.close();
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * 發送 get請求
     *
     * @param httpUrl
     */
    public String sendHttpGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 創建get請求
        return sendHttpGet(httpGet, null, null);
    }

    /**
     * 發送 get請求
     *
     * @param httpUrl
     * @param authUser
     */
    public String sendHttpGet(String httpUrl, String[] authUser) {
        HttpGet httpGet = new HttpGet(httpUrl);// 創建get請求
        return sendHttpGet(httpGet, authUser, null);
    }

    /**
     * 發送 get請求
     *
     * @param httpUrl
     * @param headerMap
     */
    public String sendHttpGet(String httpUrl, Map<String, String> headerMap) {
        HttpGet httpGet = new HttpGet(httpUrl);// 創建get請求
        return sendHttpGet(httpGet, null, headerMap);
    }

    /**
     * 發送 get請求
     *
     * @param httpGet
     * @param authUser
     * @param headerMap
     * @return
     */
    private String sendHttpGet(HttpGet httpGet, String[] authUser, Map<String, String> headerMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 創建默認的httpClient實例.
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            if (null != authUser) {
                //username:password--->訪問的用戶名，密碼,並使用base64進行加密，將加密的字節信息轉化為string類型
                String encoding = DatatypeConverter.printBase64Binary(String.valueOf(authUser[0] + ":" + authUser[1]).getBytes("UTF-8"));
                httpGet.setHeader("Authorization", "Basic " + encoding);
            }
            if (null != headerMap) {
                // 設置http request header
                for (String key : headerMap.keySet()) {
                    httpGet.setHeader(key, headerMap.get(key));
                }
            }
            // 執行請求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
            } else {
                responseContent = String.valueOf(response.getStatusLine().getStatusCode());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 關閉連接，釋放資源
            try {
                httpClient.close();
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * 發送delete請求
     *
     * @param httpUrl
     */
    public String sendHttpDelete(String httpUrl) {
        HttpDelete httpDelete = new HttpDelete(httpUrl);// 創建delete請求
        return sendHttpDelete(httpDelete, null, null);
    }

    /**
     * 發送delete請求
     *
     * @param httpUrl
     * @param authUser
     */
    public String sendHttpDelete(String httpUrl, String[] authUser) {
        HttpDelete httpDelete = new HttpDelete(httpUrl);// 創建delete請求
        return sendHttpDelete(httpDelete, authUser, null);
    }

    /**
     * 發送delete請求
     *
     * @param httpUrl
     * @param headerMap
     */
    public String sendHttpDelete(String httpUrl, Map<String, String> headerMap) {
        HttpDelete httpDelete = new HttpDelete(httpUrl);// 創建delete請求
        return sendHttpDelete(httpDelete, null, headerMap);
    }

    /**
     * 發送delete請求
     *
     * @param httpDelete
     * @param authUser
     * @param headerMap
     */
    private String sendHttpDelete(HttpDelete httpDelete, String[] authUser, Map<String, String> headerMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            httpClient = HttpClients.createDefault();
            if (null != authUser) {
                //username:password--->訪問的用戶名，密碼,並使用base64進行加密，將加密的字節信息轉化為string類型
                String encoding = DatatypeConverter.printBase64Binary(String.valueOf(authUser[0] + ":" + authUser[1]).getBytes("UTF-8"));
                httpDelete.setHeader("Authorization", "Basic " + encoding);
            }
            if (null != headerMap) {
                // 設置http request header
                for (String key : headerMap.keySet()) {
                    httpDelete.setHeader(key, headerMap.get(key));
                }
            }
            // 提交請求並以指定編碼獲取返回數據
            response = httpClient.execute(httpDelete);
            entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, "UTF-8");
            } else {
                responseContent = String.valueOf(response.getStatusLine().getStatusCode());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 關閉連接，釋放資源
            try {
                httpClient.close();
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

}