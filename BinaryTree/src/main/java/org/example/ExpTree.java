package org.example;

import java.util.Objects;
import java.util.Scanner;


public class ExpTree implements ExpressionService {

	private Node root;

	private String exp = "";
	public ExpTree() {
	    System.out.print("Introduzca la expresión en notación infija con todos los paréntesis y blancos: ");

		// token analyzer
	    Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
	    String line= inputScanner.nextLine();
	    inputScanner.close();

	    buildTree(line);
	}
	
	private void buildTree(String line) {	
		  // space separator among tokens
		  Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
		  root= new Node(lineScanner);
		  lineScanner.close();
	}
	


	
	static final class Node {
		private String data;
		private Node left, right;
		private Scanner lineScanner;

		public Node(Scanner theLineScanner) {
			lineScanner= theLineScanner;
			
			Node auxi = buildExpression();
			data= auxi.data;
			left= auxi.left;
			right= auxi.right;
			
			if (lineScanner.hasNext() ) 
				throw new RuntimeException("Bad expression");
		}
		public Node(){}

		private Node buildExpression() {
			Node n = new Node();
			if(lineScanner.hasNext("\\(")){
				lineScanner.next();
				n.left = buildExpression();

				if(!lineScanner.hasNext()){
					throw new RuntimeException("missing or invalis operator");
				}
				n.data = lineScanner.next();
				if(!Utils.isOperator(n.data)){
					throw new RuntimeException("missing or invalis operator");
				}
				n.right = buildExpression();
				if(lineScanner.hasNext("\\)")){
					lineScanner.next();
				} else {
					throw new RuntimeException("missing )");
				}
				return n;
			}
			if(!lineScanner.hasNext()){
				throw new RuntimeException("missing expression");
			}
			n.data = lineScanner.next();
			if(!Utils.isConstant(n.data)){
				throw new RuntimeException(String.format("illegal termin %s", lineScanner));
			}
			return n;
		}
	}  // end Node class

	@Override
	public void inorder() {
		inorderRec(root);
	}

	public void inorderRec(Node node){
		if(node == null)
			return;
		inorderRec(node.left);

		if(Utils.isConstant(node.data)){
			System.out.print(node.data);
			return;
		}
		if(Utils.isOperator(node.data)){
			System.out.print(node.data);
			inorderRec(node.right);
		}
	}
	@Override
	public void preorder(){
		preorderRec(root);
	}

	public void preorderRec(Node node){
		if(node == null)
			return;
		System.out.print(node.data);
		exp += node.data;
		preorderRec(node.left);
		preorderRec(node.right);
	}

	@Override
	public void postorder(){
		postorderRec(root);
	}
	public void postorderRec(Node node){
		if(node == null)
			return;
		preorderRec(node.left);
		preorderRec(node.right);
		System.out.print(node.data);

	}
	@Override
	public int eval(){
		return evalRec(root);
	}

	public int evalRec(Node node){
		if(node == null)
			return 0;
		if(Utils.isConstant(node.data))
			return toInt(node.data);
		int left = evalRec(node.left);
		int right = evalRec(node.right);
		if(Objects.equals(node.data, "+"))
			return left+right;
		if(Objects.equals(node.data, "-"))
			return left-right;
		if(Objects.equals(node.data, "*"))
			return left*right;
		if(Objects.equals(node.data, "/"))
			return left/right;
		return toInt(node.data);
	}

	private static int toInt(String s)
	{
		int num = 0;

		// Check if the integral value is
		// negative or not
		// If it is not negative, generate
		// the number normally
		if (s.charAt(0) != '-')
			for(int i = 0; i < s.length(); i++)
				num = num * 10 + ((int)s.charAt(i) - 48);

			// If it is negative, calculate the +ve number
			// first ignoring the sign and invert the
			// sign at the end
		else
		{
			for(int i = 1; i < s.length(); i++)
				num = num * 10 + ((int)(s.charAt(i)) - 48);
			num = num * -1;
		}
		return num;
	}
	// hasta que armen los testeos
	public static void main(String[] args) {
		ExpressionService myExp = new ExpTree();
		myExp.inorder();
		System.out.println();
		myExp.preorder();
		System.out.println();
		myExp.postorder();
		System.out.println();
		System.out.println(myExp.eval());
	}

}  // end ExpTree class
