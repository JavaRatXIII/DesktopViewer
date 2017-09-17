package desktopviewer;

/**
 *
 * @author Jun
 */
public class DesktopViewer 
{
    public static String port="1204";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new StartServer(Integer.parseInt(port));
    }
    
}
