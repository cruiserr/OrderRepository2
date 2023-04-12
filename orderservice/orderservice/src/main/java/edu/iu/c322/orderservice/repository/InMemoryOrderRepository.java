package edu.iu.c322.orderservice.repository;

import edu.iu.c322.orderservice.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public class InMemoryOrderRepository{

    /*



        //crud
        //create
        //update
        //delete

        private List<Order> orders = new ArrayList<>();

        public Order findOrdersByCustomerId(int id){
            return getOrderByCustomerId(id);

        }

        public int create(Order order){
            int id = orders.size() + 1;
            order.setCustomerId(id);
            List<Item> items = order.getItems();
            double orderTotal = 0;
            int itemCounter = 0;
            for (Item x: items){
                double quantity = x.getQuantity();
                double price = x.getPrice();
                double total = quantity * price;
                orderTotal += total;

                itemCounter++;
                x.setItemId(itemCounter);
            }

            order.setTotal(orderTotal);

            order.setShippingAddress(order.getShippingAddress());
            Payment pay = order.getPayment();
            BillingAddress billingAddress = pay.getBillingAddress();
            pay.setBillingAddress(billingAddress);

            orders.add(order);
            return id;
        }



        public void update(OrderUpdate request){

            int orderId = request.getOrderId();
            int itemId = request.getItemId();
            Order x = getOrderById(orderId);
            if(x != null){
                List<Item> items = x.getItems();
                for (Item p: items){
                    if (p.getItemId() == itemId){
                        items.remove(p);

                    }
                }
                int orderTotal = 0;
                for (Item p: items){
                    int quantity = p.getQuantity();
                    int price = p.getPrice();
                    int total = quantity * price;
                    orderTotal += total;
                }
                x.setTotal(orderTotal);
            }else{
                throw new IllegalStateException("order id is not valid.");
            }


        }

        public void delete(int id){
            Order x = getOrderByCustomerId(id);
            if(x != null){
                orders.remove(x);
            }else{
                throw new IllegalStateException("order id is not valid.");
            }

        }

        private Order getOrderByCustomerId(int id){
            return orders.stream().filter(x -> x.getCustomerId() == id).findAny().orElse(null);
        }


     */
    }

