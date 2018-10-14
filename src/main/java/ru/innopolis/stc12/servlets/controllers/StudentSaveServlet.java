package ru.innopolis.stc12.servlets.controllers;

import Services.CityService;
import Services.CityServiceImpl;
import Services.StudentService;
import Services.StudentServiceImpl;
import pojo.City;
import pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentSaveServlet extends HttpServlet {
    CityService cityService = new CityServiceImpl();
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String family = req.getParameter("family");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String cityName = req.getParameter("city");
        String contact = req.getParameter("contact");


        City city = cityService.getCityByName(cityName);

        if (city == null) {
            city = new City(cityName, 10000);
            cityService.addCity(city);
        }
        Student student = new Student(name, family, age, contact, city);
        System.out.println(studentService.addStudent(student));

    }
}
