package eus.julenugalde.thinkinginjava.io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ConnectionData implements Externalizable {
	private String userName, password, address;
	private int port;
	
	public ConnectionData(String userName, String password, String address, int port) {
		this.userName = userName; this.password = password; this.address = address; this.port = port;
	}	
	public ConnectionData() {this("", "", "", -1);}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(userName);
		out.writeUTF(address);
		out.writeInt(port);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		userName = in.readUTF();
		address = in.readUTF();
		port = in.readInt();
	}
	
	@Override
	public String toString() {
		return userName + "@" + address + ":" + port + " (password=" + password + ")";
	}
}
