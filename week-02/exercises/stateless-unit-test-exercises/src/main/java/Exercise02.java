public class Exercise02 {

    // [DONE] 1. Read the surroundWithTag JavaDocs.
    // [DONE] 2. Complete the surroundWithTag method. You're only allowed to confirm it's working by running
    // the accompanying test in Exercise02Test.
    // 3. The test is incomplete. It doesn't account for all scenarios. Complete the test to insure
    // surroundWithTag is 100% correct.

    /**
     * Given two Strings: some text and a tag name, return a String that embeds the text in a pseudo-HTML tag.
     * Examples:
     * "abc", "boom" -> "<boom>abc</boom"
     * "Cats are mean.", "fact" -> "<fact>Cats are mean.</fact>"
     * "this is just text", "" -> "this is just text"
     * null, "span" -> "<span></span>"
     * "splendid", null -> splendid
     *
     * @param text    string value to be surrounded by an HTML tag
     * @param tagName the HTML tag name
     * @return string in the form: <tagName>text</tagName>
     */
    static String surroundWithTag(String text, String tagName) {

        String text1 = "";
        String tag1 = "";
        String tag2 = "";

        if (text == null) {
                text1 = "";
            } else {
                 text1 = text;
                }
            if (tagName == null) {
                tag1 = "";
                tag2 = "";
            } else {
                tag1 = "<"+tagName+">";
                tag2 = "</"+tagName+">";
             }
        String result = tag1+text1+tag2;
        return result;
    }
}
