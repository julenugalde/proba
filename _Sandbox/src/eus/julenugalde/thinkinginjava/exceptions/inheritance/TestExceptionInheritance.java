package eus.julenugalde.thinkinginjava.exceptions.inheritance;

public class TestExceptionInheritance implements AInterface, BInterface{
	public static void main(String[] args) {
		TestExceptionInheritance obj = new TestExceptionInheritance();
		
		try {
			obj.aMethod();
		} 
		catch (AException e) {System.out.println("AException caught");}
		finally {System.out.println("finally block for A method call");}
				
		try {
			obj.bMethod();
		} 
		catch (BException e) {System.out.println("BException caught");}
		finally {System.out.println("finally block for B method call");}
		
		try {
			obj.commonMethod();
		} 
		catch (Exception e) {
			System.out.println("Exception caught: " + e.getMessage());
		}
		finally {System.out.println("finally block for common method call");}
		
		int number = -1;
		try {
			number = obj.getNumber(-25);
		} catch (Exception e) {
			System.out.println("Number not valid. Setting it to a safe value.");
			number = 0;
		} finally {
			System.out.println("Number = " + number);
		}
	}

	private int getNumber(int i) throws Exception {
		if (i<0) throw new Exception();
		return i;
	}

	@Override
	public void bMethod() throws BException {
		System.out.println("B Method");
		//throw new BException();
		
	}

	@Override
	public void aMethod() throws AException {
		System.out.println("A Method");
		throw new AException();
	}

	@Override
	public void commonMethod() {
		System.out.println("Common Method");
		//throw new AException();
		//throw new BException();		
	}
}
