package dev.hasangurbuz.gradesapi.service;

import dev.hasangurbuz.gradesapi.model.Grade;
import dev.hasangurbuz.gradesapi.model.Student;
import org.openapitools.model.PageRequestDTO;

import java.math.BigDecimal;

public interface GradeService {
    Grade create(Student student, BigDecimal point);

    void delete(Long id);

    Grade findById(Long id);

    PagedResult<Grade> search(String firstName, String lastName, PageRequestDTO pageRequestDTO);

}
