public class PaperRefill extends Thread {
    int refillCount = 0;
    Printer printer;

    public PaperRefill(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        while (true) {

            if (printer.printedPaperCount == printer.MAX_TRAY_PAPER_COUNT) {
                printer.papersRefilling();
            }
        }
    }

}
