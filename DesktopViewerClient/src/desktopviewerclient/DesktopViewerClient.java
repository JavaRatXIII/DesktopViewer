package desktopviewerclient;

import java.net.Socket;
import javax.swing.JOptionPane;
import Console.*;

/**
 *
 * @author Jun
 */
public class DesktopViewerClient 
{
    static String port = "1204";

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        String ip = JOptionPane.showInputDialog("Please enter server ip");
        new DesktopViewerClient().initialize(ip, Integer.parseInt(port));
    }

    public void initialize(String ip, int port)
    {
        try
        {
            Socket sc = new Socket(ip,port);
            System.out.println("Connecting to the Server");
            //ClientData finds clients PC data
            ClientData frame1= new ClientData(sc);
        } 
        catch (Exception ex)
        {
            new Console().WriteLine(ex.getMessage());
        }
    }
}
    

