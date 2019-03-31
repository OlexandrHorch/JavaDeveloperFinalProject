package com.nutritionalsupplements.service;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.repository.SupplementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SupplementServiceImpl implements SupplementService {

    @Autowired
    private SupplementRepository supplementRepository;

    @Override
    public Supplement getSupplement(Long id) {
        return supplementRepository.getOne(id);
    }

    @Override
    public void saveSupplement(Supplement supplement) {
        supplementRepository.save(supplement);
    }

    @Override
    public List<Supplement> getSupplements() {
        return supplementRepository.findAll();
    }

    @Override
    public void updateSupplement(Supplement supplement) {
        final Supplement supplementForUpdate = this.supplementRepository.findById(supplement.getId())
                .orElseThrow(EntityNotFoundException::new);

        supplementRepository.save(supplementForUpdate);
    }

    @Override
    public void removeSupplement(Supplement supplement) {
        supplementRepository.delete(supplement);
    }


}