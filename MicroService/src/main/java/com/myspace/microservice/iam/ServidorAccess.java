/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myspace.microservice.iam;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import model.Cliente;

/**
 *
 * @author Aluno
 */
public class ServidorAccess {
    public static void Main(String[] args){
        int port = 1286;
        byte[] bufferAccess = new byte[1024];
        byte[] bufferReply = new byte[1024];
        
        Cliente comand = new Cliente();
        
        try{

            //Crio um datagrama para deixar o servidor "ouvindo"
            DatagramSocket socket = new DatagramSocket(port);
           
            DatagramPacket response = new DatagramPacket(bufferAccess, bufferAccess.length);
            //recebo a resposta
            socket.receive(response);
            
            Integer value = Integer.parseInt(new String(response.getData()));
            System.out.println("Access with password: "+value);
        
          
            comand.setPort(response.getPort());
            comand.setIP(response.getAddress());
        
            String msg = "OK";
            bufferReply = msg.getBytes();
            //criamos um datagrama para retornar ao cliente com uma resposta a requisição
            //dele, passando além do array de byte e seu tamanho, o ip e porta do cliente
            System.out.println("Acesso autorizado!");
            DatagramPacket replica = new DatagramPacket(bufferReply, bufferReply.length, comand.getIP(), comand.getIP());
        
            //envio o datagrama para com os parametros do objeto acima
            socket.send(replica);
            socket.close();
            
        }catch(SocketException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
