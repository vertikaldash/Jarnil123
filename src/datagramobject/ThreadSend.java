package datagramobject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class ThreadSend extends Thread {

	private Thread sendThread;
	final static String INET_ADDR = "224.0.0.3";
	final static int PORT = 8888;
	// private static ReceiverSide rcvs = new ReceiverSide();
	String pesan, penerima;
	static String pengirim;
	static int lokasi;
	int TTL, hop, maxDist;
	InetAddress addr = InetAddress.getByName(INET_ADDR);
	ArrayList<Message> listMessage = ThreadReceive.listMessage; 
	/*ThreadSend(String pengirim) throws UnknownHostException {
		this.pengirim = pengirim;
	}*/
	
	ThreadMulticast reSend = new ThreadMulticast();

	ThreadSend() throws UnknownHostException{
	}

	public static String getPengirim(){
		return pengirim;
	}
	
	public static int getLokasi(){
		return lokasi;
	}
	public void run() {
		System.out.println("memebuat thread send");
		System.out.println("masukkan nama : ");
		Scanner pengirimIn = new Scanner(System.in);
		this.pengirim = pengirimIn.next();
		System.out.println("Masukkan Jarak Lokasi anda : ");
		this.lokasi = pengirimIn.nextInt();
		// rcvs.start();
		while (true) {
			try (DatagramSocket serverSocket = new DatagramSocket()) {

				Random rand = new Random();
				int id = rand.nextInt(1111111111);

				System.out.println("Masukkan pesan: ");
				Scanner input_m = new Scanner(System.in);
				// this.pesan = pesan;
				String msg = input_m.nextLine();

				System.out.println("Masukkan penerima: ");
				Scanner input_r = new Scanner(System.in);
				String recv = input_r.nextLine();
			

				System.out.println("Masukkan TTL: ");
				Scanner input_ttl = new Scanner(System.in);
				int ttl = input_ttl.nextInt();
				// this.TTL = TTL;

				System.out.println("Masukkan Hop: ");
				Scanner input_hop = new Scanner(System.in);
				int hop = input_hop.nextInt();
				// this.hop = hop;
				
				System.out.println("Masukkan max Distance: ");
				Scanner maxDstc = new Scanner(System.in);
				int maxDist = maxDstc.nextInt();

				ByteArrayOutputStream b_out = new ByteArrayOutputStream(256);
				ObjectOutputStream o_out = new ObjectOutputStream(
						new BufferedOutputStream(b_out));

				o_out.flush();
				/* tanpa distance
				o_out.writeObject(new Message(id, this.pengirim, recv, msg,
						ttl, hop));
						*/
				//dengan distance/lokasi
				o_out.writeObject(new Message(id, this.pengirim, recv, msg,
						ttl, hop, maxDist));
				o_out.flush();

				byte[] b = b_out.toByteArray();

				DatagramPacket msgPacket = new DatagramPacket(b, b.length,
						addr, PORT);
				serverSocket.send(msgPacket);

				// System.out.println("User "+ sender + " send message : " +
				// msg);
				o_out.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}
}
