package org.bsnyder.java18.examples;

import java.util.function.Consumer;

class Customer {
    private String ssn;
    private Double balance;

    public Customer(String ssn, Double balance) {
        this.ssn = ssn;
        this.balance = balance;
    }

    public String getSsn() {
        return ssn;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}

class Bank {

    public void updateBalance(Customer customer, Consumer<Customer> consumer) {
        System.out.println("              SSN: " + customer.getSsn());
        System.out.println("Balance before tx: " + customer.getBalance());
        consumer.accept(customer);
        System.out.println(" Balance after tx: " + customer.getBalance());
    }
}

public class ConsumerDemo {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Customer customer = new Customer("000-00-0000", 5000.0);
        bank.updateBalance(customer, c -> c.setBalance(c.getBalance() + 2000.0));
        System.out.println("-----------------------");
        bank.updateBalance(customer, c -> c.setBalance(c.getBalance() - 500.0));
    }
}
