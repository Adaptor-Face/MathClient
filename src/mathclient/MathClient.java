/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathclient;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Face
 */
public class MathClient extends Application {

    private ArrayList<String> log = new ArrayList<>();

    private String message = "";
    private String receivedLine = "";
    private CommandWords commandWords;

    @Override
    public void start(Stage primaryStage) {
        commandWords = new CommandWords();
        TextArea txt = new TextArea();
        TextField tf = new TextField();
        txt.setEditable(false);
        tf.setText("");
        final Counter counter = new Counter();
        tf.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                message = tf.getText();
                log.add(message);
                counter.set(log.size());
                if (message.equals("quit")) {
                    stop();
                }
                CommandParser cp = new CommandParser(message);
                if (commandWords.isCommand(cp.getName())) {
                    Command testCom = commandWords.getCommand(cp.getName());
                    String string = testCom.process(cp.getArgArray());
                    txt.appendText("\n" + message + "\n" + string + "\n");
                } else if (Calculator.isMathExpression(message)) {
                    Calculator calc = new Calculator();
                    txt.appendText("\n" + message + calc.calculate(message) + "\n");
                } else {
                    txt.appendText("\n\"" + message + "\" is not a command. Type help for a list of commands");
                }
                tf.setText("");
            }
        });

        tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode().equals(KeyCode.UP)) {
                    if (counter.getNumber() > 0) {
                        counter.decrement();
                        tf.setText(log.get(counter.getNumber()));
                    }
                } else if (t.getCode().equals(KeyCode.DOWN)) {
                    if (counter.getNumber() < log.size()) {
                        tf.setText(log.get(counter.getNumber()));
                        counter.increment();
                    } else {
                        tf.clear();
                    }
                }
            }
        });
        BorderPane root = new BorderPane();
        root.setBottom(tf);
        root.setCenter(txt);

        Scene scene = new Scene(root, 450, 350);

        primaryStage.setTitle("Awesome ClientUI");
        primaryStage.setScene(scene);
        String[] arguments = new String[0];
        txt.appendText(commandWords.getCommand("help").process(arguments));
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
