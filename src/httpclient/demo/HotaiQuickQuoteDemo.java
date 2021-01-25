package httpclient.demo;

import util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class HotaiQuickQuoteDemo {

    // 獲取自然人客戶基本資訊
    public static void testSendHttpGet() {
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpGet("https://ibm6-hot-app01.eisgroup.com/quickquote/v1/leads/510054");
        System.out.println("reponse content:" + responseContent);
    }

    // 新建自然人客戶
    public static void testSendHttpPost() {
        StringBuffer json = new StringBuffer("{\n" +
                "  \"firstName\": \"Mary\",\n" +
                "  \"lastName\": \"One\",\n" +
                "  \"middleName\": \"\",\n" +
                "  \"dateOfBirth\": \"1981-11-11\",\n" +
                "  \"gender\": \"female\",\n" +
                "  \"addresses\": [{\n" +
                "  \"addressLine1\": \"345 California Str\",\n" +
                "  \"addressLine2\": \"10 flr\",\n" +
                "  \"addressLine3\": \" \",\n" +
                "  \"city\": \"San Francisco\",\n" +
                "  \"county\": \"San Francisco\",\n" +
                "  \"postalCode\": \"94104\",\n" +
                "  \"stateProvCd\": \"CA\",\n" +
                "  \"countryCd\": \"US\"\n" +
                "  }],\n" +
                "  \"phones\": [{\n" +
                "  \"phone\": \"1234567890\",\n" +
                "  \"phoneTypeCd\": \"WORK\"\n" +
                "  }],\n" +
                "  \"emails\": [{\n" +
                "  \"email\": \"john@email.com\",\n" +
                "  \"emailTypeCd\": \"PERS\"\n" +
                "  }],\n" +
                "  \"chats\": [{\n" +
                "  \"chatId\": \"2789425\",\n" +
                "  \"chatTypeCd\": \"SKYPE\"\n" +
                "  }],\n" +
                "  \"socialNets\": [{\n" +
                "  \"socialNetId\": \"101533689\",\n" +
                "  \"socialNetTypeCd\": \"FCB\"\n" +
                "  }],\n" +
                "  \"webAddresses\": [{\n" +
                "  \"webAddress\": \"www.eisgroup.com\",\n" +
                "  \"webAddressTypeCd\": \"PERS\"\n" +
                "  }],\n" +
                "  \"agencies\": [{\n" +
                "  \"agencyCode\": \"QAG\"\n" +
                "  }],\n" +
                "  \"taxId\": \"A261532833\"\n" +
                "}");
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPost("https://ibm6-hot-app01.eisgroup.com/quickquote/v1/leads/find-or-create",
                        json.toString(), null, null);
        System.out.println("reponse content:" + responseContent);
    }

    // 修改潛在自然人客戶基本資訊，但是API跑不通
    public static void testSendHttpPut() {
        StringBuffer json = new StringBuffer("{\n" +
                "  \"firstName\": \"Mary\",\n" +
                "  \"lastName\": \"One\",\n" +
                "  \"middleName\": \"\",\n" +
                "  \"dateOfBirth\": \"1981-11-11\",\n" +
                "  \"gender\": \"female\",\n" +
                "  \"addresses\": [{\n" +
                "  \"addressLine1\": \"345 California Str\",\n" +
                "  \"addressLine2\": \"10 flr\",\n" +
                "  \"addressLine3\": \" \",\n" +
                "  \"city\": \"San Francisco\",\n" +
                "  \"county\": \"San Francisco\",\n" +
                "  \"postalCode\": \"94104\",\n" +
                "  \"stateProvCd\": \"CA\",\n" +
                "  \"countryCd\": \"US\"\n" +
                "  }],\n" +
                "  \"phones\": [{\n" +
                "  \"phone\": \"1234567890\",\n" +
                "  \"phoneTypeCd\": \"WORK\"\n" +
                "  }],\n" +
                "  \"emails\": [{\n" +
                "  \"email\": \"john@email.com\",\n" +
                "  \"emailTypeCd\": \"PERS\"\n" +
                "  }],\n" +
                "  \"chats\": [{\n" +
                "  \"chatId\": \"2789425\",\n" +
                "  \"chatTypeCd\": \"SKYPE\"\n" +
                "  }],\n" +
                "  \"socialNets\": [{\n" +
                "  \"socialNetId\": \"101533689\",\n" +
                "  \"socialNetTypeCd\": \"FCB\"\n" +
                "  }],\n" +
                "  \"webAddresses\": [{\n" +
                "  \"webAddress\": \"www.eisgroup.com\",\n" +
                "  \"webAddressTypeCd\": \"PERS\"\n" +
                "  }],\n" +
                "  \"agencies\": [{\n" +
                "  \"agencyCode\": \"QAG\"\n" +
                "  }],\n" +
                "  \"taxId\": \"A261532833\"\n" +
                "}");
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPut("https://ibm6-hot-app01.eisgroup.com/quickquote/v1/leads/510052", json.toString());
        System.out.println("reponse content:" + responseContent);
    }

    // 獲取lookup代碼和名稱映射表（車險）
    public static void testSendLookUpHttpGet() {
        Map<String, String> headerMap = new HashMap<String, String>() {{
            //接受en-US，zh-TW
            put("Accept-Language", "zh-TW");
        }};
        String lookupName = "Country";
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpGet("https://ibm6-hot-app01.eisgroup.com/quickquote/v1/lookups/" + lookupName, headerMap);
        System.out.println("reponse content:" + responseContent);
    }

    public static void main(String arg[]) throws Exception {
//        testSendHttpGet();
//        testSendHttpPost();
//        testSendHttpPut();
        testSendLookUpHttpGet();

    }
}
