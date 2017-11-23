package eus.julenugalde.thinkinginjava.chapter5;

public class Exercise1 {
	public int iPublic = 1;
	int iPackage = 2;
	protected int iProtected = 3;
	@SuppressWarnings("unused")
	private int iPrivate = 4;
	
		public void mPublic() {
		System.out.println("Public method");
	}
	
	void mPackage() {
		System.out.println("Package method");
	}
	
	protected void mProtected() {
		System.out.println("Protected method");
	}
	
	@SuppressWarnings("unused")
	private void mPrivate() {
		System.out.println("Private method");
	}
}
