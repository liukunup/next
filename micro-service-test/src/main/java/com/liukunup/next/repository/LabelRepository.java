package com.liukunup.next.repository;

import com.liukunup.next.bean.entity.LabelPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 标签
 * 数据库接口
 *
 * @author Liu Kun
 * @since 2021-07-18 23:03:03
 */
@Repository
public interface LabelRepository extends JpaRepository<LabelPO, Long> {

    /**
     * 通过标签ID查询标签
     * @param id 标签ID
     * @return 标签
     */
    LabelPO findLabelPOById(Long id);

    /**
     * 通过标签内容查询标签
     * @param content 标签内容
     * @return 标签
     */
    LabelPO findLabelPOByContent(String content);

    /**
     * 通过标签内容删除标签
     * @param content 标签内容
     */
    void deleteLabelPOByContent(String content);
}
