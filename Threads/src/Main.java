public class Main {
    public static void main(String[] args) {


        Printer printer = new Printer();
        printer.start();

        Thread student1 = new Student(printer);
        student1.setName("student 1");
        student1.start();

        Thread student2 = new Student(printer);
        student2.setName("student 2");
        student2.start();

        Thread student3 = new Student(printer);
        student3.setName("student 3");
        student3.start();

        Thread student4 = new Student(printer);
        student4.setName("student 4");
        student4.start();

        Thread student5 = new Student(printer);
        student5.setName("student 5");
        student5.start();

    }
}
