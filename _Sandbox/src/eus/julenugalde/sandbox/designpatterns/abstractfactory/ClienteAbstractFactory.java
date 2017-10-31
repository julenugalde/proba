package eus.julenugalde.sandbox.designpatterns.abstractfactory;

public class ClienteAbstractFactory {

	public static void main(String[] args) {
		AbstractFactory factory = AbstractFactory.getFactory(Architecture.ENGINOLA);
		CPU cpu = factory.createCPU(123456);
		MMU mmu = factory.createMMU();
		
		System.out.println(cpu.getComponentType() + " - " + cpu.getArchitectureName());
		System.out.println(cpu.toString());
		System.out.println(mmu.toString());
	}

}
