public class Student extends Thread {

    Printer printer;

    public Student(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        while (true) {
           if (!Printer.isAllocated) {
               System.out.println("Came by" + this.getName());
                Printer.isAllocated = true;
                printer.print();
                return;
            }
        }


    }
}
