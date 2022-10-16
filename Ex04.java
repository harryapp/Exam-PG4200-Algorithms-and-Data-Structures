package ex04;

import java.util.List;
import java.util.Map;


public class Ex04 {

    public static class Student {
        public int student_id;
        public String name;
        public Map<String, Double> examPoints;

        public Student(int student_id, String name, Map<String, Double> examPoints) {
            this.student_id = student_id;
            this.name = name;
            this.examPoints = examPoints;
        }
    }

    public class Course {
        String course_code;
        String topics;
        String evaluation;
        Map<Student, Integer> points;
    }

    // A.
    // I assume that it is the average examPoints im suppose to calculate, not the average grade, as there has not been provided a grade table

    public static double computeAverageGrade(List<Student> students, String courseId) {
        return students.stream()
                .map(student -> student.examPoints)
                .filter(examPointsMap -> examPointsMap.containsKey(courseId))
                .map(stringDoubleMap -> stringDoubleMap.get(courseId))
                .mapToDouble(i -> i)
                .average().getAsDouble();
    }


    // B.

    public List<Map<Student, Integer>> StudentRanking(List<Course> courses){
       return courses.stream()
                .filter(course -> course.evaluation.contains("project") || course.evaluation.contains("exam"))
                .filter(course -> course.topics.contains("programming"))
                .map(course -> course.points)
                .toList();
    }



}
