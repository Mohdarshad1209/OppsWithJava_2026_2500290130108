import java.util.*;

class Student implements Comparable<Student> {
    String name;
    int rollno;
    int marks;

    Student(String n, int r, int m) {
        name = n;
        rollno = r;
        marks = m;
    }

    @Override
    public int compareTo(Student o) {
        return this.rollno - o.rollno;
    }
    @Override
    public String toString() {
        return rollno + " " + name + " " + marks;
    }
}
public class Sortingdemo {
    public static void main(String[] args) {

        ArrayList<Integer> i = new ArrayList<>();

        i.add(23);
        i.add(12);
        i.add(14);
        i.add(15);
        i.add(16);

        // Ascending order
        i.sort(null);
        System.out.println(i);

        // Descending order
        i.sort(Collections.reverseOrder());
        System.out.println(i);


        ArrayList<Student> st = new ArrayList<>();

        st.add(new Student("Rahul", 1, 100));
        st.add(new Student("Nitesh", 10, 20));
        st.add(new Student("Neetesh", 7, 80));
        st.add(new Student("Nilesh", 9, 70));
        st.add(new Student("Milesh", 20, 80));

        // Sort according to compareTo()
        st.sort(null);

        System.out.println(st);
    }
}