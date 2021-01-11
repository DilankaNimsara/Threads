public class CartridgeRefill extends Thread {

    Printer printer;

    public CartridgeRefill(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        while (true) {
            if (printer.printedCartridgeCount == printer.MAX_INK_CART_COUNT) {
                printer.cartridgeRefilling();
            }
        }
    }


}
