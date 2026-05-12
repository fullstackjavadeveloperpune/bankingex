package com.fullstack.dto;

import com.fullstack.constant.UserRole;

public record LogInRequest(String custEmailId, String custPassword, UserRole role) {
}
