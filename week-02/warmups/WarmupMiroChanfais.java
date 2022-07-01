public class WarmupMiroChanfais {
    public static void main(String[] args) {
    String rna = "AACGGGCUACG";
    String other = rnaEncoding(rna);
        System.out.println(other);

    }
    public static String rnaEncoding(String rna) {
        String temp = "";
        for (int i = 0; i < rna.length(); i++) {
            switch (rna.charAt(i)) {
                case 'A':
                    temp += "U";
                    break;
                case 'U':
                    temp += "A";
                    break;
                case 'C':
                    temp += "G";
                    break;
                case 'G':
                    temp += "C";
                    break;
                default:
                    System.out.println("You inputted something besides U, A, C, or G");
            }
        }
        return temp;
    }
}
