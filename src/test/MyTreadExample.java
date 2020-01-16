package test;

public class MyTreadExample {
	public static void main(String[] args) {
        Thread t1 = new MyThread(1);
        Thread t2 = new MyThread(2);
        Thread t3 = new MyThread(3);
        Thread t4 = new MyThread(4);
        Thread t5 = new MyThread(5);
        
        // 新增 5 個 thread

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        System.out.println("stop");
	}
}
