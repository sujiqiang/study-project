package com.sjq.study.dp.chainOfResponsibility.v2;

import com.sjq.study.dp.chainOfResponsibility.Record;

/**
 * @author SJQ
 */
public class ShServer extends DnsServer {

    @Override
    protected boolean isLocal(String domain) {
        return domain.endsWith(".sh.com");
    }

    @Override
    protected void sign(Record record) {
        record.setOwner("上海");
    }

}
