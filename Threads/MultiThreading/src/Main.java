public class Main {
    public static void main(String[] args) {

        Thread printer = new Printer();
        printer.start();

        Thread cartridgeRefill = new CartridgeRefill();
        cartridgeRefill.start();

        Thread paperRefill = new PaperRefill();
        paperRefill.start();

        for (int i = 1; i <= 5; i++) {
            Thread student = new Student((Printer) printer);
            student.setName("Student " + i);
            student.start();
        }
    }
}
