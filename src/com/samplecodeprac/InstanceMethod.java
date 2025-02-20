package com.samplecodeprac;

public class InstanceMethod {
//	public void greet() {
//		System.out.println("Welcome to JAVA");
//	}
//	
//	public static void main(String[] args) {
//		InstanceMethod IM = new InstanceMethod();
//		IM.greet();
//	}
//  
//}

	public void multiply(int a, int b) {
		System.out.println("Mulplication :" + (a * b));
	}
	public static void main (String[] args) {
		InstanceMethod multi = new InstanceMethod();
		multi.multiply(5, 2);
	}
}