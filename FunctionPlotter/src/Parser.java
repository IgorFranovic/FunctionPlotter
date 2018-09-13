import java.util.LinkedList;
import java.util.Stack;

public class Parser {

	private String function;
	
	public Parser(String function) {
		this.function = "";
		for(int i = 0; i < function.length(); i++) {
			if(function.charAt(i) == ' ') {
				continue;
			}
			else {
				if(i > 0 && function.charAt(i) == '-' && function.charAt(i-1) == '(') {
					this.function += "neg";
				}
				else {
					this.function += function.charAt(i);
				}
			}
		}
		if(this.function.charAt(0) == '-') {
			this.function = "neg" + this.function.substring(1);
		}
		//System.out.println(this.function);
	}
	
	private LinkedList<String> tokenize(double x) throws Exception {
		LinkedList<String> list = new LinkedList<String>();
		String temp = this.function;
		String arg;
		if(x < 0) {
			arg = "neg" + Double.toString(-x);
		}
		else {
			arg = Double.toString(x);
		}
		for(int i = 0; i < temp.length(); i++) {
			if(temp.charAt(i) == 'x') {
				temp = temp.substring(0, i) + arg + temp.substring(i+1);
			}
		}
		while(temp.length() > 0) {
			if(Character.isDigit(temp.charAt(0))) {
				int i = 0;
				while(i < temp.length() && (Character.isDigit(temp.charAt(i)) || temp.charAt(i) == '.')) {
					i++;
				}
				list.add(temp.substring(0, i));
				temp = temp.substring(i);
			}
			else if(temp.matches("^[\\Q+-*/^()\\E].*$")) {
				list.add(temp.substring(0, 1));
				temp = temp.substring(1);
			}
			else if(temp.matches("^(sinh|cosh|tanh|coth).*$")) {
				list.add(temp.substring(0, 4));
				temp = temp.substring(4);
			}
			else if(temp.matches("^(asinh|acosh|atanh|acoth).*$")) {
				list.add(temp.substring(0, 5));
				temp = temp.substring(5);
			}
			else if(temp.matches("^(neg|abs|sgn|exp|log|sin|cos|tan|cot).*$")) {
				list.add(temp.substring(0, 3));
				temp = temp.substring(3);
			}
			else if(temp.matches("^(asin|acos|atan|acot).*$")) {
				list.add(temp.substring(0, 4));
				temp = temp.substring(4);
			}
			else {
				throw new Exception();
			}
		}
		/*for(String token: list) {
			System.out.print(token + ", ");
		}
		System.out.println();*/
		return list;
	}
	
	private int precedence(String operator) {
		if(operator.equals("(")) {
			return 0;
		}
		else if(operator.equals("+") || operator.equals("-")) {
			return 1;
		}
		else if(operator.equals("*") || operator.equals("/")) {
			return 2;
		}
		else if(operator.equals("^")) {
			return 3;
		}
		else {
			return 4;
		}
	}
	
	private boolean isLeftAssociative(String operator) {
		return operator.matches("^[\\Q+-*/\\E]$");
	}
	
	private LinkedList<String> infixToPostfix(double x) throws Exception {
		LinkedList<String> infix = tokenize(x);
		LinkedList<String> postfix = new LinkedList<String>();
		Stack<String> stack = new Stack<String>();
		for(int i = 0; i < infix.size(); i++) {
			String temp = infix.get(i);
			if(Character.isDigit(temp.charAt(0))) {
				postfix.add(temp);
			}
			else if(temp.equals("(")) {
				stack.push(temp);
			}
			else if(temp.equals(")")) {
				while(!stack.peek().equals("(")) {
					postfix.add(stack.pop());
				}
				stack.pop();
			}
			else {
				if(temp.length() == 1) {
					while(!stack.isEmpty()) {
						String top = stack.peek();
						if(precedence(top) > precedence(temp) || (precedence(top) == precedence(temp) && isLeftAssociative(top))) {
							postfix.add(stack.pop());
						}
						else {
							break;
						}
					}
				}
				stack.push(temp);
			}
		}
		while(!stack.isEmpty()) {
			postfix.add(stack.pop());
		}
		/*for(String token: postfix) {
			System.out.print(token + ", ");
		}
		System.out.println();*/
		return postfix;
	}
	
	public double eval(double x) throws Exception {
		LinkedList<String> postfix = infixToPostfix(x);
		Stack<Double> stack = new Stack<Double>();
		for(int i = 0; i < postfix.size(); i++) {
			String temp = postfix.get(i);
			if(Character.isDigit(temp.charAt(0))) {
				stack.push(Double.parseDouble(temp));
			}
			else {
				double operand1, operand2;
				switch(temp) {
				case "+":
					operand1 = stack.pop();
					operand2 = stack.pop();
					stack.push(operand1 + operand2);
					break;
				case "-":
					operand1 = stack.pop();
					operand2 = stack.pop();
					stack.push(operand2 - operand1);
					break;
				case "*":
					operand1 = stack.pop();
					operand2 = stack.pop();
					stack.push(operand1 * operand2);
					break;
				case "/":
					operand1 = stack.pop();
					operand2 = stack.pop();
					stack.push(operand2 / operand1);
					break;
				case "^":
					operand1 = stack.pop();
					operand2 = stack.pop();
					stack.push(Math.pow(operand2, operand1));
					break;
				// solving negative inverses
				case "neg":
					operand1 = stack.pop();
					stack.push(-operand1);
					break;
				case "abs":
					operand1 = stack.pop();
					stack.push(Math.abs(operand1));
					break;
				case "sgn":
					operand1 = stack.pop();
					stack.push(Math.signum(operand1));
					break;
				case "exp":
					operand1 = stack.pop();
					stack.push(Math.exp(operand1));
					break;
				case "log":
					operand1 = stack.pop();
					stack.push(Math.log(operand1));
					break;
				case "sinh":
					operand1 = stack.pop();
					stack.push(Math.sinh(operand1));
					break;
				case "cosh":
					operand1 = stack.pop();
					stack.push(Math.cosh(operand1));
					break;
				case "tanh":
					operand1 = stack.pop();
					stack.push(Math.tanh(operand1));
					break;
				case "coth":
					operand1 = stack.pop();
					stack.push(1/Math.tanh(operand1));
					break;
				case "asinh":
					operand1 = stack.pop();
					stack.push(Math.log(operand1 + Math.sqrt(operand1*operand1 + 1)));
					break;
				case "acosh":
					operand1 = stack.pop();
					stack.push(Math.log(operand1 + Math.sqrt(operand1*operand1 - 1)));
					break;
				case "atanh":
					operand1 = stack.pop();
					stack.push(Math.log((1 + operand1)/(1 - operand1))/2);
					break;
				case "acoth":
					operand1 = stack.pop();
					stack.push(Math.log((1 + operand1)/(operand1 - 1))/2);
					break;
				case "sin":
					operand1 = stack.pop();
					stack.push(Math.sin(operand1));
					break;
				case "cos":
					operand1 = stack.pop();
					stack.push(Math.cos(operand1));
					break;
				case "tan":
					operand1 = stack.pop();
					stack.push(Math.tan(operand1));
					break;
				case "cot":
					operand1 = stack.pop();
					stack.push(1/Math.tan(operand1));
					break;
				case "asin":
					operand1 = stack.pop();
					stack.push(Math.asin(operand1));
					break;
				case "acos":
					operand1 = stack.pop();
					stack.push(Math.acos(operand1));
					break;
				case "atan":
					operand1 = stack.pop();
					stack.push(Math.atan(operand1));
					break;
				case "acot":
					operand1 = stack.pop();
					stack.push(Math.PI/2 - Math.atan(operand1));
					break;
				}
			}
		}
		return stack.pop();
	}
	
}
