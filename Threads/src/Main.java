public class Main {
    public static void main(String[] args) throws InterruptedException {


        Printer printer = new Printer();


        Thread student1 = new Student(printer);
        student1.setName("student 1");
        student1.start();
        //student1.join();
        System.out.println("Came Here 1");

        Thread student2 = new Student(printer);
        student2.setName("student 2");
        student2.start();
//        student2.join();
        System.out.println("Came Here 2");


        Thread student3 = new Student(printer);
        student3.setName("student 3");
        student3.start();
//        student3.join();
        System.out.println("Came Here 3");


        Thread student4 = new Student(printer);
        student4.setName("student 4");
        student4.start();
//        student4.join();
        System.out.println("Came Here 4 ");


        Thread student5 = new Student(printer);
        student5.setName("student 5");
        student5.start();
//        student5.join();
        System.out.println("Came Here 5");


    }
}
