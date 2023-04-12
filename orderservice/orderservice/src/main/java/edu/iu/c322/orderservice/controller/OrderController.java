package edu.iu.c322.orderservice.controller;

import edu.iu.c322.orderservice.model.dto.*;
import edu.iu.c322.orderservice.model.entity.*;
import edu.iu.c322.orderservice.model.OrderUpdate;
import edu.iu.c322.orderservice.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {


    /*
    private OrderRepository repository;
    private final ShippingRepository shippingRepository;
    private BillingAddressRepository billingAddressRepository;

    // this is bad it is binding this class the customerRepository so instead we use spring dependency injection

    public CustomerController() {
        this.repository = new CustomerRepository();
    }


    //this is dpeendency injection
    public OrderController(OrderRepository repository,
                           ShippingRepository shippingRepository, BillingAddressRepository billingAddressRepository) {
        this.repository = repository;
        this.shippingRepository = shippingRepository;
        this.billingAddressRepository = billingAddressRepository;
    }


     */

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private BillingAddressRepository billingAddressRepository;


    @Autowired
    private PaymentRepository paymentMethodRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ReturnRepository returnRepository;


    @GetMapping("/{id}")
    public ResponseEntity<List<OrderDto>> findByCustomerId(@PathVariable int id){

        List<Order> orders = orderRepository.findByCustomerId(id);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<OrderDto> orderDtos = orders.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(orderDtos);

    }
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDto> findByOrderId(@PathVariable int id){

        Order orders = orderRepository.findByOrderId(id);
        if (orders == null) {
            return ResponseEntity.notFound().build();
        }
        OrderDto orderDtos = convertToDto(orders);
        return ResponseEntity.ok(orderDtos);

    }


    private OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotal(order.getTotal());

        Shipping shippingAddress = shippingRepository.findById(order.getShippingAddress().getId()).orElse(null);
        if (shippingAddress != null) {
            ShippingDto shippingAddressDto = new ShippingDto();
            // Set the fields for the shippingAddressDto
            shippingAddressDto.setState(shippingAddress.getState());
            shippingAddressDto.setCity(shippingAddress.getCity());
            shippingAddressDto.setPostalCode(shippingAddress.getPostalCode());
            orderDto.setShippingAddress(shippingAddressDto);
        }

        List<OrderItems> orderItems = orderItemRepository.findByOrderId(order.getOrderId());
        List<ItemDto> itemDtos = new ArrayList<>();
        for (OrderItems orderItem : orderItems) {
            Item item = itemRepository.findById(orderItem.getItem().getId()).orElse(null);
            if (item != null) {
                ItemDto itemDto = new ItemDto();
                itemDto.setName(item.getName());
                itemDto.setPrice(item.getPrice());
                itemDto.setQuantity(orderItem.getQuantity()); // Set the quantity from the orderItem table
                itemDtos.add(itemDto);
            }
        }
        orderDto.setItems(itemDtos);

        Payment paymentMethod = paymentMethodRepository.findById(order.getPaymentMethodId().getId()).orElse(null);
        if (paymentMethod != null) {
            BillingAddress billingAddress = billingAddressRepository.findById(paymentMethod.getBillingAddress().getId()).orElse(null);
            if (billingAddress != null) {
                BillingAddressDto billingAddressDto = new BillingAddressDto();
                // Set the fields for the billingAddressDto
                billingAddressDto.setState(billingAddress.getState());
                billingAddressDto.setCity(billingAddress.getCity());
                billingAddressDto.setPostalCode(billingAddress.getPostalCode());

                PaymentDto paymentMethodDto = new PaymentDto();
                // Set the fields for the paymentMethodDto
                paymentMethodDto.setMethod(paymentMethod.getMethod());
                paymentMethodDto.setNumber(paymentMethod.getCardNumber());
                paymentMethodDto.setBillingAddress(billingAddressDto);
                orderDto.setPayment(paymentMethodDto);
            }
        }
        return orderDto;
    }


    //@valid tells spring to ensure validations are checked, our validation is currently in the customer class
    @ResponseStatus(HttpStatus.CREATED)

    @PostMapping
    public int create(@Valid @RequestBody OrderDto orderDto){

        // Save the shipping address
        Shipping shippingAddress = new Shipping(orderDto.getShippingAddress());
        Shipping savedShippingAddress = shippingRepository.save(shippingAddress);

        // Save the payment method and billing address
        BillingAddress billingAddress = new BillingAddress(orderDto.getPayment().getBillingAddress());
        BillingAddress savedBillingAddress = billingAddressRepository.save(billingAddress);

        Payment paymentMethod = new Payment(orderDto.getPayment(), savedBillingAddress);
        Payment savedPaymentMethod = paymentMethodRepository.save(paymentMethod);

        // Save the order
        Order order = new Order(orderDto, savedShippingAddress, savedPaymentMethod);
        Order addedOrder = orderRepository.save(order);

        // Save the order items
        for (ItemDto items : orderDto.getItems()) {
            // Assuming you have an itemRepository for fetching existing items by ID
            Item item = new Item(items);
            Item savedItem = itemRepository.save(item);

            OrderItems orderItem = new OrderItems(addedOrder, savedItem);
            orderItem.setQuantity(items.getQuantity());
            orderItemRepository.save(orderItem);

        }

        return addedOrder.getOrderId();
    }


    // PUT lcoalhost:8080.customers/2
    @PutMapping("/return")
    public ResponseEntity<String> update(@Valid @RequestBody ReturnDto returnDto){

        Order order = orderRepository.findById(returnDto.getOrderId()).orElse(null);
        if (order == null) {
            return ResponseEntity.badRequest().body("Order id is not valid");
        }

        Item item = itemRepository.findById(returnDto.getItemId()).orElse(null);
        if (item == null) {
            return ResponseEntity.badRequest().body("Item id is not valid");
        }

        // Check if the item exists in the given order
        OrderItems orderItem = orderItemRepository.findByOrderAndItem(order, item);


        // Save the return information
        Return itemReturn = new Return(order, item, returnDto);
        returnRepository.save(itemReturn);

        // Delete the item from the order
        orderItemRepository.delete(orderItem);

        String complete = "update completed please find by order to check the changes";
        return ResponseEntity.ok(complete);

    }



    //path variables shown in url
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        Order order = orderRepository.findById(id).orElse(null);

        Shipping shipping = shippingRepository.findById(order.getShippingAddress().getId()).orElse(null);

        Payment payment = paymentMethodRepository.findById(order.getPaymentMethodId().getId()).orElse(null);

        BillingAddress billing = billingAddressRepository.findById(payment.getBillingAddress().getId()).orElse(null);



        Return returns = returnRepository.findByOrderId(id);

        List<OrderItems> orderItems = orderItemRepository.findAllByOrderId(id);
        orderItemRepository.deleteAll(orderItems);
        shippingRepository.delete(shipping);
        if(returns != null) {
            returnRepository.delete(returns);
        }
        billingAddressRepository.delete(billing);
        paymentMethodRepository.delete(payment);



        orderRepository.delete(order);
    }






}