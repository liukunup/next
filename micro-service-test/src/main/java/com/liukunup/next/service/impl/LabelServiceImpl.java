package com.liukunup.next.service.impl;

import com.liukunup.next.bean.ContentDTO;
import com.liukunup.next.bean.UserDO;
import com.liukunup.next.bean.entity.LabelPO;
import com.liukunup.next.bean.entity.UserPO;
import com.liukunup.next.repository.LabelRepository;
import com.liukunup.next.repository.UserRepository;
import com.liukunup.next.service.ILabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 标签
 * 服务实现
 *
 * @author Liu Kun
 * @since 2021-07-18 23:04:19
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service("labelServiceImpl")
public class LabelServiceImpl implements ILabelService {

    /**
     * 分页最大数量
     */
    private static final Integer MAX_PAGE_SIZE = 100;

    @Autowired
    LabelRepository labelRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean insertLabel(ContentDTO label) {
        try {
            // 判断输入是否为null
            if (null == label) {
                return false;
            }

            // DTO 转 PO
            LabelPO temp = new LabelPO();
            temp.setContent(label.getContent());

            // 检查是否设置了关联用户
            UserDO user = label.getUser();
            if (null != user) {
                // 通过工号查询到用户信息
                UserPO tmpUser = userRepository.findUserPOByEmployeeId(user.getEmployeeId());
                temp.setUser(tmpUser);
            }

            // 设置创建时间和更新时间一致
            Date date = new Date();
            temp.setGmtCreate(date);
            temp.setGmtModified(date);

            // 新增标签
            LabelPO result = labelRepository.save(temp);

            // 判断新增标签是否成功
            if ((result.getId() != null) && (result.getId() > 0)
                    && (result.getGmtCreate() != null) && (result.getGmtModified() != null)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteLabel(ContentDTO label) {
        try {
            // 判断输入是否为null
            if (null == label) {
                return false;
            }

            // 通过文本内容查询文本
            LabelPO tmpLabel = labelRepository.findLabelPOByContent(label.getContent());
            if (null == tmpLabel) {
                return false;
            }

            // 删除标签
            labelRepository.deleteLabelPOByContent(label.getContent());

            // 通过查询来确认是否删除成功
            LabelPO temp = labelRepository.findLabelPOByContent(label.getContent());

            // 判断删除标签是否成功
            return (temp == null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateLabel(ContentDTO label) {
        try {
            // 判断输入是否为null
            if (null == label) {
                return false;
            }

            // 通过标签内容查询标签
            LabelPO temp = labelRepository.findLabelPOById(label.getId());
            if (null == temp) {
                return false;
            }

            // 更新标签信息
            String content = label.getContent();
            if (null != content && !content.isEmpty()) {
                temp.setContent(content);
            }

            // 设置更新时间
            Date date = new Date();
            temp.setGmtModified(date);

            // 更新标签
            LabelPO result = labelRepository.save(temp);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ContentDTO selectLabel(ContentDTO label) {
        try {
            // 判断输入是否为null
            if (null == label) {
                return null;
            }

            // 通过标签内容查询标签
            LabelPO result = labelRepository.findLabelPOByContent(label.getContent());
            if (null == result) {
                return null;
            }

            return po2dto(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ContentDTO> listLabel(Integer pageNo, Integer pageSize) {
        try {
            // 检查传入的页码参数
            if (null == pageNo || pageNo <= 0) {
                return null;
            }

            // 检查传入的分页参数
            if (null == pageSize || pageSize > MAX_PAGE_SIZE) {
                return null;
            }

            // 分页查询
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            Page<LabelPO> labelPage = labelRepository.findAll(pageable);

            // 取出标签信息
            List<ContentDTO> result = new ArrayList<>();
            for(LabelPO label : labelPage) {
                result.add(po2dto(label));
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * PO 转 DTO
     * @param po PO对象
     * @return DTO对象
     */
    private ContentDTO po2dto(LabelPO po) {

        // 检查传入的PO对象参数
        if (null == po) {
            return null;
        }

        // PO 转 DTO
        ContentDTO dto = new ContentDTO();
        dto.setId(po.getId());
        dto.setContent(po.getContent());

        // UserPO也需要转换
        // 此处不要透露个人信息
        UserPO poUser = po.getUser();
        if (null != poUser) {
            UserDO dtoUser = new UserDO();
            dtoUser.setEmployeeId(poUser.getEmployeeId());
            dtoUser.setNickname(poUser.getNickname());
            dtoUser.setDepartment(poUser.getDepartment());
            dto.setUser(dtoUser);
        }

        return dto;
    }
}
