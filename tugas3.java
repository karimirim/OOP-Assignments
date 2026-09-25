package tugas3;

import java.util.Scanner;

public class tugas3 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        bank bank = new bank();
        String x = "John";
        String y = "Doe";
        account acct0 = new account(125000);
        bank.addCustomer(x, y);
        bank.getCustomer(bank.getNumOfCustomers() - 1).setAccount(acct0);

        System.out.println("\n#### BANK MERAH PUTIH ####");
        System.out.print("Your first Name: ");
        String f = input.nextLine();
        System.out.print("Your last Name: ");
        String l = input.nextLine();

        account acct1 = new account(50000);
        bank.addCustomer(f, l);
        bank.getCustomer(bank.getNumOfCustomers() - 1).setAccount(acct1);

        System.out.println("New? enjoy your free 50000 balance");
        System.out.println("Press Enter to continue...");
        input.nextLine();
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Option: ");
            char opt = input.nextLine().charAt(0);
            if (opt == '1') {

                System.out.print("Mau depo brp: ");
                int amount = input.nextInt();
                input.nextLine();

                bank.getCustomer(bank.getNumOfCustomers() - 1)
                        .getAccount()
                        .deposit(amount);

                System.out.println("Deposit berhasil!");
                System.out.println("Press Enter to continue...");
                input.nextLine();

            } else if (opt == '2') {

                System.out.print("Mau tarik brp: ");
                int amount = input.nextInt();
                input.nextLine();

                bank.getCustomer(bank.getNumOfCustomers() - 1)
                        .getAccount()
                        .withdraw(amount);

                System.out.println("Withdraw berhasil!");
                System.out.println("Press Enter to continue...");
                input.nextLine();

            } else if (opt == '3') {

                System.out.println(
                        "Balance: " +
                                bank.getCustomer(bank.getNumOfCustomers() - 1)
                                        .getAccount()
                                        .getBalance());

                System.out.println("Press Enter to continue...");
                input.nextLine();

            } else if (opt == '4') {

                System.out.println("Thank you for using our service");
                break;

            } else {

                System.out.println("Invalid option");
                System.out.println("Press Enter to continue...");
                input.nextLine();
            }
        }
        input.close();
    }
}
