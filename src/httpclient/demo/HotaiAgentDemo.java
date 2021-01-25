package httpclient.demo;

import util.HttpClientUtil;

public class HotaiAgentDemo {
    //獲取所有招攬機構
    public static void testSendHttpGet() {
        String[] authUser = {"qa", "qa"};
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpGet("https://ibm6-hot-app01.eisgroup.com/agent/v1/agencies", authUser);
        System.out.println("reponse content:" + responseContent);
    }

    //添加自然人其他名稱
    public static void testSendHttpPost() {
        String[] authUser = {"qa", "qa"};
        StringBuffer json = new StringBuffer("{\n" +
                "  \"id\": 0,\n" +
                "  \"salutation\": \"Doctor\",\n" +
                "  \"firstName\": \"深\",\n" +
                "  \"middleName\": \"\",\n" +
                "  \"lastName\": \"許\",\n" +
                "  \"suffix\": \"\",\n" +
                "  \"designationCd\": \"\",\n" +
                "  \"designationDescription\": \"\"\n" +
                "}");
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPost("https://ibm6-hot-app01.eisgroup.com/" +
                        "agent/v1/customers/510052/individual-additional-names", json.toString(), authUser, null);
        System.out.println("reponse content:" + responseContent);
    }

    //更改自然人其他名稱
    public static void testSendHttpPut() {
        String[] authUser = {"qa", "qa"};
        StringBuffer json = new StringBuffer("{\n" +
                "  \"id\": 0,\n" +
                "  \"salutation\": \"Doctor\",\n" +
                "  \"firstName\": \"三\",\n" +
                "  \"middleName\": \"\",\n" +
                "  \"lastName\": \"許\",\n" +
                "  \"suffix\": \"\",\n" +
                "  \"designationCd\": \"\",\n" +
                "  \"designationDescription\": \"\"\n" +
                "}");
        System.out.println(json.toString());
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPut("https://ibm6-hot-app01.eisgroup.com/agent/v1/customers/510052/" +
                        "/individual-additional-names/1381960", json.toString(), authUser);
        System.out.println("reponse content:" + responseContent);
    }

    //刪除自然人客戶其它名稱(注意切換nameId)
    public static void testSendHttpDelete() {
        String[] authUser = {"qa", "qa"};
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpDelete("https://ibm6-hot-app01.eisgroup.com/" +
                        "agent/v1/customers/510052/individual-additional-names/1381963", authUser);

        System.out.println("reponse content:" + responseContent);
    }


    public static void main(String arg[]) throws Exception {
        testSendHttpGet();
        testSendHttpPost();
        testSendHttpPut();
        testSendHttpDelete();
    }
}

