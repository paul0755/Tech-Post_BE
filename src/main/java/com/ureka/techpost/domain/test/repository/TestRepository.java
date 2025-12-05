package com.ureka.techpost.domain.test.repository;

import com.ureka.techpost.domain.test.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
