import myfirstpackage.*;
class MyFirstClass {
	// public static void main(String[] s) {
	// 	System.out.println("Hello world!!!");
	// }
    public static void main(String[] s) {
        int i, j;
        MySecondClass o = new MySecondClass();
        for (i = 1; i <= 8; i++) {
            for(j = 1; j <= 8; j++) {
                o.SetI(i);
                o.SetJ(j);
                System.out.print(o.Action());
                System.out.print(" ");
            }
            System.out.println();
        }

    }
}
