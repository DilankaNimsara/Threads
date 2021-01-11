public class Printer extends Thread {


    public static volatile boolean isAllocated = false;
    public static volatile int printedPaperCount = 0;
    public static volatile int printedCartridgeCount = 0;
    public int MAX_TRAY_PAPER_COUNT = 250;
    public int MAX_INK_CART_COUNT = 50;
    public int paperCount = 250;
    public volatile boolean isPaperIsFulled = false;
    public volatile boolean isCartridgeIsFulled = false;

    public Printer() {
        new PaperRefill(this).start();
        new CartridgeRefill(this).start();
    }

    public void cartridgeRefilling() {
        synchronized (this) {
            printedCartridgeCount = 0;
            try {
                System.out.println("Cartridge Refilled.!");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            isCartridgeIsFulled = true;
            this.notify();
        }
    }

    public void papersRefilling() {
        synchronized (this) {
            printedPaperCount = 0;
            try {
                System.out.println("Papers Refilled.!");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isPaperIsFulled = true;
            this.notify();
        }
    }


    public void print() {
        synchronized (this) {
            for (int i = 1; i <= paperCount; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + " Document printed.");
                printedPaperCount++;
                if (i % 10 == 0) {
                    ++printedCartridgeCount;
                }
                while (printedCartridgeCount == MAX_INK_CART_COUNT || printedPaperCount == MAX_TRAY_PAPER_COUNT) {
                    if (printedCartridgeCount == MAX_INK_CART_COUNT) {
                        try {
                            isCartridgeIsFulled = false;
                            this.notifyAll();
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
//                    System.out.println(printedCartridgeCount + " =----------------------=");
                    if (printedPaperCount == MAX_TRAY_PAPER_COUNT) {
                        //System.out.println("***********************************************************");
                        try {
                            isPaperIsFulled = false;
                            this.notifyAll();
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(printedPaperCount + " =---------****************--------------=");
            }
            if (isPaperIsFulled || isCartridgeIsFulled) {
                isAllocated = false;
            }
        }
    }

}
