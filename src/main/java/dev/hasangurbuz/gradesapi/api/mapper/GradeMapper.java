package dev.hasangurbuz.gradesapi.api.mapper;

import dev.hasangurbuz.gradesapi.model.Grade;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.GradeDTO;
import org.openapitools.model.GradeDetailDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GradeMapper {

    private final StudentMapper studentMapper;

    public GradeDTO toDto(Grade grade){
        GradeDTO dto = new GradeDTO();
        dto.setId(grade.getId());
        dto.setPoint(grade.getPoint().floatValue());
        dto.setStudent(studentMapper.toDto(grade.getStudent()));
        return dto;
    }

    public GradeDTO toDetailDto(Grade grade){
        GradeDTO dto = toDto(grade);
        GradeDetailDTO detailDTO = new GradeDetailDTO();
        detailDTO.setCreatedAt(grade.getCreatedAt());
        dto.setDetail(detailDTO);
        return dto;
    }

    public List<GradeDTO> toDtoList(List<Grade> gradeList){
        List<GradeDTO> dtos = new ArrayList<>(gradeList.size());
        for (Grade grade : gradeList) {
            dtos.add(toDto(grade));
        }
        return dtos;
    }

}
