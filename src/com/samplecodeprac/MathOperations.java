package com.samplecodeprac;

class MathOperations {
//    public int square(int num) {
//        return num * num;
//    }
//
//    public static void main(String[] args) {
//        MathOperations obj = new MathOperations();
//        int result = obj.square(4);
//        System.out.println("Square: " + result);
//    }
//}
//	=====================================================
//	public int square(int num) {
//		return num * num;
//	}
//	public static void main(String[] args) {
//		MathOperations obj = new MathOperations();
//		int result = obj.square(5);
//		System.out.println("Square :" +result);
//	}
//}
//==============================================================
	
	
	    public int add(int a, int b) {
	        return a + b;
	    }

	    public double add(double a, double b) {
	        return a + b;
	    }

	    public int add(int a, int b, int c) {
	        return a + b + c;
	    }
	

	    public static void main(String[] args) {
	    	MathOperations obj = new MathOperations();
	        System.out.println(obj.add(10, 20));         // Calls add(int, int)
	        System.out.println(obj.add(10.5, 20.5));    // Calls add(double, double)
	        System.out.println(obj.add(10, 20, 30));    // Calls add(int, int, int)
	    }
	}
