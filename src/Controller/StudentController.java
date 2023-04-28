/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// similar methods to BookController.java used 
public class StudentController {
    private List<Student> students; // list to store students

    public StudentController() {
        students = new ArrayList<>();
    }

    public void readStudentsFromCSV(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // skip header line
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // split by comma, but ignore commas within double quotes);
                int studentID = Integer.parseInt(fields[0].trim());
                String studentFirstName = fields[1].trim();
                String studentSurname = fields[2].trim();
                String email = fields[3].trim();

                Student student = new Student(studentID, studentFirstName, studentSurname, email);
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("Error reading students from CSV file: " + e.getMessage());
        }
    }

    public void sortStudentsByID() {
        mergeSort(students, Comparator.comparingInt(Student::getStudentID));
    }

    public void sortStudentsByFullName() {
        mergeSort(students, (s1, s2) -> {
            int result = s1.getStudentSurname().compareToIgnoreCase(s2.getStudentSurname());
            if (result == 0) {
                result = s1.getStudentFirstName().compareToIgnoreCase(s2.getStudentFirstName());
            }
            return result;
        });
    }

    public List<Student> searchStudentsByID(int studentID) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                result.add(student);
            }
        }
        return result;
    }

    public List<Student> searchStudentsByFullName(String fullName) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            String fullStudentName = student.getStudentFirstName() + " " + student.getStudentSurname();
            if (fullStudentName.equalsIgnoreCase(fullName)) {
                result.add(student);
            }
        }
        return result;
    }
    
    public Student getStudentByFullName(String fullName) {
        for (Student student : students) {
            if ((student.getStudentFirstName() + " " + student.getStudentSurname()).equalsIgnoreCase(fullName)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getStudents() {
        return students;
    }

    private static <T> void mergeSort(List<T> list, java.util.Comparator<T> comparator) {
        int size = list.size();
        if (size < 2) {
            return;
        }

        int mid = size / 2;
        List<T> leftList = new ArrayList<>(list.subList(0, mid));
        List<T> rightList = new ArrayList<>(list.subList(mid, size));

        mergeSort(leftList, comparator);
        mergeSort(rightList, comparator);

        int i = 0, j = 0, k = 0;
        while (i < leftList.size() && j < rightList.size()) {
            if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0) {
                list.set(k++, leftList.get(i++));
            } else {
                list.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) {
            list.set(k++, leftList.get(i++));
        }

        while (j < rightList.size()) {
            list.set(k++, rightList.get(j++));
        }
    }
}
