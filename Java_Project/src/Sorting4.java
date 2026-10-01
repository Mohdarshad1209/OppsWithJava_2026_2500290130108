import java.util.*;
class Movie {
    int id;
    String name;
    double rating;
    Movie(int id, String name, double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }
}

public class Sorting4 {
    public static void main(String[] args) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(104, "Inception", 9.0));
        movies.add(new Movie(102, "Interstellar", 9.0));
        movies.add(new Movie(101, "Avatar", 8.5));
        movies.add(new Movie(103, "Titanic", 8.0));
        Collections.sort(movies, new Comparator<Movie>() {
            public int compare(Movie m1, Movie m2) {
                if (m1.rating != m2.rating) {
                    return Double.compare(m2.rating, m1.rating);
                }
                return m1.name.compareTo(m2.name);
            }
        });
        for (Movie m : movies) {
            System.out.println(m.id + " " + m.name + " " + m.rating);
        }
    }
}