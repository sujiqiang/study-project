package com.sjq.study.dp.chainOfResponsibility.v2;

import com.sjq.study.dp.chainOfResponsibility.Record;

import java.util.Arrays;
import java.util.List;

/**
 * @author SJQ
 */
public class Client2 {

    public static void main(String[] args) {

        List<String> serverNames = Arrays.asList("www.baidu.cn", "www.baidu.sh.com", "www.baidu.com");

        DnsServer shServer = new ShServer();
        DnsServer cTopServerr = new CTopServer();
        DnsServer topServerr = new TopServer();

        // 设置父子关系(对被观察者 设置 观察者)
        shServer.setUpperServer(cTopServerr);
        cTopServerr.setUpperServer(topServerr);

        serverNames.forEach(serverName -> {
            Record record = new Record().setDomain(serverName);
            shServer.update(null, record);
            System.out.println(record);
        });


    }

}
