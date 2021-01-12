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

//        Thread student1 = new Student((Printer) printer);
//        student1.setName("Student 1");
//        student1.start();
//        Thread student2 = new Student((Printer) printer);
//        student2.setName("Student 2");
//        student2.start();
//        Thread student3 = new Student((Printer) printer);
//        student3.setName("Student 3");
//        student3.start();
//        Thread student4 = new Student((Printer) printer);
//        student4.setName("Student 4");
//        student4.start();
//        Thread student5 = new Student((Printer) printer);
//        student5.setName("Student 5");
//        student5.start();
    }
}
