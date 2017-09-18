package desktopviewerclient;

import Console.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JPanel;

class SendClientEvents implements KeyListener, MouseMotionListener, MouseListener
{
    public String width = "";
    public String height = "";
    private double _width;
    private double _height;
    private Socket _connectionSocket = null;
    private JPanel _clientPanel = null;
    private PrintWriter _writer = null;

    SendClientEvents(Socket socket, JPanel panel, String w, String h)
    {
        _connectionSocket = socket;
        _clientPanel = panel;
        width = w;
        height = h;
        _width = Double.parseDouble(w.trim());
        _height = Double.parseDouble(w.trim());

        _clientPanel.addKeyListener(this);
        _clientPanel.addMouseListener(this);
        _clientPanel.addMouseMotionListener(this);

        try
        {
            _writer = new PrintWriter(_connectionSocket.getOutputStream());
        } 
        catch(IOException ex) 
        {
            new Console().WriteLine(ex.getMessage());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e){}

    @Override
    public void mouseMoved(MouseEvent e)
    {
        double xScale = (double)_width/_clientPanel.getWidth();
        double yScale = (double)_height/_clientPanel.getHeight();
        _writer.println(-5);
        _writer.println((int)(e.getX()*xScale));
        _writer.println((int)(e.getY()*yScale));
        _writer.flush();
    }

    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e)
    {
        _writer.println(-1);
        int button = e.getButton();
        int xButton = 16;
        if(button==3)
        {
            xButton = 4;
        }
        _writer.println(xButton);
        _writer.flush();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        _writer.println(-2);
        int button = e.getButton();
        int xButton = 16;
        if(button==3)
        {
            xButton = 4;
        }
        _writer.println(xButton);
        _writer.flush();
    }

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e)
    {
        _writer.println(-3);
        _writer.println(e.getKeyCode());
        _writer.flush();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        _writer.println(-4);
        _writer.println(e.getKeyCode());
        _writer.flush();
    }
}