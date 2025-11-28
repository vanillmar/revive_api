/* Copyright (C)2025  Vanilson Marcos */
package com.example.revive_app.repository.specifications;

import com.example.revive_app.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    private UserSpecifications() {
        // Private constructor to prevent instantiation
    }
    public static Specification<User> isActive() {
        return (root, query, cb) -> cb.isTrue(root.get("active"));
    }

    public static Specification<User> hasRole(String role) {
        return (root, query, cb) -> cb.equal(root.get("role"), role);
    }

    public static Specification<User> isDeleted() {
        return (root, query, cb) -> cb.and(cb.isNotNull(root.get("deletedAt")), cb.isFalse(root.get("active")),
                cb.isFalse(root.get("enabled")));
    }

    public static Specification<User> isNotDeleted() {
        return (root, query, cb) -> cb.and(cb.isNull(root.get("deletedAt")), cb.isTrue(root.get("active")),
                cb.isTrue(root.get("enabled")));
    }

    public static Specification<User> searchByUser(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true
            }

            String searchPattern = "%" + search.toLowerCase() + "%";

            // Search across multiple fields (adjust field names as needed)
            return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), searchPattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), searchPattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("id")), searchPattern));
        };
    }
}