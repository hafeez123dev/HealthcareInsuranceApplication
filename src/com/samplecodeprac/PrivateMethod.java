package com.samplecodeprac;

class PrivateMethod {
    private void secretMethod() {
        System.out.println("This is a private method.");
    }

    public void callSecret() {
        secretMethod(); // Allowed inside the same class
    }

    public static void main(String[] args) {
        PrivateMethod obj = new PrivateMethod();
        obj.callSecret(); // Indirectly calling private method
    }
}

