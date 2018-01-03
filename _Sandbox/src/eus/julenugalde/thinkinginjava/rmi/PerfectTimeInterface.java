package eus.julenugalde.thinkinginjava.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PerfectTimeInterface extends Remote {
	public long getPerfectTime() throws RemoteException;
}
