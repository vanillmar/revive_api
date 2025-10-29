/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.data.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mapstruct.Mapping;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Mapping(target = "createdAt", ignore = true)
@Mapping(target = "updatedAt", ignore = true)
@Mapping(target = "deletedAt", ignore = true)
@Mapping(target = "createdBy", ignore = true)
@Mapping(target = "updatedBy", ignore = true)
@Mapping(target = "deletedBy", ignore = true)
@Mapping(target = "deleted", ignore = true)

public @interface IgnoreAuditMapping {
}