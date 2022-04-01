package com.sjq.study.rabbitmq;

import lombok.Data;

/**
 * @author java coder
 * @date 2022/3/31 16:21
 * @desc: description
 */
@Data
public class MqMessageDto {

    private boolean success;
    private String msg;

}
