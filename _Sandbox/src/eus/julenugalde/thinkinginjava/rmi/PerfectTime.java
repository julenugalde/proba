package eus.julenugalde.thinkinginjava.rmi;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PerfectTime extends UnicastRemoteObject implements PerfectTimeInterface {

	protected PerfectTime() throws RemoteException {
		super();
	}

	@Override
	public long getPerfectTime() throws RemoteException {
		return System.currentTimeMillis();
	}
	
	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
		try {
			PerfectTime pt = new PerfectTime();
			Naming.bind()
		}
	}

}
