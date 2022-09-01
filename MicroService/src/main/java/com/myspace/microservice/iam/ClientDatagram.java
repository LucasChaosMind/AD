package br.edu.utfpr.servidordatagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDatagram {

    public static void main(String[] args){
                
        //inicializo um datagrama de envio
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress ip = InetAddress.getByName("localhost");//ip do servidor
            int porta = 1286;//porta do servidor
            byte[] buffer = new byte[1054];
        
            String msg = "(Cliente)- Oi, alguem ouvindo? -(Cliente)";
            
            //transformo uma String em bytes (serealização)
            buffer = msg.getBytes();
            System.out.println("Enviando mensagem ao servido!");
            
            //envio o datagrama através de um socket
            DatagramPacket pergunta = new DatagramPacket(buffer, buffer.length, ip, porta);
            socket.send(pergunta);
            
            //cliente fica esperando uma resposta
            DatagramPacket resposta = new DatagramPacket(buffer, buffer.length);
            socket.receive(resposta);
            
            //transmorfo a mensagem em um String (deserealização)
            msg = new String (resposta.getData());
            System.out.println(msg);
            
            //fecho o socket
            socket.close();
        } catch (SocketException se) {
            Logger.getLogger(ClientDatagram.class.getName()).log(Level.SEVERE, null, se);
        } catch (UnknownHostException uhe){
            Logger.getLogger(ClientDatagram.class.getName()).log(Level.SEVERE, null, uhe);
        }catch (IOException ex) {
            Logger.getLogger(ClientDatagram.class.getName()).log(Level.SEVERE, null, ex); 
        }//fim catch
    }//fim main
}//fim class