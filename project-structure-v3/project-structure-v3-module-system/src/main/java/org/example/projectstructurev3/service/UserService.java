package org.example.projectstructurev3.service;

import org.example.projectstructurev3.dto.CreateUserRequest;
import org.example.projectstructurev3.dto.PageResponse;
import org.example.projectstructurev3.dto.UpdateUserRequest;
import org.example.projectstructurev3.dto.UserPageRequest;
import org.example.projectstructurev3.dto.UserResponse;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse update(Long id, UpdateUserRequest request);

    void delete(Long id);

    UserResponse getById(Long id);

    PageResponse<UserResponse> page(UserPageRequest request);
}
