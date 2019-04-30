package bach;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;
import javafx.scene.control.TextField;

import java.sql.*;

class Bachmanity extends Thread
{
    
    String prnNum;

	Bachmanity(){

	}

	Bachmanity(String prnNum){
		this.prnNum=prnNum;
	}
    
	static int cnt=0;
	static Runtime r= Runtime.getRuntime();

	public void run()
	{
            
            //connect to database
          try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/prj","root","");
            
               Statement stmnt = con.createStatement();
		while(true)
		{
				
			try{
			Process p= r.exec("tasklist");
			InputStream inputStream = p.getInputStream();
		 	InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		 	BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		 	

			String sb=bufferedReader.readLine();
                        
			String token = new String("");


			while(sb!=null)
			{
				sb=bufferedReader.readLine(); 
				token+=(sb);
			}

			if(token.contains("opera.exe")||token.contains("firefox.exe")|| token.contains("chrome.exe")||token.contains("edge.exe"))
			{
                            Thread.sleep(5000);
				System.out.println("Opened Browser");
                               // Send data to database
                             //  stmnt.executeUpdate("INSERT into record values('2016BTECS00038','Desc gavla browser','gvhfuy')");
			      // Thread.sleep(5000);

				try
				{
                                  //  System.out.println("122");
					Robot robot = new Robot();
					String format = "jpeg";
					String Fname = prnNum+cnt+"."+format;
                                    //        System.out.println("223");
					cnt++;
                                        
                                        File f = new File(Fname);
                                      //    System.out.println("3 test");
                                         PreparedStatement pst = con.prepareStatement("INSERT INTO prj(rno,msg,photo) VALUES(?,?,?)");
					 Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
					 BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
                                         ImageIO.write(screenFullImage, format, f);
                                       //   System.out.println("4 test");
                                       //  Thread.sleep(1000);
                                        pst.setString(1,prnNum);
                                        pst.setString(2,"Opened a web browser.");
                                        FileInputStream finputStream = new FileInputStream(f);
                                        //  System.out.println("5 test");
                                         
                                         pst.setBinaryStream(3, (InputStream) finputStream, (long)(f.length()));
                                         //pst.setInt(2, cnt);
                                         // System.out.println("6 test");
                                         pst.executeUpdate();
                                         // System.out.println("7 test");
                                         
                                        Thread.sleep(5000);
		        }
		        catch (Exception e){e.printStackTrace();}
			}
		}
		catch(Exception e)
		 	{e.printStackTrace();}
		}
                
        }catch(Exception e){e.printStackTrace();}  //outer exception
		
	}
	

}

class PdDetect extends Thread
{
    
    String prnNum;

	PdDetect(){
		
	}

	PdDetect(String prnNum){
		this.prnNum=prnNum;
	}


    
    
    int c=0;
    static File[] oldListRoot = File.listRoots();
    

        
	        public void run()
	         {
                     
                    try{
               
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/prj","root","");
            
               PreparedStatement stmnt = con.prepareStatement("INSERT INTO prj values(?,?,?)");

	 
                     
	            while (true)
	             {
	                try 
	                {
	                    Thread.sleep(100);
	                } 
	                catch (InterruptedException e) 
	                {
	                    e.printStackTrace();
	                }
	                if (File.listRoots().length > oldListRoot.length) 
	                {
	                    System.out.println("new drive detected");
                           // stmnt.executeUpdate("INSERT into record values(prnNum,'pipipipvla browser','gvpopopopohfuy')");
	                    File f = new File("pdinsert.jpg");
                            FileInputStream finputStream = new FileInputStream(f);
                            stmnt.setString(1,prnNum);
                            stmnt.setString(2,"An external drive is inserted.");
                            stmnt.setBinaryStream(3,(InputStream)finputStream,(long)f.length());
                            stmnt.executeUpdate();
                            
                            
                         
                            oldListRoot = File.listRoots();
	                    System.out.println("drive"+oldListRoot[oldListRoot.length-1]+" detected");

	                } 
	                else if(File.listRoots().length < oldListRoot.length) 
	                {
	    				System.out.println(oldListRoot[oldListRoot.length-1]+" drive removed");
                                        oldListRoot = File.listRoots();
                                        
                                        File f = new File("pdremoved.jpeg");
                                        FileInputStream finputStream = new FileInputStream(f);
                                        stmnt.setString(1,prnNum);
                                        stmnt.setString(2,"An external drive is removed.");
                                       stmnt.setBinaryStream(3,(InputStream)finputStream,(long)f.length());
                                        stmnt.executeUpdate();

	                }

	            }
	        }
                    catch(Exception e){}
                }
                
}



public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button start123;
    
    @FXML
    private TextField prn;
    
    @FXML
    private void start(ActionEvent event) throws IOException {
        
        System.out.println(prn.getText());
        String prnNum = prn.getText();
        start123.setDisable(true);
      Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("All the best");
                a.setHeaderText("Best of luck");
                a.setResizable(true);
                String version = System.getProperty("java.version");
                String content = String.format("Don't cheat yourself!", version);
                a.setContentText(content);
                a.showAndWait();
                
             /*   Parent tp;
        tp = FXMLLoader.load(getClass().getResource("commence.fxml"));
        Scene ts= new Scene( tp );
        
        Stage w= (Stage)((Node)event.getSource()).getScene().getWindow();
        
        w.setScene(ts);
        w.show();*/
             
             System.out.print("Function Started.");
             Bachmanity b= new Bachmanity(prnNum);
	     PdDetect pd= new PdDetect(prnNum);

		Thread t1= new Thread(b);
		Thread t2= new Thread(pd);
		t1.start();
		t2.start();
                
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
