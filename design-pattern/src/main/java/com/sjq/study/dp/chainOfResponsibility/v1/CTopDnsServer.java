package com.sjq.study.dp.chainOfResponsibility.v1;

import com.sjq.study.dp.chainOfResponsibility.Record;

/**
 * 中国顶级 DNS 服务器
 *
 * @author SJQ
 */
public class CTopDnsServer extends DnsServer {

    /**
     * 是否本地地址
     *
     * @param domain 域名
     * @return 是/否
     */
    @Override
    public boolean isLocal(String domain) {
        return domain.endsWith(".cn");
    }

    /**
     * 输出解析后的地址信息
     *
     * @param domain 域名
     * @return
     */
    @Override
    public Record echo(String domain) {
        return super.echo(domain).setOwner("中国顶级DNS服务器");
    }

}
