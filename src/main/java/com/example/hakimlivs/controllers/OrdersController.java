package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.*;
import com.example.hakimlivs.repositories.*;
import com.example.hakimlivs.services.CustomerService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = ("/api/orders"))
public class OrdersController {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    AreaCodeRepository areaCodeRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DeliveryOptionRepository deliveryOptionRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    Order_ContainsRepository order_containsRepository;
    @Autowired
    CustomerService customerService;

    //Inner class
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ProductResponse {
        Long product_id;
        int amount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class OrderResponse {
        String firstName;
        String lastName;
        String address;
        String areaCode;
        String city;
        String email;
        String phoneNumber;
        Long delivery_option_id;
        List<ProductResponse> products;
    }

    @PostMapping(path = "/add")
    public Message addOrdersByPost(@RequestBody OrderResponse newOrder) {
        //Här kontrollerar vi för varje produkt i beställningen ifall det finns nog i lager.
        for (var v : newOrder.products
        ) {
            Product product = productRepository.findById(v.getProduct_id()).get();
            int inventory = product.getInventory();
            if (inventory < v.getAmount()) {
                return new Message(
                        false,
                        String.format("Det finns inte nog utav %s i lagret (%d kvar)",
                                product.getTitle(),
                                product.getInventory()));
            }
        }

        Orders orders = new Orders();
        orders.setOrderDate(LocalDate.now());
        orders.setOrderStatus(orderStatusRepository.findById(1L).get());

        if (newOrder.getDelivery_option_id() == 1) {// Hämtas i butik 1
            orders.setDeliveryOption(deliveryOptionRepository.findById(1L).get());
            orders.setDeliveryAddress(null);
        } else if (newOrder.getDelivery_option_id() == 2 || newOrder.getDelivery_option_id() == 3) { // 2=Hemleverans 2, 3 = Fri frakt
            orders.setDeliveryOption(deliveryOptionRepository.findById(newOrder.delivery_option_id).get());
            Address tempAddress = new Address();
            AreaCode tempAreaCode = new AreaCode();
            City tempCity = new City();


            if (!cityRepository.existsByCity(newOrder.city)) {
                tempCity.setCity(newOrder.city);
                cityRepository.save(tempCity);
            }
            if (!areaCodeRepository.existsByAreaCode(String.valueOf(newOrder.areaCode))) {
                tempAreaCode.setAreaCode(String.valueOf(newOrder.areaCode));
                areaCodeRepository.save(tempAreaCode);
            }
            if (!addressRepository.existsByAddress(newOrder.address)) {
                tempAddress.setAddress(newOrder.address);
                addressRepository.save(tempAddress);
            }

            orders.setDeliveryAddress(addressRepository.findAddressByAddress(newOrder.address));
            tempCity = cityRepository.findCityBycity(newOrder.city);
            tempAreaCode = areaCodeRepository.findAreaCodeByareaCode(String.valueOf(newOrder.areaCode));
            tempAddress = addressRepository.findAddressByAddress(newOrder.address);
            tempAreaCode.setCity(tempCity);
            tempAddress.setAreaCode(tempAreaCode);
//            customer.setAddress(tempAddress);
            cityRepository.save(tempCity);
            areaCodeRepository.save(tempAreaCode);
            addressRepository.save(tempAddress);
            ordersRepository.save(orders);

        } else {
            return new Message(false, "Delivery Option not found");
        }

        if (customerRepository.existsByEmail(newOrder.email)) {
            Customer customer = customerRepository.findCustomerByEmail(newOrder.email);
            orders.setCustomer(customer);
            ordersRepository.save(orders);
        } else {
            customerService.addCustomer(newOrder.firstName,
                    newOrder.lastName,
                    newOrder.email,
                    "password",
                    false,
                    false,
                    newOrder.address,
                    String.valueOf(newOrder.areaCode),
                    newOrder.city);
            Customer customer = customerRepository.findCustomerByEmail(newOrder.email);
            orders.setCustomer(customer);
            ordersRepository.save(orders);
        }

        newOrder.products.forEach(orderedProduct -> {
            Order_Contains oc = new Order_Contains();
            oc.setOrder(orders);
            Product databaseProduct = productRepository.findById(orderedProduct.getProduct_id()).get();
            databaseProduct.setInventory(databaseProduct.getInventory() - orderedProduct.getAmount());
            productRepository.save(databaseProduct);
            oc.setProduct(databaseProduct);
            oc.setProductAmount(orderedProduct.getAmount());
            order_containsRepository.save(oc);
        });

        return new Message(true, "Order added");
    }

    @GetMapping(path = "/byId")
    public Orders getOrdersById(@RequestParam long id) throws NotFoundException {
        if (ordersRepository.findById(id).isPresent()) {
            return ordersRepository.findById(id).get();
        } else {
            throw new NotFoundException(String.format("Item by that id:%s was not found", id));
        }
    }

    @GetMapping(path = "post-info")
    public String getPostInfo() {
        return """
                  {
                        "products" : [
                            {
                                "product_id" : 1,
                                "amount": 2
                            },
                            {
                                "product_id" : 2,
                                "amount": 5
                            }
                        ],
                        "delivery_option_id" : 1,
                        "firstName": "Jane",
                        "lastName": "Andresson",
                        "address": "Stadsgårdshamnen 22",
                        "areaCode" : 11645,
                        "city": "Stockholm",
                        "email": "jabari45@example.org",
                        "phoneNumber": "070-1740605"
                    }
                """;
    }

    @GetMapping(path = "/deleteById")
    public String deleteOrdersById(@RequestParam long id) {
        if (ordersRepository.findById(id).isPresent()) {
            ordersRepository.deleteById(id);
            return String.format("Order with id:%s has been deleted", id);
        } else return String.format("Order by that id:%s did not exist and was therefore not deleted", id);
    }

    @GetMapping(path = "/all")
    public Iterable<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @GetMapping("/update")
    public Message updateOrders(@RequestParam Long id, @RequestParam long statusid) {
        try {
            if (!ordersRepository.existsById(id)) {
                return new Message(false, "Order does not exist");
            } else if (!orderStatusRepository.existsById(statusid))
                return new Message(false, "OrderstatusID does not exist");

            Orders existingOrders = ordersRepository.findById(id).get();
            existingOrders.setOrderStatus(orderStatusRepository.findById(statusid).get());
            ordersRepository.save(existingOrders);
            return new Message(true, String.format("%s updated", existingOrders.getId()));

        } catch (Exception e) {
            e.printStackTrace();
            return new Message(false, "Error when processing.");
        }
    }

    @GetMapping("/allByStatus")
    public Iterable<Orders> findOrdersByStatus(@RequestParam long id) {
        return ordersRepository.findOrdersByOrderStatus(orderStatusRepository.findById(id).get());
    }
}
