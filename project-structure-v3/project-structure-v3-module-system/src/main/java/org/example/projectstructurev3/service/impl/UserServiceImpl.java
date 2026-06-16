package org.example.projectstructurev3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import org.example.projectstructurev3.domain.UserDO;
import org.example.projectstructurev3.dto.CreateUserRequest;
import org.example.projectstructurev3.dto.PageResponse;
import org.example.projectstructurev3.dto.UpdateUserRequest;
import org.example.projectstructurev3.dto.UserPageRequest;
import org.example.projectstructurev3.dto.UserResponse;
import org.example.projectstructurev3.exception.BusinessException;
import org.example.projectstructurev3.mapper.UserMapper;
import org.example.projectstructurev3.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    private static final long MAX_PAGE_SIZE = 100;

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponse create(CreateUserRequest request) {
        assertUsernameAvailable(request.getUsername(), null);
        assertEmailAvailable(request.getEmail(), null);

        UserDO user = new UserDO();
        user.setUsername(request.getUsername());
        user.setNickname(request.getNickname());
        user.setEmail(blankToNull(request.getEmail()));
        user.setPhone(blankToNull(request.getPhone()));
        user.setStatus(request.getStatus());
        userMapper.insert(user);
        return toResponse(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponse update(Long id, UpdateUserRequest request) {
        UserDO existing = userMapper.selectById(id);
        if (existing == null) {
            throw new BusinessException("用户不存在");
        }
        assertEmailAvailable(request.getEmail(), id);

        UserDO user = new UserDO();
        user.setId(id);
        user.setNickname(request.getNickname());
        user.setEmail(blankToNull(request.getEmail()));
        user.setPhone(blankToNull(request.getPhone()));
        user.setStatus(request.getStatus());
        userMapper.updateById(user);
        return getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        int affectedRows = userMapper.deleteById(id);
        if (affectedRows == 0) {
            throw new BusinessException("用户不存在");
        }
    }

    @Override
    public UserResponse getById(Long id) {
        UserDO user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return toResponse(user);
    }

    @Override
    public PageResponse<UserResponse> page(UserPageRequest request) {
        long pageNum = normalizePageNum(request.getPageNum());
        long pageSize = normalizePageSize(request.getPageSize());
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<UserDO>()
                .and(StringUtils.hasText(request.getKeyword()), query -> query
                        .like(UserDO::getUsername, request.getKeyword())
                        .or()
                        .like(UserDO::getNickname, request.getKeyword()))
                .orderByDesc(UserDO::getId);

        Page<UserDO> page = userMapper.selectPage(Page.of(pageNum, pageSize), wrapper);
        List<UserResponse> records = page.getRecords().stream()
                .map(this::toResponse)
                .toList();
        return new PageResponse<>(page.getCurrent(), page.getSize(), page.getTotal(), records);
    }

    private void assertUsernameAvailable(String username, Long excludeId) {
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<UserDO>()
                .eq(UserDO::getUsername, username)
                .ne(excludeId != null, UserDO::getId, excludeId);
        if (userMapper.selectCount(wrapper) > 0) {
            throw new BusinessException("用户名已存在");
        }
    }

    private void assertEmailAvailable(String email, Long excludeId) {
        if (!StringUtils.hasText(email)) {
            return;
        }
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<UserDO>()
                .eq(UserDO::getEmail, email)
                .ne(excludeId != null, UserDO::getId, excludeId);
        if (userMapper.selectCount(wrapper) > 0) {
            throw new BusinessException("邮箱已存在");
        }
    }

    private long normalizePageNum(long pageNum) {
        return pageNum < 1 ? 1 : pageNum;
    }

    private long normalizePageSize(long pageSize) {
        if (pageSize < 1) {
            return 10;
        }
        return Math.min(pageSize, MAX_PAGE_SIZE);
    }

    private String blankToNull(String value) {
        return StringUtils.hasText(value) ? value : null;
    }

    private UserResponse toResponse(UserDO user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setNickname(user.getNickname());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setStatus(user.getStatus());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
}
