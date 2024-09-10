import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FilterTextField extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = currentText.substring(0, offset) + text + currentText.substring(offset);

        if (isValid(newText)) {
            super.insertString(fb, offset, text, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

        if (isValid(newText)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = currentText.substring(0, offset) + currentText.substring(offset + length);

        if (isValid(newText)) {
            super.remove(fb, offset, length);
        }
    }

    public String revise(String text){
        StringBuilder builder = new StringBuilder(text);
        int index = 0;
        boolean hasDot = false;
        boolean hasNumber = false;

        while (index < builder.length()){
            if (accept(builder.charAt(index), hasDot, hasNumber)){
                if (Character.isDigit(builder.charAt(index))){
                    hasNumber = true;
                }

                if (builder.charAt(index) == '.'){
                    hasDot = true;
                }

                index++;
            } else {
                builder.deleteCharAt(index);
            }
        }

        return builder.toString();
    }

    public boolean accept(final char c, boolean hasDot, boolean hasNumber){
        if (Character.isDigit(c)){
            return true;
        }

        return c == '.' && !hasDot && hasNumber;
    }

    private boolean isValid(String text) {
        return revise(text).equals(text);  // Usa a lógica do revise() para validar a entrada
    }
}
