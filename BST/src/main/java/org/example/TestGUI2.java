package org.example;

// https://gluonhq.com/products/javafx/

// en el VM poner 
// --module-path C:\Users\lgomez\Downloads\javafx-sdk-11.0.2\lib --add-modules javafx.fxml,javafx.controls


import org.example.controller.GraphicsTree;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import org.example.core.BSTTree;
import org.example.core.BSTreeInterface;

public class TestGUI2 extends Application {

	public static void main(String[] args) {
		// GUI
		launch(args);
	}

    @Override
	public void start(Stage stage) {
		stage.setTitle("Drawing the BST11");
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 500, 500);
		BSTreeInterface<Person> myTree = createModel();
        GraphicsTree<Person> c = new GraphicsTree<>(myTree);
		
		
		c.widthProperty().bind(scene.widthProperty());
		c.heightProperty().bind(scene.heightProperty());
	
		root.getChildren().add(c);
		stage.setScene(scene);
		stage.show();
		
	}


	
	private BSTTree<Person> createModel() {
		BSTTree<Person> myTree = new BSTTree<>();
	   myTree.insert(new Person(50, "Ana"));
	   myTree.insert(new Person(60, "Juan"));
	   myTree.insert(new Person(80, "Sergio"));
	   myTree.insert(new Person(20, "Lila"));
	   myTree.insert(new Person(77, "Ana"));
	   
	   return myTree;
	}	


	
	private class Person implements Comparable<Person>{
		private Integer legajo;
		private String nombre;
		
		public Person(int aLegajo, String aNombre) {
			legajo= aLegajo;
			nombre= aNombre;
		}

		@Override
		public int compareTo(Person other) {
			return legajo.compareTo(other.legajo) ;
		}

		public String toString() {
			return legajo.toString() + "\n" + nombre;
		}
		
		
	}

}