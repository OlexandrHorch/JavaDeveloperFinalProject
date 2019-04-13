package com.additives.service;

import com.additives.entity.Additive;
import com.additives.entity.AdditiveCategory;
import com.additives.entity.AdditiveDanger;
import com.additives.entity.AdditiveOrigin;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class AdditiveSpecifications {

    public static Specification<Additive> withUsingInfoLike(String usingInfo) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("usingInfo")), "%" + usingInfo.toLowerCase() + "%");
    }

    public static Specification<Additive> withECodeLike(String eCode) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("eCod")), "%" + eCode.toLowerCase() + "%");
    }

    public static Specification<Additive> withNameContaining(String codeName) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("name")), "%" + codeName.toLowerCase() + "%");
    }

    public static Specification<Additive> withOtherNamesLike(String otherNames) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("otherNames")), "%" + otherNames.toLowerCase() + "%");
    }

    public static Specification<Additive> withCategory(AdditiveCategory category) {
        return (root, cq, cb) -> cb.equal(root.get("category"), category);
    }

    public static Specification<Additive> withDanger(AdditiveDanger danger) {
        return (root, cq, cb) -> cb.equal(root.get("danger"), danger);
    }

    public static Specification<Additive> withOrigin(AdditiveOrigin origin) {
        return (root, cq, cb) -> cb.equal(root.get("origin"), origin);
    }

    public static Specification and(List<Specification<Additive>> specifications) {
        return and(specifications.toArray(new Specification[specifications.size()]));
    }

    public static Specification<Additive> and(Specification<Additive>... specifications) {
        Specification<Additive> result = Specification.where(specifications[0]);

        for (int i = 1; i < specifications.length; i++) {
            result = result.and(specifications[i]);
        }

        return result;
    }

    public static Specification<Additive> or(Specification<Additive>... specifications) {
        Specification<Additive> result = Specification.where(specifications[0]);

        for (int i = 1; i < specifications.length; i++) {
            result = result.or(specifications[i]);
        }

        return result;
    }
}