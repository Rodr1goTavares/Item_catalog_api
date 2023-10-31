package br.com.rodr1gotavares.item_catalog.api.dto.auth;

import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull String username, @NotNull String password) {}