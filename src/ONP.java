class ONP {

    private String result = "";
    private Stack stack = new Stack();

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

    String onp(String s) {
        int q = -1;
        while (!s.isEmpty()) {
            String a = s.substring(0, 1);
            s = s.substring(1, s.length());
            q = analyze(a, q);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        result += sb.toString();
        return result;
    }

    private int analyze(String a, int mode) {
        if ((mode == -1 || mode == 2 || mode == 3) && a.equals("-")) {
            result += "0";
        }
        if ((mode == 1 || mode == 3) && a.equals("(")) {
            analyze("*");
        }
        return analyze(a);
    }

    private int analyze(String a) {
        if (a.equals("(")) {
            stack.push(a);
            return 2;
        }

        if (a.equals(")")) {
            StringBuilder sb = new StringBuilder();
            while (!stack.peek().equals("(")) {
                sb.append(stack.pop());
            }
            stack.pop();
            result += sb.toString();
            return 3;
        }

        int newOp = getPriority(a);
        if (newOp == 0) {
            result += a;
            return 1;
        }

        int mode = 0;
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty() && newOp <= getPriority(stack.peek())) {
            sb.append(stack.pop());
            mode = 2;
        }
        stack.push(a);
        result += sb.toString();
        return mode;
    }

}
