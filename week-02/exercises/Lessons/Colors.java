 public class Colors {

        public static void main(String[] args) {
            // success is an instance of Color
            Color success = new Color("green");

            // failure is an instance of Color
            Color failure = new Color("blue");

            // warning is an instance of Color
            Color warning = new Color("yellow");

            // each color has its own state
            // the dot operator, `.`, looks "inside" the object
            System.out.println("Success color is: " + success.name); // Success color is: green
            System.out.println("Failure color is: " + failure.name); // Failure color is: blue
            System.out.println("Warning color is: " + warning.name); // Warning color is: yellow

            // the failure color doesn't seem right
            // change it to red
            failure.name = "red";
            System.out.println("Failure color is: " + failure.name);  // Failure color is: red
        }
    }

    // The template, blueprint, or recipe for a Color.
// It isn't a color, but it contains the rules which define
// what it means to be a color.
    class Color {

        public String name;

        public Color(String name) {
            this.name = name;
        }
    }
