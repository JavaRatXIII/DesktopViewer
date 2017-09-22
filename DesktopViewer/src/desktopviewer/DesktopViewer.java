package desktopviewer;

import Utilities.SocketFactory;

/**
 *
 * @author Jun
 */
public class DesktopViewer 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        new StartServer(new SocketFactory());
    }
    
}
