package com.nutritionalsupplements.service;

import com.nutritionalsupplements.entity.Supplement;

import java.util.List;

public interface SupplementService {

    Supplement getSupplement(Long id);

    void saveSupplement(Supplement supplement);

    List<Supplement> getSupplements();

    void updateSupplement(Supplement supplement);

    void removeSupplement(Supplement supplement);

}
