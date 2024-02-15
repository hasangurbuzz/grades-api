package dev.hasangurbuz.gradesapi.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.hasangurbuz.gradesapi.model.Grade;
import dev.hasangurbuz.gradesapi.model.QGrade;
import dev.hasangurbuz.gradesapi.model.Student;
import dev.hasangurbuz.gradesapi.repository.GradeRepository;
import dev.hasangurbuz.gradesapi.service.GradeService;
import dev.hasangurbuz.gradesapi.service.PagedResult;
import dev.hasangurbuz.gradesapi.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.openapitools.model.DirectionTypeDTO;
import org.openapitools.model.PageRequestDTO;
import org.openapitools.model.SortOrderDTO;
import org.openapitools.model.SortTypeDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final JPAQueryFactory queryFactory;
    private final QGrade qGrade = QGrade.grade;

    @Override
    public Grade create(Student student, BigDecimal point) {
        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setPoint(point);
        grade.setCreatedAt(OffsetDateTime.now());
        return gradeRepository.save(grade);
    }

    @Override
    public void delete(Long id) {
        gradeRepository.deleteById(id);
    }

    @Override
    public Grade findById(Long id) {
        Optional<Grade> result = gradeRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        return null;
    }

    @Override
    public PagedResult<Grade> search(String firstName, String lastName, PageRequestDTO pageRequestDTO) {
        JPAQuery<Grade> query = queryFactory.selectFrom(qGrade);
        if (!StringUtils.isBlank(firstName)){
            query = query.where(
                    qGrade.student.firstName.containsIgnoreCase(firstName.trim())
            );
        }
        if (!StringUtils.isBlank(lastName)){
            query = query.where(
                    qGrade.student.lastName.containsIgnoreCase(lastName.trim())
            );
        }

        SortOrderDTO order = pageRequestDTO.getOrder();

        if (order != null){
            SortTypeDTO sort = order.getSort();
            DirectionTypeDTO direction = order.getDirection();
            if (sort == null){
                sort = SortTypeDTO.STUDENT_FIRSTNAME;
            }
            if (direction == null){
                direction = DirectionTypeDTO.ASC;
            }
            query = query.orderBy(getOrder(order));
        }

        QueryResults<Grade> results = query.offset(pageRequestDTO.getStart())
                .limit(pageRequestDTO.getLimit())
                .fetchResults();

        PagedResult<Grade> gradePagedResult = new PagedResult<>();
        gradePagedResult.setItems(results.getResults());
        gradePagedResult.setTotal(results.getTotal());

        return gradePagedResult;
    }

    private OrderSpecifier getOrder(SortOrderDTO orderDTO) {
        SortTypeDTO sort = orderDTO.getSort();
        DirectionTypeDTO direction = orderDTO.getDirection();

        if (sort.equals(SortTypeDTO.STUDENT_FIRSTNAME)) {
            if (direction.equals(DirectionTypeDTO.ASC)){
                return qGrade.student.firstName.asc();
            }
            return qGrade.student.firstName.desc();
        }

        if (sort.equals(SortTypeDTO.STUDENT_LASTNAME)) {
            if (direction.equals(DirectionTypeDTO.ASC)){
                return qGrade.student.lastName.asc();
            }
            return qGrade.student.lastName.desc();
        }

        if (sort.equals(SortTypeDTO.POINT)) {
            if (direction.equals(DirectionTypeDTO.ASC)){
                return qGrade.point.asc();
            }
            return qGrade.point.desc();
        }

        if (sort.equals(SortTypeDTO.CREATED_AT)){
            if (direction.equals(DirectionTypeDTO.ASC)){
                return qGrade.createdAt.asc();
            }
        }
        return qGrade.createdAt.desc();
    }
}
