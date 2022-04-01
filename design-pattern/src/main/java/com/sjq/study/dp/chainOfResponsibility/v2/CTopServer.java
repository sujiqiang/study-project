package com.sjq.study.dp.chainOfResponsibility.v2;

import com.sjq.study.dp.chainOfResponsibility.Record;

/**
 * @author SJQ
 */
public class CTopServer extends DnsServer {

    @Override
    protected boolean isLocal(String domain) {
        return domain.endsWith(".cn");
    }

    @Override
    protected void sign(Record record) {
        record.setOwner("中国顶级");
    }

}
