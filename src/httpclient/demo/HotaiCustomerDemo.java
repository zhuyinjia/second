package httpclient.demo;

import util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class HotaiCustomerDemo {

    private static final String customerNumber = "510052";

    //獲取客戶理賠記錄
    public static void testSendHttpGet() {
        Map<String, String> headerMap = new HashMap<String, String>() {{
            put("X-Customer-Number", customerNumber);
        }};
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpGet("https://ibm6-hot-app01.eisgroup.com/customer/v1/policies", headerMap);
        System.out.println("reponse content:" + responseContent);
    }

    //取消保單,注意切換policyNumber
    public static void testSendHttpPost() {
        String policyNumber = "P0000700582";
        Map<String, String> headerMap = new HashMap<String, String>() {{
            put("X-Customer-Number", customerNumber);
        }};
        StringBuffer json = new StringBuffer("{\n" +
                "  \"cancellationDate\": \"2020-02-27\",\n" +
                "  \"cancellationReasonCd\": \"1\",\n" +
                "  \"supportingData\": \"string\"\n" +
                "}");
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPost("https://ibm6-hot-app01.eisgroup.com/customer/v1/policies/" + policyNumber + "/cancellation",
                        json.toString(), null, headerMap);
        System.out.println("reponse content:" + responseContent);
    }

    //更新續保訊息，customer模組只有這一個put,但是API跑不通，原因是EIS的API修改了...
    public static void testSendHttpPut() {
        String policyNumber = "P0000700590";
        Map<String, String> headerMap = new HashMap<String, String>() {{
            put("X-Customer-Number", customerNumber);
        }};
        StringBuffer json = new StringBuffer("{\n" + "  \"customerNumber\": \"510052\",\n" +
                "  \"effectiveDate\": \"2021-02-26T16:00:00Z\",\n" +
                "  \"vehicleCategoryCd\": \"04\",\n" +
                "  \"vehicleTypeCd\": \"C\",\n" +
                "  \"currencyCode\": \"TWD\",\n" +
                "  \"countryCd\": \"TW\",\n" +
                "  \"riskStateCd\": \"CYQ\",\n" +
                "  \"packageCd\": \"Standard\",\n" +
                "  \"coverageTypeCd\": \"single\",\n" +
                "  \"insuredsAndDrivers\": [").append("{\n" +
                "  \"oid\": \"l-GCoT6xnqMZFAnJDLlBjQ\",\n" +
                "  \"firstName\": \"深深\",\n" +
                "  \"middleName\": \"\",\n" +
                "  \"lastName\": \"許\",\n" +
                "  \"dateOfBirth\": \"1989-08-02\",\n" +
                "  \"genderCd\": \"male\",\n" +
                "  \"maritalStatusCd\": \"M\",\n" +
                "  \"employmentStatusCd\": \"EMP_FT\",\n" +
                "  \"address\": {\n" +
                "    \"oid\": \"iA2u2xeZuBwXQ-ZxWjpMzg\",\n" +
                "    \"addressLine1\": \"創意產業園\",\n" +
                "    \"addressLine2\": \"\",\n" +
                "    \"addressLine3\": \"\",\n" +
                "    \"city\": \"273\",\n" +
                "    \"county\": null,\n" +
                "    \"postalCode\": \"820\",\n" +
                "    \"stateProvCd\": \"KHH\",\n" +
                "    \"countryCd\": \"TW\",\n" +
                "    \"addressTypeCd\": \"MAILING\"\n" +
                "  },").append("\"phones\": [\n" +
                "{\n" +
                "\"oid\": \"gwQFgl_u2PoYXnstnOVN-Q\",\n" +
                "\"phone\": \"1234567890\",\n" +
                "\"phoneTypeCd\": \"WORK\"\n" +
                "}\n" +
                "],").append("\"emails\": [\n" +
                "{\n" +
                "\"oid\": \"kG_jQQTpGlHYViUpi0xJ4Q\",\n" +
                "\"email\": \"shenshen.zhou@eisgroup.com\",\n" +
                "\"emailTypeCd\": \"PERS\"\n" +
                "}\n" +
                "],\n" +
                "\"insured\": {\n" +
                "\"primaryInsuredInd\": true\n" +
                "},\n" +
                "\"driver\": null\n" +
                "}\n" +
                "],").append("\"vehicles\": [\n" +
                "{\n" +
                "\"oid\": \"oiCf1bHWzC0MaYrJnYlCVA\",\n" +
                "\"vin\": \"01011100\",\n" +
                "\"make\": \"裕隆(國產)\",\n" +
                "\"model\": null,\n" +
                "\"year\": \"200608\",\n" +
                "\"type\": null,\n" +
                "\"registrationNo\": \"L100000000\",\n" +
                "\"vehicleUsageCd\": null,\n" +
                "\"marketValue\": 329000,\n" +
                "\"costNew\": null,\n" +
                "\"distancePerWeek\": null,\n" +
                "\"estimatedAnnualDistanceCd\": \"12000_17999\",\n" +
                "\"noVinReasonCd\": null,\n" +
                "\"performanceCd\": null,\n" +
                "\"otherModel\": null,\n" +
                "\"otherManufacturer\": null,\n" +
                "\"otherSeries\": null,\n" +
                "\"series\": \"速利:321 LB 1.3 (M)   *\",\n" +
                "\"registeredStateCd\": null,\n" +
                "\"manufactureYear\": null,\n" +
                "\"garagingAddress\": {\n" +
                "\"addressLine1\": \"長春路300號\",\n" +
                "\"addressLine2\": \"\",\n" +
                "\"addressLine3\": \"\",\n" +
                "\"city\": \"2\",\n" +
                "\"county\": null,\n" +
                "\"postalCode\": \"101\",\n" +
                "\"stateProvCd\": \"TPE\",\n" +
                "\"countryCd\": \"TW\"\n" +
                "},\n" +
                "\"registeredOwner\": {\n" +
                "\"firstName\": \"Mary\",\n" +
                "\"middleName\": \"\",\n" +
                "\"lastName\": \"One\",\n" +
                "\"registrationTypeCd\": \"SNGL\"\n" +
                "},").append("\"coverages\": {\n" +
                "\"liabilityCombinedSingleLimitCd\": null,\n" +
                "\"liabilityCombinedSingleDeductibleCd\": null,\n" +
                "\"uninsuredMotoristCombinedSingleLimitCd\": null,\n" +
                "\"uninsuredMotoristCombinedSingleDeductibleCd\": null,\n" +
                "\"medicalPaymentsLimitCd\": null,\n" +
                "\"bodyInjuryLimitCd\": \"300000\",\n" +
                "\"propertyDamageLimitCd\": \"5000\",\n" +
                "\"uninsuredMotoristBodyInjuryLimitCd\": null,\n" +
                "\"uninsuredMotoristPropertyDamageLimitCd\": 30000,\n" +
                "\"comprehensiveDeductibleCd\": null,\n" +
                "\"collisionDeductibleCd\": null,\n" +
                "\"roadsideAssistanceCd\": null,\n" +
                "\"rentalReimbursementCd\": null\n" +
                "}\n" +
                "}\n" +
                "]\n" +
                "\n" +
                "}");
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpPut("https://ibm6-hot-app01.eisgroup.com/customer/v1/renewals-auto/" + policyNumber, json.toString(), headerMap);
        System.out.println("reponse content:" + responseContent);
    }

    //刪除續保的保單(注意切換policynumber)
    public static void testSendHttpDelete() {
        String policyNumber = "P0000700590";
        Map<String, String> headerMap = new HashMap<String, String>() {{
            put("X-Customer-Number", customerNumber);
        }};
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpDelete("https://ibm6-hot-app01.eisgroup.com/" +
                        "/customer/v1/renewals/" + policyNumber, headerMap);
        System.out.println("reponse content:" + responseContent);
    }

    public static void main(String arg[]) throws Exception {
        testSendHttpGet();
        testSendHttpPost();
        testSendHttpPut();
        testSendHttpDelete();
    }
}

