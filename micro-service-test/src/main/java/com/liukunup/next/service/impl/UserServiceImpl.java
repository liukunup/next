package com.liukunup.next.service.impl;

import com.liukunup.next.bean.UserDO;
import com.liukunup.next.bean.entity.UserPO;
import com.liukunup.next.repository.UserRepository;
import com.liukunup.next.service.IUserService;
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
import java.util.UUID;

/**
 * 用户
 * 服务实现
 *
 * @author Liu Kun
 * @since 2021-07-18 22:59:03
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

    /**
     * 秘钥最小长度
     */
    private static final Integer MIN_KEY_SIZE = 16;

    /**
     * 分页最大数量
     */
    private static final Integer MAX_PAGE_SIZE = 100;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean insertUser(UserDO user) {
        try {
            // 判断输入是否为null
            if (null == user) {
                log.error("调用insertUser函数时,输入参数为null!");
                return false;
            }

            // 用户已存在
            UserPO tmpUser = userRepository.findUserPOByEmployeeId(user.getEmployeeId());
            if (null != tmpUser) {
                log.error("调用insertUser函数时,发现输入的用户工号信息已存在!");
                return false;
            }

            // 构建待使用的持久化对象
            UserPO temp = new UserPO();
            temp.setEmployeeId(user.getEmployeeId());
            temp.setNickname(user.getNickname());
            temp.setPhone(user.getPhone());
            temp.setEmail(user.getEmail());
            temp.setDepartment(user.getDepartment());
            // 使用用户设置的SecretKey还是系统自动生成的SecretKey
            String secretKey = user.getSecretKey();
            if (null != secretKey && secretKey.length() >= MIN_KEY_SIZE) {
                temp.setSecretKey(secretKey);
            } else {
                temp.setSecretKey(UUID.randomUUID().toString());
            }
            // 设置创建时间和更新时间一致
            Date date = new Date();
            temp.setGmtCreate(date);
            temp.setGmtModified(date);
            log.debug("调用insertUser函数时,待持久化的用户信息为{}", temp);

            // 新增用户
            UserPO result = userRepository.save(temp);

            // 判断新增用户是否成功
            if ((result.getId() != null) && (result.getId() > 0)
                    && (result.getGmtCreate() != null)
                    && (result.getGmtModified() != null)) {
                log.debug("调用insertUser函数成功,已持久化的用户信息为{}", result);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.error("调用insertUser函数失败,输入的用户信息为{}", user);
        return false;
    }

    @Override
    public boolean deleteUser(UserDO user) {
        try {
            // 判断输入是否为null
            if (null == user) {
                log.error("调用deleteUser函数时,输入参数为null!");
                return false;
            }

            // 删除用户
            userRepository.deleteUserPOByEmployeeId(user.getEmployeeId());

            // 通过查询来确认是否删除成功
            UserPO temp = userRepository.findUserPOByEmployeeId(user.getEmployeeId());

            // 判断删除用户是否成功
            if (null == temp) {
                log.debug("调用deleteUser函数成功,已删除的持久化用户信息为{}", user);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.warn("调用deleteUser函数失败,输入的用户信息为{}", user);
        return false;
    }

    @Override
    public boolean updateUser(UserDO user) {
        try {
            // 判断输入是否为null
            if (null == user) {
                log.error("调用updateUser函数时,输入参数为null!");
                return false;
            }

            // 通过工号查询到用户信息
            UserPO tmpUser = userRepository.findUserPOByEmployeeId(user.getEmployeeId());
            if (null == tmpUser) {
                log.warn("调用updateUser函数时,发现输入的用户工号信息不存在!");
                return false;
            }

            // 更新用户信息
            String nickname = user.getNickname();
            if (null != nickname && !nickname.isEmpty()) {
                tmpUser.setNickname(user.getNickname());
            }

            String phone = user.getPhone();
            if (null != phone && !phone.isEmpty()) {
                tmpUser.setPhone(user.getPhone());
            }

            String email = user.getEmail();
            if (null != email && !email.isEmpty()) {
                tmpUser.setEmail(user.getEmail());
            }

            String department = user.getDepartment();
            if (null != department && !department.isEmpty()) {
                tmpUser.setDepartment(user.getDepartment());
            }

            // 设置更新时间
            Date date = new Date();
            tmpUser.setGmtModified(date);

            // 更新用户
            UserPO result = userRepository.save(tmpUser);

            log.debug("调用updateUser函数成功,已更新的持久化用户信息为{}", result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.error("调用updateUser函数失败,输入的用户信息为{}", user);
        return false;
    }

    @Override
    public UserDO selectUser(UserDO user) {
        try {
            // 判断输入是否为null
            if (null == user) {
                log.error("调用selectUser函数时,输入参数为null!");
                return null;
            }

            // 通过工号查询到用户信息
            UserPO result = userRepository.findUserPOByEmployeeId(user.getEmployeeId());

            // 构建业务抽象对象
            UserDO tmpUser = po2dto(result);
            if (null == tmpUser) {
                log.error("调用selectUser函数查询失败,已查询到的持久化用户信息为{}", result);
                return null;
            }

            // 返回查询结果
            log.debug("调用selectUser函数成功,已查询到的持久化用户信息为{}", tmpUser);
            return tmpUser;
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.error("调用selectUser函数失败,输入的用户信息为{}", user);
        return null;
    }

    @Override
    public List<UserDO> listUser(Integer pageNo, Integer pageSize) {
        try {
            // 检查传入的页码参数
            if ((null == pageNo) || (pageNo < 0)) {
                log.error("调用listUser函数时,输入参数pageNo为null或小于0!");
                return null;
            }

            // 检查传入的分页参数
            if ((null == pageSize) || (pageSize > MAX_PAGE_SIZE)) {
                log.error("调用listUser函数时,输入参数pageSize为null或大于{}!", MAX_PAGE_SIZE);
                return null;
            }

            // 分页查询
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<UserPO> userPage = userRepository.findAll(pageable);

            // 取出用户信息
            List<UserDO> result = new ArrayList<>();
            for(UserPO user : userPage.getContent()) {
                result.add(po2dto(user));
            }

            // 返回查询结果
            log.debug("调用listUser函数成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.error("调用listUser函数失败,输入参数 pageNo={} pageSize={}", pageNo, pageSize);
        return null;
    }

    /**
     * PO 转 DTO
     * @param po PO对象
     * @return DTO对象
     */
    private UserDO po2dto(UserPO po) {

        // 检查传入的PO对象参数
        if (null == po) {
            log.error("调用po2dto函数时,输入参数po为null!");
            return null;
        }

        // 构建业务抽象对象
        // 注意不透露秘钥信息
        UserDO dto = new UserDO();
        dto.setEmployeeId(po.getEmployeeId());
        dto.setNickname(po.getNickname());
        dto.setPhone(po.getPhone());
        dto.setEmail(po.getEmail());
        dto.setDepartment(po.getDepartment());

        return dto;
    }
}
