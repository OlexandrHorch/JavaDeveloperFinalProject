package com.nutritionalsupplements.service;

import com.nutritionalsupplements.entity.Supplement;
import org.springframework.data.jpa.domain.Specification;

public class SupplementSpecifications {

    public static Specification<Supplement> withNameContaining(String codeName) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("name")), "%" + codeName.toLowerCase() + "%");
    }
}
