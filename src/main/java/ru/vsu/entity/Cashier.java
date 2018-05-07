package ru.vsu.entity;

import java.util.concurrent.PriorityBlockingQueue;

public class Cashier extends Thread {
    private PriorityBlockingQueue<Client> queue = new PriorityBlockingQueue();

    CashDeskEntity deskEntity;

    public Cashier(CashDeskEntity deskEntity) {
        this.deskEntity = deskEntity;
    }

    public PriorityBlockingQueue<Client> getQueue() {
        return queue;
    }

    public void setQueue(PriorityBlockingQueue<Client> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start");
        while (true) {
            Client client = queue.poll();
            if (client != null) {
                if (client.getOperation().equals("withdraw")) {

                    try {
                        Thread.sleep(client.getTime_in_seconds() * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    withdraw(client.getAmount());
                }
                if (client.getOperation().equals("deposit")) {

                    try {
                        Thread.sleep(client.getTime_in_seconds() * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    deposit(client.getAmount());
                }
            }
        }
    }

    private void withdraw(double amount) {
        System.out.println(getName() + " " + deskEntity.getBalance() + " before withdraw");
        synchronized (deskEntity) {
            if (deskEntity.getBalance() > amount)
                deskEntity.setBalance(deskEntity.getBalance() - amount);
            else System.out.println("Not enough money");
        }
        System.out.println("withdraw = " + amount);
        System.out.println(getName() + " " + deskEntity.getBalance() + " after withdraw");
    }

    private void deposit(double amount) {
        System.out.println(getName() + " " + deskEntity.getBalance() + " before deposit");
        synchronized (deskEntity) {
            deskEntity.setBalance(deskEntity.getBalance() + amount);
        }
        System.out.println("deposit = " + amount);
        System.out.println(getName() + " " + deskEntity.getBalance() + " after deposit");
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "queue=" + queue;
    }
}
