package ru.samsung.itschool.mdev;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Процесс (Программа)
        // Поток
        // Синхронизация потоков
        //  new MyThread("+").start();  // 1
        /// new MyThread("-").start();  // 2
        MyThread thread1 = new MyThread("+");
        thread1.start();
        MyThread thread2 = new MyThread("-");
        thread2.start();
        Thread.sleep(1000);
        thread1.flag = false;
        thread1.join(); // ждет завершения потока
        test("1Stopped!");

        // [+][-][+][-][+][-]
    }

    public static final Object key = new Object();
    public static void test(String message) {

      //  synchronized (key) {
            try {
                System.out.print("[");
                Thread.sleep(10);
                System.out.print(message);
                Thread.sleep(10);
                System.out.print("]");
    //            key.notify(); // возобновляем поток, наход. в режим ожидания
    //            key.wait(); // выставляем потоку режим ожидания
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
   //     }

    }

}
class MyThread extends Thread {
    private String mess;
    public boolean flag = true;
    MyThread(String mess) {
        this.mess = mess;
    }
    @Override
    public void run() {
        while (flag) {
            Main.test(this.mess);
        }
    }
}

