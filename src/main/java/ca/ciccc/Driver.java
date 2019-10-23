package ca.ciccc;

public class Driver {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("238949", "Yuki", 0, true);
        BankAccount acc2 = new BankAccount("238949", "Tom", 500, true);
        BankAccount acc3 = new BankAccount("238949", "Thomson", -999, true);

        System.out.println("-----toString()-----");
        System.out.println(acc1.toString());
        System.out.println(acc2.toString());
        System.out.println(acc3.toString());

        System.out.println("-----deposit-----");
        acc1.deposit(10);
        acc1.deposit(50);
        acc1.deposit(10);
        acc1.deposit(70);
        System.out.println(acc1.transactionFee(5));
        System.out.println(acc1.transactionFee(10));
        System.out.println(acc1.toString());

        System.out.println("-----transfer-----");
        acc1.transfer(50, acc2);
        System.out.println(acc1);
        System.out.println(acc2);
    }
}
