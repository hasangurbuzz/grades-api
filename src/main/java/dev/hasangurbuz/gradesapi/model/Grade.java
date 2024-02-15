package dev.hasangurbuz.gradesapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "grade")
@Getter
@Setter
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_seq")
    @SequenceGenerator(name = "grade_seq", sequenceName = "grade_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "point", nullable = false, scale = 1, precision = 4)
    private BigDecimal point;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
