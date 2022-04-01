package com.sjq.study.dp.chainOfResponsibility.v2;

import com.sjq.study.dp.chainOfResponsibility.Record;

/**
 * @author SJQ
 */
public class TopServer extends DnsServer {

    @Override
    protected boolean isLocal(String domain) {
        return true;
    }

    @Override
    protected void sign(Record record) {
        record.setOwner("世界顶级");
    }

}
