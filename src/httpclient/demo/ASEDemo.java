package httpclient.demo;

import crypto.CryptoUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ASEDemo {

    public static void main(String arg[]) throws Exception {
//        String agentCont = "qa|" + (new Date().getTime() + 5 * 60 * 1000) / 1000;
        String agentCont = "qa|" + ((System.currentTimeMillis() / 1000 + 24 * 60 * 60));
        String secret = "12345678";
        String secret2 = "v0yp7MClE8Nd81Uhu6V@MOp4_HcH1npJK0JVuZIoEBG_hr>/mnlsX_<Z>Aqw1z:u";
        String agentEncryptContent = CryptoUtil.encrypt(agentCont, secret2);
        System.out.println("agentEncryptContent=" + agentEncryptContent);
        String agentDecode = CryptoUtil.decrypt(agentEncryptContent, secret2);
        System.out.println(agentDecode);
        System.out.println(agentDecode.split("\\|")[1]);

        String custCont = "510022|" + (new Date().getTime() + 24 * 60 * 60 * 1000) / 1000;
        String custEncryptContent = CryptoUtil.encrypt(custCont, secret2);
        System.out.println("custEncryptContent=" + custEncryptContent);

//        String encryptContent2 = CryptoUtil.encrypt(secret2, "HOTAI");
//        System.out.println("encryptContent2=" + encryptContent2);

        //2-rruYJdPlLbD/+W5hYS+SHEvrUbRuQzTBIOqtRlg=
        String eisContent = CryptoUtil.decrypt("2-38Lhjrd4Bjgyy7lBP/qdKRdGJJJgYkVoD+tGFrVk1XA=", secret2);
        Date eisDate = new Date(Long.parseLong(eisContent.split("\\|")[1]) * 1000);
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String eisTime = sdFormatter.format(eisDate);
        String eisContent2 = CryptoUtil.decrypt("2-mBWngXJwEIeoYmNGHd6pX2mRNPoJoxdVTvsfB/c=", secret2);
        Date dxpDate = new Date(Long.parseLong(eisContent2.split("\\|")[1]) * 1000);
        String dxpTime = sdFormatter.format(dxpDate);
        System.out.format("eisContent=%s, eisContent2=%s\n", eisContent, eisContent2);
        System.out.printf("eisTime=%s, dxpTime=%s\n", eisTime, dxpTime);

        long afterTime = (new Date().getTime() + 5 * 60 * 1000) / 1000;
        System.out.println("afterTime=" + afterTime);

        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("file.encoding"));
    }
}
