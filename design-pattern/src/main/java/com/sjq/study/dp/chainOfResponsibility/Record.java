package com.sjq.study.dp.chainOfResponsibility;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author SJQ
 */
@Data
@Accessors(chain = true)
public class Record {

    private String domain;
    private String ip;
    private String owner;

    @Override
    public String toString() {
        return "Record{" +
                "domain=" + domain +
                ", ip=" + ip +
                ", owner='" + owner +
                "}";
    }

}
