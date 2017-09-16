package desktopviewerclient;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.*;
import Console.*;

import java.io.IOException;

class CreateClientFrame extends Thread 
{
    public String width="";
    public String height="";
    private JFrame _desktopFrame = new JFrame();
    private JDesktopPane _desktop = new JDesktopPane();
    private Socket _connectionSocket = null;
    private JInternalFrame _inerFrame = new JInternalFrame("Server Screen", true, true, true);
    private JPanel _panel = new JPanel();

    public CreateClientFrame(Socket socket, String w, String h) 
    {
        width = w;
        height = h;
        _connectionSocket = socket;
        start();
    }

    public void drawDesktop() 
    {
        _desktopFrame.add(_desktop, BorderLayout.CENTER);
        _desktopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _desktopFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        _desktopFrame.setVisible(true);

        _inerFrame.setLayout(new BorderLayout());
        _inerFrame.getContentPane().add(_panel, BorderLayout.CENTER);
        _desktop.add(_inerFrame);

        try 
        {
            _inerFrame.setMaximum(true);
        }catch(PropertyVetoException ex) 
        { 
             new Console().WriteLine(ex.getMessage());
        }

        _panel.setFocusable(true);
        _inerFrame.setVisible(true);
    }

    @Override
    public void run() 
    {
        InputStream in = null;
        drawDesktop();

        try
        {
            in = _connectionSocket.getInputStream();
        }
        catch (IOException ex)
        {
            new Console().WriteLine(ex.getMessage());
        }

        new ClientScreen(in,_panel);
        new SendClientEvents(_connectionSocket,_panel,width,height);
    }
}
