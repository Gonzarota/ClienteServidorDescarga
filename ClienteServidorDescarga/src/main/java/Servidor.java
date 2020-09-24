import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    Downloader downloader;

    public static void main(String[] args) throws Exception {

        ServerSocket ss=new ServerSocket(3333);
        System.out.println("Escuchando peticiones de descarga...");

        while(true){
            Socket s=ss.accept();
            System.out.println("Petición escuchada");

            DataInputStream din=new DataInputStream(s.getInputStream());

            String str=din.readUTF();

            System.out.println("Client says: "+str);

            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            Downloader d=new Downloader();
            d.performDownload(dout,str);

            System.out.println("Descargado");




            s.close();
        }
    }
}
