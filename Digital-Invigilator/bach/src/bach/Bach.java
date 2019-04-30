
package bach;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author TeamBachmanity - ashtnemi448, iamrohitrc, kesurswapnil
 */
public class Bach extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        System.out.println(com.sun.javafx.runtime.VersionInfo.getRuntimeVersion());
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                System.out.println("Cannot be closed. Focus on the exam.");
                
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Beware");
                a.setHeaderText("Last Warning. Admin will recieve updates.");
                a.setResizable(true);
                String version = System.getProperty("java.version");
                String content = String.format("Closing this will send notification to Admin.", version);
                a.setContentText(content);
                a.showAndWait();
                
                e.consume();
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
