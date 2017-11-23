package eus.julenugalde.thinkinginjava.chapter5;

public class Exercise1Main {

	public static void main(String[] args) {
		Exercise1 obj = new Exercise1();
		
		obj.iPublic++;
		obj.mPublic();
		
		obj.iPackage++;
		obj.mPackage();
		
		obj.iProtected++;
		obj.mProtected();
		
		//obj.iPrivate++;
		//obj.mPrivate();
	}

}
