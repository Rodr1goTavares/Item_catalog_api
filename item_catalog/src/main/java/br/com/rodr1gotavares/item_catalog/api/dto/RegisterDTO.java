package br.com.rodr1gotavares.item_catalog.api.dto;

import br.com.rodr1gotavares.item_catalog.entity.user.UserRole;

public record RegisterDTO(String username, String password, UserRole userRole) {}
