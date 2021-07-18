package com.liukunup.next.service;

import com.liukunup.next.bean.ContentDTO;

import java.util.List;

/**
 * 标签
 * 服务接口
 *
 * @author Liu Kun
 * @since 2020-07-18
 */
public interface ILabelService {

    /**
     * 新增标签
     *
     * @param label 标签信息
     * @return 新增操作结果
     */
    boolean insertLabel(ContentDTO label);

    /**
     * 删除标签
     *
     * @param label 标签信息
     * @return 删除操作结果
     */
    boolean deleteLabel(ContentDTO label);

    /**
     * 更新标签信息
     *
     * @param label 标签信息
     * @return 更新操作结果
     */
    boolean updateLabel(ContentDTO label);

    /**
     * 通过标签内容查询标签
     *
     * @param label 标签信息
     * @return 标签信息
     */
    ContentDTO selectLabel(ContentDTO label);

    /**
     * 分页列举标签
     *
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return 标签列表
     */
    List<ContentDTO> listLabel(Integer pageNo, Integer pageSize);
}
