package com.sjq.study.dp.chainOfResponsibility.v1;

import com.sjq.study.dp.chainOfResponsibility.Record;

/**
 * @author SJQ
 * 上海区域 DNS 服务器
 */
public class SHDnsServer extends DnsServer {

    /**
     * 是否本地地址
     *
     * @param domain 域名
     * @return 是/否
     */
    @Override
    public boolean isLocal(String domain) {
        return domain.endsWith(".sh.com");
    }

    /**
     * 输出解析后的地址信息
     *
     * @param domain 域名
     * @return
     */
    @Override
    public Record echo(String domain) {
        Record record = super.echo(domain);
        record.setOwner("上海区域服务器");
        return record;
    }

}
