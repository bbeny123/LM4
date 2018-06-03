import java.util.Stack;

public class ONP {

    private String result = "";
    private Stack<String> stack = new Stack<>();

    private int getPriority(String s) {
        switch (s) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }

    public String onp(String s) {
        while (!s.isEmpty()) {
            String a = s.substring(0, 1);
            s = s.substring(1, s.length());
            analyze(a);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        result += sb.toString();
        return result;
    }

    private void analyze(String a) {
        if (a.equals("(")) {
            stack.push(a);
            return;
        }

        if (a.equals(")")) {
            StringBuilder sb = new StringBuilder();
            while (!stack.peek().equals("(")) {
                sb.append(stack.pop());
            }
            stack.pop();
            result += sb.toString();
            return;
        }

        int newOp = getPriority(a);
        if (newOp == 0) {
            result += a;
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty() && newOp <= getPriority(stack.peek())) {
            sb.append(stack.pop());
        }
        stack.push(a);
        result += sb.toString();
    }

}
