package com.example.hakimlivs;

import com.example.hakimlivs.models.*;
import com.example.hakimlivs.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddTestData implements CommandLineRunner {
    @Autowired
    AddressRepository addressRepo;
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
            addAreaCodes();
            addAddresses();
            addCustomers();
            addBrands();
            addUnits();
            addCategories();
            addOrderStatus();
            addDeliveryOptions();
        } catch (Exception e) {

        }
    }

    private void addCities() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Stockholm"));
        cities.add(new City("Kista"));
        cities.add(new City("Märsta"));
        cities.add(new City("Saltsjöbaden"));
        cities.add(new City("Hägersten"));
        for (int i = 0, citiesSize = cities.size(); i < citiesSize; i++) {
            Long id = i + 1L;
            cities.get(i).setId(id);
        }
        cityRepo.saveAll(cities);
    }

    private void addAreaCodes() {
        List<AreaCode> areaCodes = new ArrayList<>();
        areaCodes.add(new AreaCode("11645", cityRepo.findById(1L).get()));
        areaCodes.add(new AreaCode("16434", cityRepo.findById(2L).get()));
        areaCodes.add(new AreaCode("19550", cityRepo.findById(3L).get()));
        areaCodes.add(new AreaCode("13333", cityRepo.findById(4L).get()));
        areaCodes.add(new AreaCode("12938", cityRepo.findById(5L).get()));
        areaCodes.add(new AreaCode("11160", cityRepo.findById(1L).get()));
        for (int i = 0; i < areaCodes.size(); i++) {
            Long id = i + 1L;
            areaCodes.get(i).setId(id);
        }
        areaCodeRepo.saveAll(areaCodes);
    }

    private void addAddresses() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("Stadsgårdshamnen 22", areaCodeRepo.findById(1L).get()));
        addresses.add(new Address("Nidarosgatan 15", areaCodeRepo.findById(2L).get()));
        addresses.add(new Address("Valsta Gårdsväg 4", areaCodeRepo.findById(3L).get()));
        addresses.add(new Address("Vikingavägen 8", areaCodeRepo.findById(4L).get()));
        addresses.add(new Address("Väderkvarnsgatan 129", areaCodeRepo.findById(5L).get()));
        addresses.add(new Address("Klustertorget 2", areaCodeRepo.findById(5L).get()));
        addresses.add(new Address("Katarinavägen 19", areaCodeRepo.findById(1L).get()));
        addresses.add(new Address("Kammakargatan 30", areaCodeRepo.findById(6L).get()));
        addresses.add(new Address("Holländargatan 21", areaCodeRepo.findById(6L).get()));
        for (int i = 0; i < addresses.size(); i++) {
            Long id = i + 1L;
            addresses.get(i).setId(id);
        }
        addressRepo.saveAll(addresses);
    }

    //Missing phone numbers for now.
    private void addCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Jane", "Andresson", addressRepo.findById(1L).get(), "jabari45@example.org", "password", true, false));
        customers.add(new Customer("Mark", "Browall", addressRepo.findById(2L).get(), "mallie.abbott@example.org", "password", true, false));
        customers.add(new Customer("Ann", "Cavallin", addressRepo.findById(3L).get(), "lpouros@example.com", "password", false, false));
        customers.add(new Customer("Rid", "Deichmann", addressRepo.findById(4L).get(), "rath.felicity@example.net", "password", true, false));
        customers.add(new Customer("Berit", "Engquist", addressRepo.findById(5L).get(), "sim.heaney@example.com", "password", true, false));
        customers.add(new Customer("Per", "Fisk", addressRepo.findById(6L).get(), "ziemann.lucinda@example.net", "password", false, false));
        customers.add(new Customer("Nora", "Guldstrand", addressRepo.findById(7L).get(), "eichmann.daisha@example.com", "password", false, false));
        customers.add(new Customer("Matilda", "Hartelius", addressRepo.findById(8L).get(), "ykoss@example.net", "password", true, false));
        customers.add(new Customer("Monica", "Irmlev", addressRepo.findById(8L).get(), "huel.felicity@example.org", "password", false, false));
        customers.add(new Customer("Caroline", "Johansson", addressRepo.findById(1L).get(), "marley.doyle@example.com", "password", false, false));
        customers.add(new Customer("Hakim", "Knöppel", addressRepo.findById(9L).get(), "greenfelder.brandy@example.com", "password", true, true));
        for (int i = 0; i < customers.size(); i++) {
            Long id = i + 1L;
            customers.get(i).setId(id);
        }
        customerRepo.saveAll(customers);
    }

    private void addBrands() {
        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand("Göteborgs"));
        brands.add(new Brand("Linkosuo"));
        brands.add(new Brand("Semper"));
        brands.add(new Brand("Axa"));
        brands.add(new Brand("Pot noodle"));
        brands.add(new Brand("GoGreen"));
        brands.add(new Brand("Zeta"));
        brands.add(new Brand("Premium Life"));
        brands.add(new Brand("Vitamin well"));
        brands.add(new Brand("Redbull"));
        brands.add(new Brand("Fun Light"));
        brands.add(new Brand("OLW"));
        brands.add(new Brand("Fruit-tella"));
        brands.add(new Brand("Marabou"));
        brands.add(new Brand("Bubs"));
        brands.add(new Brand("Softlan"));
        brands.add(new Brand("Ninjaplast"));
        brands.add(new Brand("Wettex"));
        brands.add(new Brand("Ajax"));
        brands.add(new Brand("Pepsodent"));
        brands.add(new Brand("Palmolive"));
        brands.add(new Brand("Love Beauty & Planet"));
        for (int i = 0; i < brands.size(); i++) {
            Long id = i + 1L;
            brands.get(i).setId(id);
        }
        brandRepo.saveAll(brands);
    }

    private void addUnits() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit("ml", "milliliter"));
        units.add(new Unit("cl", "centiliter"));
        units.add(new Unit("l", "liter"));
        units.add(new Unit("g", "gram"));
        units.add(new Unit("hg", "hektogram"));
        units.add(new Unit("kg", "kilogram"));
        units.add(new Unit("st", "styck"));
        units.add(new Unit("fpr", "förpackning"));
        for (int i = 0; i < units.size(); i++) {
            Long id = i + 1L;
            units.get(i).setId(id);
        }
        unitRepo.saveAll(units);
    }

    private void addCategories(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Bröd & kakor"));
        categories.add(new Category("Skafferi"));
        categories.add(new Category("Dryck"));
        categories.add(new Category("Godis & snacks"));
        categories.add(new Category("Hem & hushåll"));
        categories.add(new Category("Hygien & apotek"));
        for (int i = 0; i < categories.size(); i++) {
            Long id = i + 1L;
            categories.get(i).setId(id);
        }
        categoryRepo.saveAll(categories);
    }

    private void addOrderStatus(){
        List<OrderStatus> orderStatuses = new ArrayList<>();
        orderStatuses.add(new OrderStatus("Ny"));
        orderStatuses.add(new OrderStatus("Plock"));
        orderStatuses.add(new OrderStatus("Skickad"));
        for (int i = 0; i < orderStatuses.size(); i++) {
            Long id = i + 1L;
            orderStatuses.get(i).setId(id);
        }
        orderStatusRepo.saveAll(orderStatuses);
    }

    private void addDeliveryOptions(){
        List<DeliveryOption> deliveryOptions = new ArrayList<>();
        deliveryOptions.add(new DeliveryOption("Hämtas i butik", 0));
        deliveryOptions.add(new DeliveryOption("Levereras till kund", 39));
        deliveryOptions.add(new DeliveryOption("Fri leverans", 0));
        for (int i = 0; i < deliveryOptions.size(); i++) {
            Long id = i + 1L;
            deliveryOptions.get(i).setId(id);
        }
        deliveryOptionRepo.saveAll(deliveryOptions);
    }
}
