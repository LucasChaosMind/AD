package br.edu.utfpr.servidordatagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorDatagram {

    public static void main(String[] args) throws SocketException, IOException {
        int porta = 1286;
        byte[] buffer = new byte[1024];
        
        //Crio um datagrama para deixar o servidor "ouvindo"
        DatagramSocket socket = new DatagramSocket(porta);
        System.out.println("Servidor esta ouvindo.................");
        DatagramPacket resposta = new DatagramPacket(buffer, buffer.length);
        //recebo a resposta
        socket.receive(resposta);
        System.out.println("Requisicao recebida!");
        String msg = new String(resposta.getData());
        System.out.println(msg);
        
        //um datagrama não cria uma conexão por isso não sabemos o ip e porta
        //do cliente que contacto o servidor, para obter essas isso precisamos
        //codificar isso "na mão"
        int portaCliente = resposta.getPort();
        InetAddress ipCliente = resposta.getAddress();
        
        msg = "(Servidor)- Oi, estou ouvindo -(Servidor)";
        buffer = msg.getBytes();
        //criamos um datagrama para retornar ao cliente com uma resposta a requisição
        //dele, passando além do array de byte e seu tamanho, o ip e porta do cliente
        System.out.println("Enviando resposta ao cliente!");
        DatagramPacket replica = new DatagramPacket(buffer, buffer.length, ipCliente, portaCliente);
        
        //envio o datagrama para com os parametros do objeto acima
        socket.send(replica);
        socket.close();
    }
}
