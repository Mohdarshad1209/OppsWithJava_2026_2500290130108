import java.util.*;
class Student {
    int rollNo;
    String name;
    int marks;
    Student(int rollNo, String name, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }
}
public class Sorting1 {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(103, "Rahul", 85));
        students.add(new Student(101, "Aman", 95));
        students.add(new Student(104, "Ravi", 85));
        students.add(new Student(102, "Karan", 95));
        Collections.sort(students, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                if (s1.marks != s2.marks) {
                    return s2.marks - s1.marks;
                }
                return s1.rollNo - s2.rollNo;
            }
        });
        for (Student s : students) {
            System.out.println(
                s.rollNo + " " + s.name + " " + s.marks
            );
        }
    }
}