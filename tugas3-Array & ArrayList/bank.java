package tugas3;

public class bank {

    private customer[] customers;
    private int numberOfCustomers = 0;

    public bank() {
        customers = new customer[10];
    }

    public void addCustomer(String first, String last) {
        customers[numberOfCustomers] = new customer(first, last);
        numberOfCustomers++;
    }

    public int getNumOfCustomers() {
        return numberOfCustomers;
    }

    public customer getCustomer(int i) {
        return customers[i];
    }
}