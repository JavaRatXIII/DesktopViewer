package desktopviewer;

/**
 *
 * @author Jun
 */
public class DesktopViewer 
{
    static String port="1204";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new InitConnection(Integer.parseInt(port),"");
    }
    
}
