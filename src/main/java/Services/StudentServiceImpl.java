package Services;

import dao.StudentDao;
import dao.StudentDaoImpl;
import pojo.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public StudentServiceImpl() {
        studentDao = new StudentDaoImpl();
    }

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public boolean addStudent(Student student) {
        studentDao.addStudent(student);
        return true;
    }
}
