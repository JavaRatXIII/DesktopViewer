package desktopviewerclient;

import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import Console.*;

class ReceiveScreen extends Thread
{
    private JPanel _clientPanel = null;
    private InputStream _clinetInput = null;
    private Image _clientImage = null;

    public ReceiveScreen(InputStream input,JPanel clientPanel){
        _clinetInput = input;
        _clientPanel = clientPanel;
        start();
    }

    public void run()
    {
        try
        {
            while(true)
            {
                byte[] bytes = new byte[1024*1024];
                int count = 0;
                
                while(!(count>4 && bytes[count-2]==(byte)-1 && bytes[count-1]==(byte)-39))
                {
                    count+=_clinetInput.read(bytes,count,bytes.length-count);
                }

                _clientImage = ImageIO.read(new ByteArrayInputStream(bytes));
                _clientImage = _clientImage.getScaledInstance(_clientPanel.getWidth(),_clientPanel.getHeight(),Image.SCALE_FAST);
                Graphics graphics = _clientPanel.getGraphics();
                graphics.drawImage(_clientImage, 0, 0, _clientPanel.getWidth(), _clientPanel.getHeight(), _clientPanel);
            }
        } 
        catch(IOException ex) 
        {
            new Console().WriteLine(ex.getMessage());
        }
    }
}
