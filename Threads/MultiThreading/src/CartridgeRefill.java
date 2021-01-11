public class CartridgeRefill extends Thread {

    public void cartridgeRefilling() {
        synchronized (this) {
            Printer.USED_CARTRIDGE_COUNT = 0;
            try {
                System.out.println("Cartridge Refilling");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            Printer.IS_CARTRIDGE_FULLED = true;
            this.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (Printer.USED_CARTRIDGE_COUNT == Printer.MAX_CARTRIDGE_COUNT) {
                cartridgeRefilling();
            }
        }

    }

}
