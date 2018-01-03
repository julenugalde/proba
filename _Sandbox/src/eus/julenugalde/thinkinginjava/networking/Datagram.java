package eus.julenugalde.thinkinginjava.networking;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * Utility class to convert back and forth between Strings and DatagramPacket
 */
public class Datagram {
	public static DatagramPacket toDatagram(String str, InetAddress destAddr, int destPort) {
		byte[] buf = str.getBytes();
		return new DatagramPacket(buf, buf.length, destAddr, destPort);
	}
	
	public static String toString(DatagramPacket packet) {
		return new String(packet.getData(), 0, packet.getLength());
	}
}
