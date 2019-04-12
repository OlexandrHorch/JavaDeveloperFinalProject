package com.nutritionalsupplements.service;

import com.nutritionalsupplements.entity.Supplement;
import com.nutritionalsupplements.repository.SupplementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupplementService {

    @Autowired
    private SupplementRepository supplementRepository;

    public Supplement getSupplement(Long id) {
        return supplementRepository.getOne(id);
    }

    public void saveSupplement(Supplement supplement) {
        supplementRepository.save(supplement);
    }

    public List<Supplement> getSupplements() {
        List<Supplement> supplements = supplementRepository.findAll();
        return supplementRepository.findAll();
    }

    public void updateSupplement(Supplement supplement) {
        final Supplement supplementForUpdate = this.supplementRepository.findById(supplement.getId())
                .orElseThrow(EntityNotFoundException::new);

        supplementRepository.save(supplement);
    }

    public void removeSupplement(Supplement supplement) {
        supplementRepository.delete(supplement);
    }

    public List<Supplement> query(Specification<Supplement> specification, Sort sort) {
        return supplementRepository.findAll(specification, sort);
    }

    public List<Supplement> query(Specification<Supplement> specification) {
        return supplementRepository.findAll(specification);
    }

    public List<String> getNames(Supplement supplement) {
        String[] names = supplement.getName().split("#");

        List<String> result = new ArrayList<>();

        for(String name: names) {
            String cleanName = name.trim();

            if (!cleanName.isEmpty()) {
                result.add(cleanName);
            }
        }

        return result;
    }

    public String getFirstNameOrDefault(Supplement supplement, String defaultValue) {
        List<String> names = getNames(supplement);

        return names.size() == 0 ? defaultValue : names.get(0);
    }

    public void saveAll(List<Supplement> supplements) {
        supplementRepository.saveAll(supplements);
    }
}