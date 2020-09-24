import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws Exception {

        Scanner sc=new Scanner(System.in);
        System.out.println("¿Qué paquete quieres? (kernel, osuos, za o video?)");
        String paquete=sc.nextLine();

        Socket s = new Socket("localhost",3333);

        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        dout.writeUTF(paquete);

        DataInputStream din=new DataInputStream(s.getInputStream());
        FileOutputStream fileOutputStream=new FileOutputStream("film.mp4");


        ReadableByteChannel readableByteChannel= Channels.newChannel(din);
        WritableByteChannel writableByteChannel= Channels.newChannel(fileOutputStream);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while(readableByteChannel.read(buffer)!=-1) {
            buffer.flip();
            writableByteChannel.write(buffer);
            buffer.clear();
        }

        dout.close();
        din.close();
        s.close();

    }

}
