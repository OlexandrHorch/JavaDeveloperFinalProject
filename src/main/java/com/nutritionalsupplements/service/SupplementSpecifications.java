package com.nutritionalsupplements.service;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.entity.SupplementCategory;
import com.nutritionalsupplements.entity.SupplementDanger;
import com.nutritionalsupplements.entity.SupplementOrigin;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SupplementSpecifications {

    public static Specification<Supplement> withUsingInfoLike(String usingInfo) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("using_info")), "%" + usingInfo.toLowerCase() + "%");
    }

    public static Specification<Supplement> withECodeLike(String eCode) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("eCod")), "%" + eCode.toLowerCase() + "%");
    }

    public static Specification<Supplement> withNameContaining(String codeName) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("name")), "%" + codeName.toLowerCase() + "%");
    }

    public static Specification<Supplement> withOtherNamesLike(String other_names) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("other_names")), "%" + other_names.toLowerCase() + "%");
    }

    public static Specification<Supplement> withCategory(SupplementCategory category) {
        return (root, cq, cb) -> cb.equal(root.get("category"), category);
    }

    public static Specification<Supplement> withDanger(SupplementDanger danger) {
        return (root, cq, cb) -> cb.equal(root.get("danger"), danger);
    }

    public static Specification<Supplement> withOrigin(SupplementOrigin origin) {
        return (root, cq, cb) -> cb.equal(root.get("origin"), origin);
    }

    public static Specification and(List<Specification<Supplement>> specifications) {
        return and(specifications.toArray(new Specification[specifications.size()]));
    }

    public static Specification<Supplement> and(Specification<Supplement>... specifications) {
        Specification<Supplement> result = Specification.where(specifications[0]);

        for (int i = 1; i < specifications.length; i++) {
            result = result.and(specifications[i]);
        }

        return result;
    }

    public static Specification<Supplement> or(Specification<Supplement>... specifications) {
        Specification<Supplement> result = Specification.where(specifications[0]);

        for (int i = 1; i < specifications.length; i++) {
            result = result.or(specifications[i]);
        }

        return result;
    }
}