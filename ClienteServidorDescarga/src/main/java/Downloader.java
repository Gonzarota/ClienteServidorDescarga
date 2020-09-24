import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;

public class Downloader {

    HashMap<String,String> descargas=new HashMap<>();

    public Downloader(){
        this.descargas.put("kernel","http://mirrors.kernel.org/ubuntu/pool/main/m/mysql-5.7/mysql-server-5.7_5.7.31-0ubuntu0.16.04.1_amd64.deb");
        this.descargas.put("osuos","http://ftp.osuosl.org/pub/ubuntu/pool/main/m/mysql-5.7/mysql-server-5.7_5.7.31-0ubuntu0.16.04.1_amd64.deb");
        this.descargas.put("za","http://za.archive.ubuntu.com/ubuntu/pool/main/m/mysql-5.7/mysql-server-5.7_5.7.31-0ubuntu0.16.04.1_amd64.deb");
        this.descargas.put("video","https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4");
    }


    public void performDownload(DataOutputStream d,String web){

        try {
            URLConnection conn = new URL(descargas.get(web)).openConnection();
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

            ReadableByteChannel readableByteChannel= Channels.newChannel(conn.getInputStream());

            WritableByteChannel writableByteChannel= Channels.newChannel(d);

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while(readableByteChannel.read(buffer)!=-1) {
                buffer.flip();
                writableByteChannel.write(buffer);
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
