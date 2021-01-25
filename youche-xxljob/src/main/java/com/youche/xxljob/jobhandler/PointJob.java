package com.youche.xxljob.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PointJob {

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("pointOverdueResetJobHandler")
    public ReturnT<String> pointOverdueResetJobHandler(String param) throws Exception {
        XxlJobLogger.log("pointOverdueResetJobHandler is called");

        for (int i = 0; i < 100; i++) {
            XxlJobLogger.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(1);
        }
        return ReturnT.SUCCESS;
    }
}
