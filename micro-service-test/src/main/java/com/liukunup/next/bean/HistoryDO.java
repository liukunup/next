package com.liukunup.next.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 测试用例执行历史记录
 * 业务抽象对象
 *
 * @author Liu Kun
 * @since 2021-07-18 22:29:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HistoryDO implements Serializable {

    /**
     * 用例编号
     */
    private Long testcaseId;

    /**
     * 任务编号
     * 使用其他系统的id号或者UUID
     */
    private String taskId;

    /**
     * 用例包含字段列表
     */
    private List<FieldCompareDO> fieldList;

    /**
     * 工号
     * 可选
     */
    private String employeeId;
}
