package dev.hasangurbuz.gradesapi.api.controller;

import dev.hasangurbuz.gradesapi.api.exception.NotFoundException;
import dev.hasangurbuz.gradesapi.api.mapper.GradeMapper;
import dev.hasangurbuz.gradesapi.model.Grade;
import dev.hasangurbuz.gradesapi.model.Student;
import dev.hasangurbuz.gradesapi.service.GradeService;
import dev.hasangurbuz.gradesapi.service.PagedResult;
import dev.hasangurbuz.gradesapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.GradeApi;
import org.openapitools.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class GradeApiController implements GradeApi {

    private final StudentService studentService;
    private final GradeService gradeService;
    private final Logger logger = LoggerFactory.getLogger(GradeApiController.class);
    private final GradeMapper gradeMapper;

    @Override
    @Transactional
    public ResponseEntity<GradeDTO> create(GradeCreateRequestDTO gradeCreateRequestDTO) {
        Float pointDTO = gradeCreateRequestDTO.getPoint();
        BigDecimal point = BigDecimal.valueOf(Math.floor(pointDTO * 10) / 10f);
        StudentDTO studentDTO = gradeCreateRequestDTO.getStudent();
        Student foundStudent = studentService.findByName(studentDTO.getFirstname(), studentDTO.getLastname());
        if (foundStudent == null) {
            foundStudent = studentService.create(studentDTO.getFirstname(), studentDTO.getLastname());
        }
        Grade grade = gradeService.create(foundStudent, point);
        GradeDTO dto = gradeMapper.toDto(grade);
        return ResponseEntity.ok(dto);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> delete(Long id) {
        gradeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<GradeDTO> get(Long id) {
        Grade grade = gradeService.findById(id);
        if (grade == null) {
            throw new NotFoundException("Not found with id : " + id);
        }
        GradeDTO dto = gradeMapper.toDetailDto(grade);
        return ResponseEntity.ok(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<GradeSearchResponseDTO> search(GradeSearchRequestDTO gradeSearchRequestDTO) {
        PageRequestDTO pageRequest = gradeSearchRequestDTO.getPageRequest();
        SortOrderDTO orderDTO = pageRequest.getOrder();
        if (orderDTO != null) {
            if (orderDTO.getDirection() == null) {
                orderDTO.setDirection(DirectionTypeDTO.ASC);
            }
            if (orderDTO.getSort() == null) {
                orderDTO.setSort(SortTypeDTO.STUDENT_LASTNAME);
            }
        }

        String firstName = gradeSearchRequestDTO.getFirstname();
        String lastName = gradeSearchRequestDTO.getLastname();

        PagedResult<Grade> searchResult = gradeService.search(firstName, lastName, pageRequest);

        GradeSearchResponseDTO response = new GradeSearchResponseDTO();
        response.setTotal(searchResult.getTotal());
        List<GradeDTO> items = gradeMapper.toDtoList(searchResult.getItems());
        response.setItems(items);

        return ResponseEntity.ok(response);
    }
}
