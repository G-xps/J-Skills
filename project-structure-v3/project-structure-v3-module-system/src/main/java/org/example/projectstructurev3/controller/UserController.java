package org.example.projectstructurev3.controller;

import jakarta.validation.Valid;
import org.example.projectstructurev3.common.Result;
import org.example.projectstructurev3.dto.CreateUserRequest;
import org.example.projectstructurev3.dto.PageResponse;
import org.example.projectstructurev3.dto.UpdateUserRequest;
import org.example.projectstructurev3.dto.UserPageRequest;
import org.example.projectstructurev3.dto.UserResponse;
import org.example.projectstructurev3.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Result<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        return Result.success(userService.create(request));
    }

    @PutMapping("/{id}")
    public Result<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        return Result.success(userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<UserResponse> getById(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping
    public Result<PageResponse<UserResponse>> page(UserPageRequest request) {
        return Result.success(userService.page(request));
    }
}
