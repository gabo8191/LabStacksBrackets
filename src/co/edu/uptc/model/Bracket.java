package co.edu.uptc.model;

import java.util.Stack;

public class Bracket {

  private Stack<Character> curlyBrackets;
  private Stack<Character> parentheses;
  private Stack<Character> brackets;

  public Bracket() {
    curlyBrackets = new Stack<>();
    parentheses = new Stack<>();
    brackets = new Stack<>();
  }

  public void fillStacks(String cod) {
    for (int i = 0; i < cod.length(); i++) {
      char currentChar = cod.charAt(i);
      if (currentChar == '{') {
        curlyBrackets.push(currentChar);
      } else if (currentChar == '(') {
        parentheses.push(currentChar);
      } else if (currentChar == '[') {
        brackets.push(currentChar);
      }
    }
  }

  public String checkBalance(String cod) {
    StringBuilder check = new StringBuilder();
    for (int i = 0; i < cod.length(); i++) {
      char currentChar = cod.charAt(i);
      if (currentChar == '}') {
        check = checkBrackets(check, curlyBrackets, "{");
      } else if (currentChar == ')') {
        check = checkBrackets(check, parentheses, "(");
      } else if (currentChar == ']') {
        check = checkBrackets(check, brackets, "[");
      }
    }

    check = checkRemainingBrackets(check);

    return check.toString();
  }

  private StringBuilder checkBrackets(StringBuilder check, Stack<Character> stack, String bracketType) {
    if (stack.isEmpty()) {
      check.append("Le falta uno o mas: ").append(bracketType).append("\n");
    } else {
      stack.pop();
    }
    return check;
  }

  private StringBuilder checkRemainingBrackets(StringBuilder check) {
    if (!curlyBrackets.isEmpty() || !parentheses.isEmpty() || !brackets.isEmpty()) {
      if (!curlyBrackets.isEmpty()) {
        check.append("Le falta uno o mas: }\n");
      }
      if (!parentheses.isEmpty()) {
        check.append("Le falta uno o mas: )\n");
      }
      if (!brackets.isEmpty()) {
        check.append("Le falta uno o mas: ]\n");
      }
    } else if (check.length() == 0) {
      check.append("La sintaxis es correcta");
    }
    return check;
  }
}
