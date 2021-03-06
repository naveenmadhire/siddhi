/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.siddhi.core.subscription;

import org.wso2.siddhi.core.config.ExecutionPlanContext;
import org.wso2.siddhi.core.query.output.callback.OutputCallback;
import org.wso2.siddhi.core.query.output.ratelimit.OutputRateLimiter;

import java.util.Map;

public class SubscriptionRuntime {

    private final InputTransport inputTransport;
    private final InputMapper inputMapper;
    private final OutputRateLimiter outputRateLimiter;
    private final OutputCallback outputCallback;

    public SubscriptionRuntime(InputTransport inputTransport, InputMapper inputMapper, OutputRateLimiter outputRateLimiter, OutputCallback outputCallback) {

        this.inputTransport = inputTransport;
        this.inputMapper = inputMapper;
        this.outputRateLimiter = outputRateLimiter;
        this.outputCallback = outputCallback;
    }

    public void init(Map<String, String> transportOptions, ExecutionPlanContext executionPlanContext) {
        inputTransport.init(transportOptions, inputMapper,executionPlanContext );
    }

    public OutputCallback getOutputCallback() {
        return outputCallback;
    }
}
