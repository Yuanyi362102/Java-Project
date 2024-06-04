package Communication;

import java.awt.FlowLayout;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import Frames.ChatFrame;


public class ReceiveMsg extends Thread{
    ChatFrame chatFrame;
    DatagramSocket socket;
    public ReceiveMsg(ChatFrame chatFrame,DatagramSocket socket){
        this.chatFrame = chatFrame;
        this.socket = socket;
    }
    public void run(){
        System.out.println("======启动后台接收消息线程====");
        byte[] data = new byte[1024];

        while(true){
            //创建包裹对象
            DatagramPacket packet = new DatagramPacket(data, data.length);

            try {
                //等待接受消息，同时阻塞线程
                socket.receive(packet);
                //如果接收到消息后，上面阻塞解除，并将本地空的packet对象填充接收到的消息
                int len = packet.getLength();
                String msg = new String(data,0,len);
                System.out.println("接收到消息:"+msg);
                //接收的消息添加到消息面板
                msgAddToMsgPanel recevieMsg = new msgAddToMsgPanel(msg,chatFrame,FlowLayout.LEFT);
                recevieMsg.addMegToPanel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
