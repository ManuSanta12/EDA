package org.example;

public interface ExpressionService {
	
	// lanza exception si no se puede evaluar porque hay algo mal formado en la expresion
	int eval();
	
	void preorder();
	
	void inorder();
	
	void postorder();
	
}
