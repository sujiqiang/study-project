package com.sjq.study.dp.chainOfResponsibility.v1;

import com.sjq.study.dp.chainOfResponsibility.Record;
import lombok.Data;

import java.util.Random;

/**
 * @author SJQ
 * DNS 服务器
 */
@Data
public abstract class DnsServer {

    /**
     * 父级DNS服务器
     */
    public DnsServer upperServer;

    /**
     * 解析域名。
     * 解析域名的框架是固定的，所以在抽象类中实现，无需子类实现。且定义为 final，不允许修改。
     *
     * @param domain 域名
     * @return 解析的记录
     */
    public final Record resolve(String domain) {
        if (isLocal(domain)) {
            return echo(domain);
        } else {
            return upperServer.resolve(domain);
        }
    }

    /**
     * 是否本区域地址。
     * 由子类根据自己的逻辑判断。
     *
     * @param domain 域名
     * @return 是/否
     */
    public abstract boolean isLocal(String domain);

    /**
     * 输出解析后的地址信息。
     * 提供默认的方法，子类可以个性化输出。
     *
     * @param domain 域名
     * @return
     */
    public Record echo(String domain) {
        return new Record().setDomain(domain)
                .setIp(getIpAddress());
    }

    /**
     * 模拟DNS解析地址
     *
     * @return
     */
    public String getIpAddress() {
        Random random = new Random();
        return random.nextInt(255) + "." + random.nextInt(255) + "." + random.nextInt(255);
    }

}
