package ru.vsu.entity;

import java.util.Random;

public class ClientCreator implements Runnable {
    Random random = new Random(System.currentTimeMillis());
    String operation[] = {"withdraw", "deposit"};
    Cashier[] pool;

    public ClientCreator(Cashier[] pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true) {
            int minClients = Integer.MAX_VALUE;
            int cashierWithMinClient = 0;
            for (int i = 0; i < pool.length; i++) {
                if (pool[i].getQueue().size() < minClients) {
                    minClients = pool[i].getQueue().size();
                    cashierWithMinClient = i;
                }
            }
            Client client = new Client(operation[random.nextInt(operation.length)],
                    random.nextDouble() * 10000,
                    random.nextInt(10));
            pool[cashierWithMinClient].getQueue().add(client);
            System.out.println("New client added to cashier " + client);
            try {
                Thread.sleep(random.nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
