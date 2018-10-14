package Services;

import pojo.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    public boolean addStudent(Student student);
}
