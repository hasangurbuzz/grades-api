package dev.hasangurbuz.gradesapi.api.mapper;

import dev.hasangurbuz.gradesapi.model.Student;
import org.openapitools.model.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDTO toDto(Student student){
        StudentDTO dto = new StudentDTO();
        dto.setFirstname(student.getFirstName());
        dto.setLastname(student.getLastName());
        return dto;
    }

}
