package tn.esprit.studentmanagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.services.IStudentService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student createTestStudent(Long id, String firstName, String lastName, String email) {
        return new Student(id, firstName, lastName, email, "12345",
                LocalDate.of(2000, 1, 1), "Address", null, null);
    }

    @Test
    void testGetAllStudents() throws Exception {
        List<Student> studentList = Arrays.asList(
                createTestStudent(1L, "John", "Doe", "john@mail.com")
        );

        when(studentService.getAllStudents()).thenReturn(studentList);

        mockMvc.perform(get("/students/getAllStudents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testGetStudentById() throws Exception {
        Long studentId = 1L;
        Student student = createTestStudent(studentId, "John", "Doe", "john@mail.com");

        when(studentService.getStudentById(studentId)).thenReturn(student);

        mockMvc.perform(get("/students/getStudent/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").value(studentId))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testCreateStudent() throws Exception {
        Student studentToSave = createTestStudent(null, "Jane", "Smith", "jane@mail.com");
        Student savedStudent = createTestStudent(2L, "Jane", "Smith", "jane@mail.com");

        when(studentService.saveStudent(any(Student.class))).thenReturn(savedStudent);

        mockMvc.perform(post("/students/createStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentToSave)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").value(2L))
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        Long studentId = 1L;

        doNothing().when(studentService).deleteStudent(studentId);

        mockMvc.perform(delete("/students/deleteStudent/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}