package com.chenjianjx.order.workflow.demo.temporalio.flow.impl;

import com.chenjianjx.order.workflow.demo.temporalio.flow.CompleteResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlowStatus {
    private String lastActivity;
    private CompleteResult completeResult;
}
