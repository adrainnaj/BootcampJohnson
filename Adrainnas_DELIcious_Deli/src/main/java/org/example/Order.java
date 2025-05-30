package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String customerName;
    private List<OrderItem> items;
  //  private List<Sandwich> sandwiches;
   // private List<Drink> drinks;




    public Order() {
        this.items = new ArrayList<>();


    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void addItemToOrder(OrderItem item){
        this.items.add(item);
    }

    public double getOrderTotal(){
        double total = 0;

        for(OrderItem item: items){
            total += item.getCostOfItem();
        }

        return total;
    }
}
