public class PaperRefill extends Thread {

    public void paperRefilling() {
        synchronized (this) {
            Printer.USED_PAPER_COUNT = 0;
            try {
                System.out.println("Paper Refilling .....");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.notify();
        }
    }


    @Override
    public void run() {
        while (true) {
            if (Printer.USED_PAPER_COUNT == Printer.MAX_PAPER_TRAY_COUNT) {
                paperRefilling();
            }
        }
    }
}
