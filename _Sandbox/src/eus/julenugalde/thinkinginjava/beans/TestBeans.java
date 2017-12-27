package eus.julenugalde.thinkinginjava.beans;

import java.beans.*;
import java.lang.reflect.Method;

public class TestBeans {

	public static void dump(Class<?> bean) {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(bean, java.lang.Object.class);
		} catch (IntrospectionException ie) {
			System.out.println("Couldn't introspect " + bean.getName());
			System.exit(1);
		}
		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
		System.out.println("PROPERTIES INFO\nTYPE\tNAME\tREAD METHOD\tWRITE METHOD");
		for (PropertyDescriptor property : properties) {
			Class<?> type = property.getPropertyType();
			Method readMethod = property.getReadMethod();
			Method writeMethod = property.getWriteMethod();
			System.out.println(type.getName() + "\t" + property.getName() + "\t" + 
					readMethod.getName() + "\t" + writeMethod.getName());
		}
		
		MethodDescriptor[] methods = beanInfo.getMethodDescriptors();
		System.out.println("METHODS INFO");
		for (MethodDescriptor method : methods) {
			System.out.println(method.getMethod().toString());
		}
	}
	
	public static void main(String[] args) {
		dump(eus.julenugalde.thinkinginjava.beans.Pokemon.class);
	}
}
