import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FilterTextField extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        super.insertString(fb, offset, revise(text), attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        super.replace(fb, offset, length, revise(text), attrs);
    }

    public String revise(String text){
        StringBuilder builder = new StringBuilder(text);
        int index = 0;

        while (index < builder.length()){
            if (accept(builder.charAt(index))){
                System.out.println(builder.length());
                index++;
            } else {
                builder.deleteCharAt(index);
            }
        }

        return builder.toString();
    }

    public boolean accept(final char c){
        return Character.isDigit(c) || c == '.';
    }
}
