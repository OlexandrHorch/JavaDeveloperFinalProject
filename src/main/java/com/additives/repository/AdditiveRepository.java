package com.additives.repository;

import com.additives.entity.Additive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditiveRepository extends JpaRepository<Additive, Long>, JpaSpecificationExecutor<Additive> {

}