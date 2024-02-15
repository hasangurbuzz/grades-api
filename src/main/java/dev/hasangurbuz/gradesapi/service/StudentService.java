package dev.hasangurbuz.gradesapi.service;

import dev.hasangurbuz.gradesapi.model.Student;

public interface StudentService {
    Student create(String firstName, String lastName);

    Student findByName(String firstName, String lastName);
}
