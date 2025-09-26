//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
    }

    public String longestCommonPrefix(String[] strs) {
        StringBuffer stringBuffer = new StringBuffer();

        outer: for(int i=0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);

            for(int j=1; j < strs.length; j++) {
                if(i >= strs[j].length()
                        || c != strs[j].charAt(i)) {
                    break outer;
                }
            }

            stringBuffer.append(c);
        }

        return stringBuffer.toString();
    }
}