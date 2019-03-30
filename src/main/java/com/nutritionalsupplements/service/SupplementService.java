package com.nutritionalsupplements.service;

import com.nutritionalsupplements.entity.Supplement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplementService {

    Supplement getSupplement(Long id);

    void saveSupplement(Supplement supplement);

    List<Supplement> getSupplements();

    void updateSupplement(Supplement supplement);

    void removeSupplement(Supplement supplement);

}