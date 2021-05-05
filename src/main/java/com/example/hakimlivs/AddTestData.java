package com.example.hakimlivs;

import com.example.hakimlivs.models.*;
import com.example.hakimlivs.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            for(City city: cityRepo.findAll()){
                return;
            }
            addCities();
            addAreaCodes();
            addAddresses();
            addCustomers();
            addBrands();
            addUnits();
            addCategories();
            addOrderStatus();
            addDeliveryOptions();
            addProducts();
            addImages();
            addOrders();
            addOrder_Contains();
        } catch (Exception e) {
            e.printStackTrace();
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

    private void addCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Jane", "Andresson", addressRepo.findById(1L).get(),"070-1740605", "jabari45@example.org", "password", true, false));
        customers.add(new Customer("Mark", "Browall", addressRepo.findById(2L).get(), "070-1740606","mallie.abbott@example.org", "password", true, false));
        customers.add(new Customer("Ann", "Cavallin", addressRepo.findById(3L).get(),"070-1740607", "lpouros@example.com", "password", false, false));
        customers.add(new Customer("Rid", "Deichmann", addressRepo.findById(4L).get(),"070-1740608", "rath.felicity@example.net", "password", true, false));
        customers.add(new Customer("Berit", "Engquist", addressRepo.findById(5L).get(),"070-1740609", "sim.heaney@example.com", "password", true, false));
        customers.add(new Customer("Per", "Fisk", addressRepo.findById(6L).get(),"070-1740610", "ziemann.lucinda@example.net", "password", false, false));
        customers.add(new Customer("Nora", "Guldstrand", addressRepo.findById(7L).get(),"070-1740611", "eichmann.daisha@example.com", "password", false, false));
        customers.add(new Customer("Matilda", "Hartelius", addressRepo.findById(8L).get(),"070-1740612", "ykoss@example.net", "password", true, false));
        customers.add(new Customer("Monica", "Irmlev", addressRepo.findById(8L).get(),"070-1740613", "huel.felicity@example.org", "password", false, false));
        customers.add(new Customer("Caroline", "Johansson", addressRepo.findById(1L).get(),"070-1740614", "marley.doyle@example.com", "password", false, false));
        customers.add(new Customer("Hakim", "Knöppel", addressRepo.findById(9L).get(), "070-1740615", "greenfelder.brandy@example.com", "password", true, true));
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

    //OBS Godislåda ska ha 1.5 i size istället för 2
    //OBS Sträng-längd max 255 tecken just nu, behöver ökas till.. mer @Size(max=?)
    private void addProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(
                "Ballerina Salted Caramel 10-pack",
                "Ballerina salted caramel är två spröda kex med len och krämig fyllning med salt kolasmak",
                69,
                33,
                10,
                190,
                brandRepo.findById(1L).get(),
                categoryRepo.findById(1L).get(),
                unitRepo.findById(4L).get(),
                true));
        products.add(new Product(
                "Råg crisps med gräddfil & dill",
                "Perfekt när du ska bjuda på tilltugg eller smårätter.",
                19,
                10,
                1,
                150,
                brandRepo.findById(2L).get(),
                categoryRepo.findById(1L).get(),
                unitRepo.findById(4L).get(),
                true));
        products.add(new Product(
                "Salta Kex Vallmofrö",
                "Glutenfria, salta kex med vallmofrö. Goda som snacks både med eller utan topping!",
                25,
                9,
                1,
                155,
                brandRepo.findById(3L).get(),
                categoryRepo.findById(1L).get(),
                unitRepo.findById(4L).get(),
                true));
        products.add(new Product(
                "Havregryn",
                "Nyckelhålsmärkta havregryn. Rika på fibrer, vitaminer och mineraler. Perfekta till bakningen eller gröten.",
                12,
                31,
                1,
                750,
                brandRepo.findById(4L).get(),
                categoryRepo.findById(2L).get(),
                unitRepo.findById(4L).get(),
                true));
        products.add(new Product(
                "Koppnudlar kyckling och svamp",
                "Snabblagade nudlar i kopp med smak av kyckling och svamp. Klar att ätas på några minuter.",
                12,
                19,
                1,
                90,
                brandRepo.findById(5L).get(),
                categoryRepo.findById(2L).get(),
                unitRepo.findById(4L).get(),
                true));
        products.add(new Product(
                "Eko Majs",
                "Ekologiska majs från GoGreen.",
                13,
                6,
                1,
                380,
                brandRepo.findById(6L).get(),
                categoryRepo.findById(2L).get(),
                unitRepo.findById(4L).get(),
                true));
        products.add(new Product(
                "Casa di Luca Pappardelle",
                "Pappardelle pasta gjord på durumvetemjöl. Ät med valfri pastasås eller ha i pastasalladen.",
                25,
                24,
                1,
                500,
                brandRepo.findById(7L).get(),
                categoryRepo.findById(2L).get(),
                unitRepo.findById(4L).get(),
                true));
        products.add(new Product(
                "Energidryck Mango 24-pack",
                "Bubblig energidryck med taurin & koffein.",
                99,
                45,
                24,
                250,
                brandRepo.findById(8L).get(),
                categoryRepo.findById(3L).get(),
                unitRepo.findById(1L).get(),
                true));
        products.add(new Product(
                "Dryck \"Celebrate Zero\"",
                "Vitamin Well Celebrate Zero är en kolsyrad och sockerfri dryck med smak av mango och ananas. Drycken innehåller vitamin D, B12 och zink.",
                12,
                17,
                1,
                355,
                brandRepo.findById(9L).get(),
                categoryRepo.findById(3L).get(),
                unitRepo.findById(1L).get(),
                true));
        products.add(new Product(
                "Eko Simply Cola 24-pack",
                "Red Bull Cola - en ekologiskt kolsyrad läsk med växtextrakt och naturligt koffein. Ger dig vingar när du behöver dem! Totalt 24 burkar.",
                199,
                50,
                24,
                250,
                brandRepo.findById(10L).get(),
                categoryRepo.findById(3L).get(),
                unitRepo.findById(1L).get(),
                true));
        products.add(new Product(
                "Saft Mojito",
                "Dryckeskoncentrat med smak av mojito - utan tillsatt socker samt med naturliga färger och aromer. Ger tio liter färdig dryck.",
                15,
                47,
                1,
                1,
                brandRepo.findById(11L).get(),
                categoryRepo.findById(3L).get(),
                unitRepo.findById(3L).get(),
                true));
        products.add(new Product(
                "Chips Ost, Tomat & Lök",
                "Bästisar i form av grovräfflade potatischips, stolt framröstade av kompisarna på Maskrosbarn. Smak av ost, tomat och lök.",
                10,
                2,
                1,
                250,
                brandRepo.findById(12L).get(),
                categoryRepo.findById(4L).get(),
                unitRepo.findById(4L).get(),
                true));
        products.add(new Product(
                "Hel låda Fruktkola Jordgubb",
                "Fruktkola med tydlig jordgubbssmak, här i ett 24-pack.",
                129,
                45,
                24,
                28,
                brandRepo.findById(13L).get(),
                categoryRepo.findById(4L).get(),
                unitRepo.findById(4L).get(),
                true));
        products.add(new Product(
                "Chokladask Aladdin",
//                "Aladdin- Vad vore julen utan Aladdin? Svenska folkets mest älskade pralinask kommer med både julglädje - och den oskrivna regeln att aldrig börja på det undre lagret, förrän det övre är slut. Aladdin är en klassiker och funnits i Marabous sortiment sedan 1939.",
                "Aladdin- Vad vore julen utan Aladdin? Svenska folkets mest älskade pralinask kommer med julglädje.",
                29,
                34,
                1,
                500,
                brandRepo.findById(14L).get(),
                categoryRepo.findById(4L).get(),
                unitRepo.findById(4L).get(),
                false));
        products.add(new Product(
                "Godislåda",
//                "Bubs godisfabrik är fantastiska på att tillverka godis. Men det händer faktiskt ibland att vissa godisar inte får helt perfekt form. Inga stora mängder, men både vi och Bubs anser att inget ska behöva slängas på grund av skönhetsfel. Därför säljer vi lådor med utmärkt, men inte helt perfekt godis. OBS: Vissa lådor är mer blandade än andra, det kan alltså förekomma en övervägande majoritet av en typ av godis, vissa lådor innehåller endast en sorts godis.",
                "Bubs godisfabrik är fantastiska på att tillverka godis. Men det händer faktiskt ibland att vissa godisar inte får helt perfekt form.",
                69,
                8,
                1,
                2, //OBS, ska vara 1.5 egentligen, Error nu då size för tillfället är int
                brandRepo.findById(15L).get(),
                categoryRepo.findById(4L).get(),
                unitRepo.findById(6L).get(),
                true));
        products.add(new Product(
                "Sköljmedel White Flower",
                "Sköljmedel för känslig hud med ingredienser från hållbara källor och naturligt ursprung. Formulan är också biolgiskt nedbrytbar. Dermatologiskt testad. ",
                8,
                28,
                1,
                650,
                brandRepo.findById(16L).get(),
                categoryRepo.findById(5L).get(),
                unitRepo.findById(1L).get(),
                true));
        products.add(new Product(
                "Avfallspåsar med Drawstring 35L",
//                "100 procent fummelfria soppåsar från Ninjaplast! Gjorda av 100 procent återvunnen plast. Påsarna är försedda med drawstring vilket gör det enkelt och fummelfritt att hantera hushållssoporna. Så snygga att du kommer att vilja använda dem till annat än sopor.Bli en Ninja i köket!",
                "100 procent fummelfria soppåsar från Ninjaplast! Gjorda av 100 procent återvunnen plast.",
                20,
                11,
                1,
                15,
                brandRepo.findById(17L).get(),
                categoryRepo.findById(5L).get(),
                unitRepo.findById(7L).get(),
                true));
        products.add(new Product(
                "Disktrasor 3-pack",
                "Disktrasor pink edition från Wettex, här i ett 3-pack.",
                13,
                17,
                1,
                3,
                brandRepo.findById(18L).get(),
                categoryRepo.findById(5L).get(),
                unitRepo.findById(7L).get(),
                true));
        products.add(new Product(
                "Rengöringsspray Stainless Steel",
                "Rengör effektivt och enkelt dina rostfria ytor med Ajax Specialist Stainless Steel rengöringsspray.",
                27,
                18,
                1,
                500,
                brandRepo.findById(19L).get(),
                categoryRepo.findById(5L).get(),
                unitRepo.findById(1L).get(),
                true));
        products.add(new Product(
                "Tandkräm Long Active White Fresh",
//                "Pepsodent Long Active White Fresh är en fluortandkräm berikad med zink. Pepsodent Long Actives formula med Pro-Time Zinc använder de naturliga antibakteriella egenskaperna hos zink för att motverka bildandet av plackbakterier i upp till 24 timmar.",
                "Pepsodent Long Active White Fresh är en fluortandkräm berikad med zink.",
                9,
                40,
                1,
                75,
                brandRepo.findById(20L).get(),
                categoryRepo.findById(6L).get(),
                unitRepo.findById(1L).get(),
                true));
        products.add(new Product(
                "Handtvål Kamelia",
                "Palmolive Naturals är en flytande handtvål med glycerin, som hjälper till att hålla huden återfuktad och hålla händer lena.Denna handtvål har en blommig doft av kameliaolja.",
                15,
                14,
                1,
                300,
                brandRepo.findById(21L).get(),
                categoryRepo.findById(6L).get(),
                unitRepo.findById(1L).get(),
                true));
        products.add(new Product(
                "Duschkräm Muru Muru Butter & Rose",
                "Ta hand om dig själv och njut av en uppfriskande dusch med den fina duschkrämen med doft av Muru Muru Butter & Rose.",
                39,
                29,
                1,
                500,
                brandRepo.findById(22L).get(),
                categoryRepo.findById(6L).get(),
                unitRepo.findById(1L).get(),
                true));
        products.add(new Product(
                "Tandborste Vertical Expert Soft",
                "Med denna mjuka tanborste rengör du tack vare det solfjäderformat borsthuvudet effektivt plack mellan tänderna.",
                15,
                23,
                1,
                1,
                brandRepo.findById(20L).get(),
                categoryRepo.findById(6L).get(),
                unitRepo.findById(7L).get(),
                true));
        for (int i = 0; i < products.size(); i++) {
            Long id = i + 1L;
            products.get(i).setId(id);
        }
        productRepo.saveAll(products);
    }

    private void addImages() {
        List<Image> images = new ArrayList<>();
        images.add(new Image("ballerina_salted_caramel_10-pack.png", productRepo.findById(1L).get()));
        images.add(new Image("linkosuo_rye_crisps_sourcream_dill.jpg", productRepo.findById(2L).get()));
        images.add(new Image("semper_crackers.jpg", productRepo.findById(3L).get()));
        images.add(new Image("axa_havregryn_750g_ny.jpg", productRepo.findById(4L).get()));
        images.add(new Image("pot_koppnudlar_kyckling_och_svamp_90g_.jpg", productRepo.findById(5L).get()));
        images.add(new Image("gog_majskorn_380g_eko_.jpg", productRepo.findById(6L).get()));
        images.add(new Image("ms121451-zet_casa_di_luca_pappardelle_500gjpg.jpg", productRepo.findById(7L).get()));
        images.add(new Image("ms121444-24-pack_lif_energy_mango_dryck_250mljpg.jpg", productRepo.findById(8L).get()));
        images.add(new Image("ms107352_dryck_celebrate_zero_355mljpg.jpg", productRepo.findById(9L).get()));
        images.add(new Image("redbullsimplycola24p.jpg", productRepo.findById(10L).get()));
        images.add(new Image("fun_light_mojito_ny.jpg", productRepo.findById(11L).get()));
        images.add(new Image("ms117663_olw_friendchips_ost_tomat_lok_250g_front.jpg", productRepo.findById(12L).get()));
        images.add(new Image("ms117631_24-pack_fru_mansikka_hedelmatoffee_vahemman_sokeria_28g.jpg", productRepo.findById(13L).get()));
        images.add(new Image("ms120733-mar_aladdin_500gjpg.jpg", productRepo.findById(14L).get()));
        images.add(new Image("godislada.png", productRepo.findById(15L).get()));
        images.add(new Image("ms120252_sof_softlan_skoljmedel_plant_based_white_flower_650_ml_650mljpg.jpg", productRepo.findById(16L).get()));
        images.add(new Image("ms120252_sof_softlan_skoljmedel_plant_based_white_flower_650_ml_650mljpg.jpg", productRepo.findById(17L).get()));
        images.add(new Image("wet_new_wetter_art_collection_pink_edition_2020_3-pack_3pcs_.jpg", productRepo.findById(18L).get()));
        images.add(new Image("ajax_stainless_steel.jpg", productRepo.findById(19L).get()));
        images.add(new Image("ms120613_pep_pepsodent_tk_75ml_long_act_white_fresh_75jpg.jpg", productRepo.findById(20L).get()));
        images.add(new Image("ms120250_pal_palmolive_flytande_handtval_camelia_300_ml_300mljpgjpg.jpg", productRepo.findById(21L).get()));
        images.add(new Image("lovebeautyandplanet.jpg", productRepo.findById(22L).get()));
        images.add(new Image("ms120611_pep_pepsodent_tb_vertical_expert_soft_x12_1pcsjpg.jpg", productRepo.findById(23L).get()));
        for (int i = 0; i < images.size(); i++) {
            Long id = i + 1L;
            images.get(i).setId(id);
        }
        imageRepo.saveAll(images);
    }

    private void addOrders(){
        List<Orders> orders = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        orders.add(new Orders(
                LocalDate.parse("2021-03-29 14:00:00", formatter),
                orderStatusRepo.findById(1L).get(),
                customerRepo.findById(1L).get(),
                deliveryOptionRepo.findById(1L).get(),
                null
        ));
        orders.add(new Orders(
                LocalDate.parse("2021-03-26 13:30:00", formatter),
                orderStatusRepo.findById(2L).get(),
                customerRepo.findById(3L).get(),
                deliveryOptionRepo.findById(2L).get(),
                addressRepo.findById(3L).get()
        ));
        orders.add(new Orders(
                LocalDate.parse("2021-03-25 10:45:00", formatter),
                orderStatusRepo.findById(3L).get(),
                customerRepo.findById(2L).get(),
                deliveryOptionRepo.findById(2L).get(),
                addressRepo.findById(2L).get()
        ));
        orders.add(new Orders(
                LocalDate.parse("2021-03-19 11:00:00", formatter),
                orderStatusRepo.findById(3L).get(),
                customerRepo.findById(4L).get(),
                deliveryOptionRepo.findById(1L).get(),
                null
        ));
        orders.add(new Orders(
                LocalDate.parse("2021-03-29 15:10:00", formatter),
                orderStatusRepo.findById(1L).get(),
                customerRepo.findById(4L).get(),
                deliveryOptionRepo.findById(1L).get(),
                null
        ));
        for (int i = 0; i < orders.size(); i++) {
            Long id = i + 1L;
            orders.get(i).setId(id);
        }
        ordersRepo.saveAll(orders);
    }

    private void addOrder_Contains(){
        List<Order_Contains> OCs = new ArrayList<>();
        //Order1
        OCs.add(new Order_Contains(
                ordersRepo.findById(1L).get(),
                productRepo.findById(2L).get(),
                1
                ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(1L).get(),
                productRepo.findById(6L).get(),
                2
                ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(1L).get(),
                productRepo.findById(8L).get(),
                1
                ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(2L).get(),
                productRepo.findById(18L).get(),
                2
                ));
        //Order2
        OCs.add(new Order_Contains(
                ordersRepo.findById(2L).get(),
                productRepo.findById(19L).get(),
                3
                ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(2L).get(),
                productRepo.findById(20L).get(),
                1
                ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(2L).get(),
                productRepo.findById(6L).get(),
                4
                ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(2L).get(),
                productRepo.findById(4L).get(),
                2
                ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(2L).get(),
                productRepo.findById(1L).get(),
                5
                ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(2L).get(),
                productRepo.findById(13L).get(),
                1
                ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(2L).get(),
                productRepo.findById(2L).get(),
                3
                ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(2L).get(),
                productRepo.findById(4L).get(),
                2
                ));
        //Order 3
        OCs.add(new Order_Contains(
                ordersRepo.findById(3L).get(),
                productRepo.findById(14L).get(),
                3
        ));
        //Order 4
        OCs.add(new Order_Contains(
                ordersRepo.findById(4L).get(),
                productRepo.findById(3L).get(),
                4
        ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(4L).get(),
                productRepo.findById(15L).get(),
                1
        ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(4L).get(),
                productRepo.findById(23L).get(),
                2
        ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(4L).get(),
                productRepo.findById(11L).get(),
                1
        ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(4L).get(),
                productRepo.findById(12L).get(),
                2
        ));
        //Order 5
        OCs.add(new Order_Contains(
                ordersRepo.findById(5L).get(),
                productRepo.findById(16L).get(),
                5
        ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(5L).get(),
                productRepo.findById(21L).get(),
                2
        ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(5L).get(),
                productRepo.findById(1L).get(),
                4
        ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(5L).get(),
                productRepo.findById(4L).get(),
                1
        ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(5L).get(),
                productRepo.findById(12L).get(),
                2
        ));
        OCs.add(new Order_Contains(
                ordersRepo.findById(5L).get(),
                productRepo.findById(2L).get(),
                3
        ));
        for (int i = 0; i < OCs.size(); i++) {
            Long id = i + 1L;
            OCs.get(i).setId(id);
        }
        order_containsRepo.saveAll(OCs);
    }
}
