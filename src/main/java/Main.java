import Services.CityService;
import Services.CityServiceImpl;
import Services.StudentService;
import Services.StudentServiceImpl;
import pojo.City;
import pojo.Student;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentServiceImpl();
        CityService cityService = new CityServiceImpl();

        City city = new City("Ryazan", 10000);
        cityService.addCity(city);

        Student student = new Student("vasa", "anatolyev", 12, "123234", cityService.getCityByName(city.getName()));
        studentService.addStudent(student);

    }

}
