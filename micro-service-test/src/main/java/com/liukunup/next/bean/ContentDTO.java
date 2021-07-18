package com.liukunup.next.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文本或标签
 * 数据传输对象
 *
 * @author Liu Kun
 * @since 2021-07-18 23:05:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContentDTO {

    /**
     * 仅在更新文本或标签内容时被使用
     */
    private Long id;

    private String content;

    private UserDO user;
}
