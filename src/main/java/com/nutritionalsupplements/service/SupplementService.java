package com.nutritionalsupplements.service;

import com.nutritionalsupplements.repository.SupplementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplementService {
    @Autowired
    private SupplementRepository supplementRepository;

}