public class Printer extends Thread {

    public static volatile int MAX_PAPER_TRAY_COUNT = 250;
    public static volatile int MAX_CARTRIDGE_COUNT = 50;
    public static volatile int USED_CARTRIDGE_COUNT = 0;
    public static volatile int USED_PAPER_COUNT = 0;
    public static volatile boolean IS_PRINTER_ALLOCATED = false;
    public static volatile int PAPER_COUNT = 250;
    public static volatile boolean IS_PAPER_FULLED = false;
    public static volatile boolean IS_CARTRIDGE_FULLED = false;

    public static volatile boolean IS_STUDENT_PRINT = false;


    @Override
    public void run() {

    }

    public void print() {
        synchronized (this) {

            for (int i = 1; i <= PAPER_COUNT; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + " Document printed");
//                System.out.println(Thread.currentThread().getName() + " : " + i + " Document printed" + USED_PAPER_COUNT);
                USED_PAPER_COUNT++;
                if (USED_PAPER_COUNT % 10 == 0) {
                    USED_CARTRIDGE_COUNT++;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (USED_CARTRIDGE_COUNT == MAX_CARTRIDGE_COUNT || USED_PAPER_COUNT == MAX_PAPER_TRAY_COUNT) {
                    if (USED_CARTRIDGE_COUNT == MAX_CARTRIDGE_COUNT) {
                        try {
//                            IS_CARTRIDGE_FULLED = false;
//                            this.notifyAll();
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (USED_PAPER_COUNT == MAX_PAPER_TRAY_COUNT) {
                        try {
//                            IS_PAPER_FULLED = false;
//                            this.notifyAll();
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (IS_CARTRIDGE_FULLED || IS_PAPER_FULLED) {
//                IS_PRINTER_ALLOCATED = false;
//            }
        }
    }
}
