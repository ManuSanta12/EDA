package org.example;

// bajar el paquete nativo
// https://gluonhq.com/products/javafx/ 

// en el VM poner el lib del paquete nativo
// --module-path C:\Users\lgomez\Downloads\javafx-sdk-11.0.2\lib --add-modules javafx.fxml,javafx.controls


import org.example.controller.GraphicsTree;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import org.example.core.BSTTree;
import org.example.core.BSTreeInterface;

public class TestGUI extends Application {

	public static void main(String[] args) {
		// GUI
		launch(args);
	}

    @Override
	public void start(Stage stage) {
		stage.setTitle("Drawing the BST");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 500, 500);

		BSTTree<Integer> myTree = createModel();
		
	
		GraphicsTree<Integer> c = new GraphicsTree<>(myTree);

		c.widthProperty().bind(scene.widthProperty());
		c.heightProperty().bind(scene.heightProperty());
	
		root.getChildren().add(c);
		stage.setScene(scene);
		stage.show();
		

	}

    
	private BSTTree<Integer> createModel2() {
		BSTTree<Integer> myTree = new BSTTree<>();
		myTree = new BSTTree<>();
		myTree.insert(50);
		myTree.insert(60);
		myTree.insert(80);
		myTree.insert(20);
		myTree.insert(70);
		myTree.insert(40);
		myTree.insert(44);
		myTree.insert(10);
		myTree.insert(40);
		myTree.inOrder();

		return myTree;
	}
	
	
	private BSTTree<Integer> createModel() {
		BSTTree<Integer> myTree = new BSTTree<>();
	myTree.insert(120);
	myTree.insert(100);
	myTree.insert(200);
	myTree.insert(20);
	myTree.insert(100);
	myTree.insert(100);
	
	return myTree;
	}
	

}