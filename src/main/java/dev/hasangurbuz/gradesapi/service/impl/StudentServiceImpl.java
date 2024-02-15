package dev.hasangurbuz.gradesapi.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.hasangurbuz.gradesapi.model.QStudent;
import dev.hasangurbuz.gradesapi.model.Student;
import dev.hasangurbuz.gradesapi.repository.StudentRepository;
import dev.hasangurbuz.gradesapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final QStudent qStudent = QStudent.student;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Student create(String firstName, String lastName) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student = studentRepository.save(student);
        return student;
    }

    @Override
    public Student findByName(String firstName, String lastName) {
        Student student = jpaQueryFactory.selectFrom(qStudent)
                .where(qStudent.firstName.equalsIgnoreCase(firstName)
                        .and(qStudent.lastName.equalsIgnoreCase(lastName)))
                .fetchOne();

        return student;
    }


}
