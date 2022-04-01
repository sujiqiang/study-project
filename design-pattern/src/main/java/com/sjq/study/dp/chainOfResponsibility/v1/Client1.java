package com.sjq.study.dp.chainOfResponsibility.v1;

import com.sjq.study.dp.chainOfResponsibility.Record;

import java.util.Arrays;
import java.util.List;

/**
 * 测试客户端
 *
 * @author SJQ
 */
public class Client1 {

    public static void main(String[] args) {

        // 测试域名
        List<String> serverNames = Arrays.asList("www.baidu.cn", "www.baidu.sh.com", "www.baidu.com");

        DnsServer topDnsServer = new TopDnsServer();
        DnsServer cDsServer = new CTopDnsServer();
        DnsServer shDnsServer = new SHDnsServer();
        // 定义责任链（要指定链条关系）
        cDsServer.setUpperServer(topDnsServer);
        shDnsServer.setUpperServer(cDsServer);

        // 使用最低级的DNS服务器解析，并输出解析结果
        serverNames.forEach(s -> {
            Record record = shDnsServer.resolve(s);
            System.out.println(record.toString());
        });

    }

}
