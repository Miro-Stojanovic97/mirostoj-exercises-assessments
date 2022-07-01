public class warmupRNA {
        public static void main(String[] args) {
            String rna1 = "AACGGGCUACG";
            String rna2 = "AACCCGCUACG";
            String other = rnaEncoding(rna1);
            System.out.println(other);
            System.out.println(rnaIsEqual(rna1,other));
        }
        public static String rnaEncoding(String rna1) {
            String temp = "";
            for (int i = 0; i < rna1.length(); i++) {
                switch (rna1.charAt(i)) {
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
        public static boolean rnaIsEqual(String rna1, String rna2){
            if(rna1.length() != rna2.length()){
                return false;
            }
            for(int i = 0; i < rna1.length(); i++){
                switch (rna1.charAt(i)) {
                    case 'A':
                        if(rna2.charAt(i) != 'U') {
                            return false;
                        }
                        break;
                    case 'U':
                        if(rna2.charAt(i) != 'A') {
                            return false;
                        }
                        break;
                    case 'C':
                        if(rna2.charAt(i) != 'G') {
                            return false;
                        }
                        break;
                    case 'G':
                        if(rna2.charAt(i) != 'C') {
                            return false;
                        }
                        break;
                    default:
                        System.out.println("You inputted something besides U, A, C, or G");
                }
            }

         return true;
        }
    }
