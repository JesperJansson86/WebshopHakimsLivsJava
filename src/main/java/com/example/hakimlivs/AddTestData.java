package com.example.hakimlivs;

import com.example.hakimlivs.models.*;
import com.example.hakimlivs.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class AddTestData implements CommandLineRunner {
    @Autowired
    AddressRepository adressRepo;
    @Autowired
    AreaCodeRepository areaCodeRepo;
    @Autowired
    BrandRepository brandRepo;
    @Autowired
    CategoryRepository categoryRepo;
    @Autowired
    CityRepository cityRepo;
    @Autowired
    ContentRepository contentRepo;
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    DeliveryOptionRepository deliveryOptionRepo;
    @Autowired
    ImageRepository imageRepo;
    @Autowired
    Order_ContainsRepository order_containsRepo;
    @Autowired
    OrdersRepository ordersRepo;
    @Autowired
    OrderStatusRepository orderStatusRepo;
    @Autowired
    ProductRepository productRepo;
    @Autowired
    StoreRepository storeRepo;
    @Autowired
    UnitRepository unitRepo;

    @Override
    public void run(String... args) throws Exception {
        try {
            addCities();
        }catch (Exception e){

        }
    }

    private void addCities(){
        City c1 = new City("Stockholm");
        City c2 = new City("Kista");
        City c3 = new City("Märsta");
        City c4 = new City("Saltsjöbaden");
        City c5 = new City("Hägersten");
        c1.setId(1L);
        c2.setId(2L);
        c3.setId(3L);
        c4.setId(4L);
        c5.setId(5L);
    }

    private void force(){}
}
