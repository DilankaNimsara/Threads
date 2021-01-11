public class Printer {


    public static volatile boolean isAllocated = false;
    public static volatile int printedPaperCount = 0;
    public static volatile int printedCartridgeCount = 0;
    public static volatile int paperCount = 250;
    public volatile int MAX_TRAY_PAPER_COUNT = 250;
    public volatile int MAX_INK_CART_COUNT = 50;
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
                //for (int i = 0; i <10 ; i++) {
                System.out.println("Cartridge Refilled.!");
                //}

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            isCartridgeIsFulled = true;
            this.notifyAll();
        }
    }

    public void papersRefilling() {
        synchronized (this) {
            printedPaperCount = 0;
            try {
                // for (int i = 0; i <10 ; i++) {
                System.out.println("Papers Refilled.!");
                //}

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isPaperIsFulled = true;
            this.notifyAll();
        }
    }


    public void print() {

        synchronized (this) {

            System.out.println("called by thread" + Thread.currentThread().getName());
            isAllocated = true;
            for (int i = 1; i <= paperCount; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + " Document printed.");
                System.out.println(Thread.currentThread().getName() + " : " + i + " Document printed." + printedPaperCount);

                printedPaperCount++;
                if (i % 10 == 0) {
                    ++printedCartridgeCount;
                }
                while (printedCartridgeCount == MAX_INK_CART_COUNT || printedPaperCount == MAX_TRAY_PAPER_COUNT) {
                    if (printedCartridgeCount == MAX_INK_CART_COUNT) {
                        try {
                            isCartridgeIsFulled = false;
                            // this.notifyAll();
                            //printedCartridgeCount = 0;
                            isAllocated = true;
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (printedPaperCount == MAX_TRAY_PAPER_COUNT) {
                        try {
                            isPaperIsFulled = false;
                            //this.notifyAll();
                            //printedPaperCount = 0;
                            isAllocated = true;
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }


            }
            isAllocated = false;


        }
    }

}
