package desktopviewerclient;

import java.net.Socket;
import javax.swing.JOptionPane;
import Console.*;
import Utilities.SocketFactory;

/**
 *
 * @author Jun
 */
public class DesktopViewerClient 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        String ip = JOptionPane.showInputDialog("Please enter server ip");
        new DesktopViewerClient().initialize(ip);
    }

    public void initialize(String IP)
    {
        try
        {
            Socket connectionSocket = new SocketFactory().getClientSocket(IP);
            System.out.println("Connecting to the Server");
            ClientData frame1 = new ClientData(connectionSocket);
        } 
        catch (Exception ex)
        {
            new Console().WriteLine(ex.getMessage());
        }
    }
}
    

