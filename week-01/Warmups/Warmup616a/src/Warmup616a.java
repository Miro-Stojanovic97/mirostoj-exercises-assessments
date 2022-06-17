public class Warmup616a {

    public static void main(String[] args) {
        //school performance, and analyzed by gender
        int men = 345; //number of male students
        int women = 356;
        double menGPA = 3.2; //avg gpa per male student
        double womenGPA = 3.4;

        int totalStudents = men + women;
        double netGPA = (menGPA * men + womenGPA * women)/totalStudents;
        System.out.println(netGPA);
        if (netGPA < 3.5){
            System.out.println("The school needs to improve.");
        }
        else {
            System.out.println("The schools doing good.");
        }
    }
}
