package com.chester.shen.event;

import com.alibaba.fastjson.JSONObject;
import com.chester.shen.component.EventManager;
import com.chester.shen.constant.PublishStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chester Shen
 * @date 2023/01/30 15:42
 */
@RestController
public class EventController {

    @Autowired
    private EventManager eventManager;

    @RequestMapping("/publish")
    public void publish() {
        String str = "{\"bizType\":\"callback_sales_order_ex_warehouse\",\"data\":\"{\\\"auditStep\\\":3,\\\"details\\\":[{\\\"actualNum\\\":3.00000000,\\\"inspectionNum\\\":3.00000000,\\\"omsOrderDetailSplits\\\":[{\\\"batchNumber\\\":\\\"1\\\",\\\"billDetailID\\\":116066398,\\\"goodsCode\\\":\\\"22100911\\\",\\\"goodsDesc\\\":\\\"\\\",\\\"goodsID\\\":2022841565,\\\"goodsNum\\\":3.0,\\\"productionDate\\\":20221010000000,\\\"taxAmount\\\":0.0,\\\"taxPrice\\\":0.0}],\\\"orgCode\\\":\\\"HN010000089999\\\",\\\"productCode\\\":\\\"22100911\\\",\\\"productNum\\\":3.00000000},{\\\"actualNum\\\":3.00000000,\\\"inspectionNum\\\":3.00000000,\\\"omsOrderDetailSplits\\\":[{\\\"batchNumber\\\":\\\"1\\\",\\\"billDetailID\\\":116066399,\\\"goodsCode\\\":\\\"22100913\\\",\\\"goodsDesc\\\":\\\"\\\",\\\"goodsID\\\":2022841567,\\\"goodsNum\\\":3.0,\\\"productionDate\\\":20221010000000,\\\"taxAmount\\\":0.0,\\\"taxPrice\\\":0.0}],\\\"orgCode\\\":\\\"HN010000089999\\\",\\\"productCode\\\":\\\"22100913\\\",\\\"productNum\\\":3.00000000}],\\\"ent\\\":\\\"HUAZHUTEST\\\",\\\"orderID\\\":587916,\\\"orderNo\\\":\\\"SA20221027587915\\\"}\",\"delayTime\":0,\"isAlarmMail\":\"1\",\"isAlarmWX\":\"0\",\"isDelay\":\"1\",\"isException\":\"0\",\"isRetry\":\"1\",\"isWriteToDB\":\"0\",\"mails\":[\"22458499@qq.com\",\"sxcsch@163.com\"],\"retryCount\":10,\"retryTime\":100,\"sendClients\":[\"supplychain-oms-api\"],\"serverMsgCode\":\"0\",\"serverSend\":\"0\",\"supplychainAdminServiceAccept\":\"1\"}\n";
        RabbitMQCommonEvent rabbitMQCommonEvent = JSONObject.parseObject(str, RabbitMQCommonEvent.class);
        rabbitMQCommonEvent.setBizType("callback_sales_order_ex_warehouse");
        rabbitMQCommonEvent.setSendClients(new String[]{"springboot-log4j2-demo"});
        rabbitMQCommonEvent.setServerSend("1");
        rabbitMQCommonEvent.setStrategy(PublishStrategy.LOCAL);
        rabbitMQCommonEvent.setClassName(RabbitMQCommonEvent.class.getSimpleName());
        rabbitMQCommonEvent.setData(str);
        eventManager.publish(rabbitMQCommonEvent);
    }
}
