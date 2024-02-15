package dev.hasangurbuz.gradesapi.repository;

import dev.hasangurbuz.gradesapi.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}