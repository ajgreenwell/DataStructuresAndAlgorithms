/* file: Main.java
   author: Ryan Krawczyk, Andrew Greenwell

   CSCI 1102 Computer Science 2

   This is the main entry point of an implementation of
   C. Shannon's n-gram algorithm for modeling English text.
*/
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.TextArea;
import javafx.collections.ObservableList;
import java.util.Iterator;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;

public class Main extends Application {

  public static final Character SENTINEL = new Character((char) 0);

  private final int fontSize = 22;
  private final String style =
    String.format("-fx-font: %d arial; -fx-base: #1e90ff;", fontSize);

  private void setPromptText(Node node, String text) {
    ObservableList<Node> ol = ((HBox) node).getChildren();
    for (Node n : ol)
      ((TextArea) n).setPromptText(text);
  }

  public static void setText(Node node, String text) {
    ObservableList<Node> ol = ((HBox) node).getChildren();
    for (Node n : ol)
      ((TextArea) n).setText(text);
  }

  public static String getText(Node node) {
    ObservableList<Node> ol = ((HBox) node).getChildren();
    Iterator i = ol.iterator();
    return ((TextArea)(i.next())).getText();
  }

  private Node makeTextArea(String text) {
    HBox hbox = new HBox();
    TextArea txt = new TextArea();
    txt.setWrapText(true);
    txt.setFont(new Font(fontSize));
    txt.setPromptText(text);
    txt.setFocusTraversable(false);
    hbox.getChildren().add(txt);
    return hbox;
  }

  private void loadFile(File file, Node textArea) {
    StringBuilder sb = new StringBuilder();
    String line;
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      while ((line = br.readLine()) != null) sb.append(line + "\n");
    } catch (IOException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    setText(textArea, sb.toString());
  }

  // extendString takes care of tacking a new character onto the right
  // end of the subject string, if it gets up to length degree then a
  // character is dropped off the left end.
  //
  public static String extendString(String old, Character c, int degree) {
    String suffix = c.toString();
    boolean atDegree = old.length() == degree;
    return (atDegree ? old.substring(1) : old) + suffix;
  }

  @Override
  public void start(final Stage stage) {

    // Setting up the UI
    //
    stage.setTitle("Shannon's n-Gram Markov Model of Text");

    Node
      inputTextArea = makeTextArea("Type something or load a text file."),
      outputTextArea = makeTextArea("");

    // loading an input text file
    //
    FileChooser fileChooser = new FileChooser();
    File dir = new File(System.getProperty("user.dir"));
    fileChooser.setInitialDirectory(dir);

    Button loadButton = new Button("Load");
    loadButton.setStyle(style);
    loadButton.setOnAction( e -> {
      File file = fileChooser.showOpenDialog(stage);
      if (file != null) loadFile(file, inputTextArea);
      });

    // The text input field for the arity
    //
    TextField degree = new TextField("4");
    degree.setStyle(style);

    // The Go button, runs the application. 
    //
    Button goButton = new Button("Go");
    goButton.setStyle(style);
    goButton.setOnAction( e -> {
      Integer n = Integer.parseInt(degree.getText());
      Shannon shannon = new Shannon(inputTextArea, n, outputTextArea);
      shannon.process();
    });

    GridPane controlPane = new GridPane();
    controlPane.setAlignment(Pos.CENTER);
    controlPane.setPadding(new Insets(10, 10, 10, 10));
    controlPane.setHgap(20);

    GridPane.setConstraints(loadButton, 1, 0);
    GridPane.setConstraints(degree,     2, 0);
    GridPane.setConstraints(goButton,   3, 0);
    controlPane.getChildren().addAll(goButton, degree, loadButton);

    Pane rootGroup = new VBox(20);
    rootGroup.getChildren().addAll(inputTextArea, controlPane, outputTextArea);
    rootGroup.setPadding(new Insets(12, 12, 12, 12));

    stage.setScene(new Scene(rootGroup));
    stage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
