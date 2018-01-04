package eus.julenugalde.thinkinginjava.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class RMITimeServer extends UnicastRemoteObject implements PerfectTimeInterface {

	protected RMITimeServer() throws RemoteException {
		super();
	}

	@Override
	public long getPerfectTime() throws RemoteException {
		return System.currentTimeMillis();
	}
	
	public static void main(String[] args) {
		//System.setSecurityManager(new SecurityManager());
		try {
			RMITimeServer rmiTimeServer = new RMITimeServer();
			PerfectTimeInterface stub = 
					(PerfectTimeInterface)UnicastRemoteObject.exportObject(rmiTimeServer, 2005);
			
			//Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("PerfectTimeInterface", stub);
			
			//java.rmi.Naming.bind("//localhost:2005", pt);
			
			System.out.println("Server ready to do time");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}
