package com.additives.service;

import com.additives.entity.Additive;
import com.additives.repository.AdditiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdditiveService {

    @Autowired
    private AdditiveRepository additiveRepository;

    public Additive getAdditive(Long id) {
        return additiveRepository.getOne(id);
    }

    public void saveAdditive(Additive additive) {
        additiveRepository.save(additive);
    }

    public List<Additive> getAdditives() {
        List<Additive> additives = additiveRepository.findAll();
        return additiveRepository.findAll();
    }

    public void updateAdditive(Additive additive) {
        final Additive additiveForUpdate = this.additiveRepository.findById(additive.getId())
                .orElseThrow(EntityNotFoundException::new);

        additiveRepository.save(additive);
    }

    public void removeAdditive(Additive additive) {
        additiveRepository.delete(additive);
    }

    public List<Additive> query(Specification<Additive> specification, Sort sort) {
        return additiveRepository.findAll(specification, sort);
    }

    public List<Additive> query(Specification<Additive> specification) {
        return additiveRepository.findAll(specification);
    }

    public List<String> getNames(Additive additive) {
        String[] names = additive.getName().split("#");

        List<String> result = new ArrayList<>();

        for(String name: names) {
            String cleanName = name.trim();

            if (!cleanName.isEmpty()) {
                result.add(cleanName);
            }
        }

        return result;
    }

    public String getFirstNameOrDefault(Additive additive, String defaultValue) {
        List<String> names = getNames(additive);

        return names.size() == 0 ? defaultValue : names.get(0);
    }

    public void saveAll(List<Additive> additives) {
        additiveRepository.saveAll(additives);
    }
}