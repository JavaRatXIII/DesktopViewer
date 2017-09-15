package desktopviewerclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import Console.*;

class ClientData
{
    private Socket _connectionSocket = null;
    private DataOutputStream _pass = null;
    private DataInputStream _verification = null;
    public String width="";
    public String height="";

    ClientData(Socket cSocket)
    {
        _connectionSocket = cSocket;
        meth();
    }
    
    public void meth()
    {
        try
        {
            _pass= new DataOutputStream(_connectionSocket.getOutputStream());
            _verification= new DataInputStream(_connectionSocket.getInputStream());
            _pass.writeUTF("");
            _verification.readUTF();
        }
        catch (IOException e)
        {
            new Console().WriteLine(e.getMessage());
        }

        try
        {
            width = _verification.readUTF();
            height = _verification.readUTF();
        }
        catch (IOException e)
        {
            new Console().WriteLine(e.getMessage());		
        }
        CreateClientFrame abc= new CreateClientFrame(_connectionSocket,width,height);
    }			
}

