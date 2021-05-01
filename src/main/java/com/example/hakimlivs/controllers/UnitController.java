package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Unit;
import com.example.hakimlivs.repositories.UnitRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ("/api/unit"))
public class UnitController {

    @Autowired
    UnitRepository unitRepository;

    @GetMapping(path = "/add")
    public String addUnit(
            @RequestParam String unit,
            @RequestParam String longUnit
    ) {
        if (unitRepository.existsByUnit(unit)) {
            return "Unit with that unit already exists ";
        } else if (unitRepository.existsByLongUnit(longUnit)) {
            return "unit by that longUnit already exists ";
        } else {
            Unit u = new Unit(unit, longUnit);
            unitRepository.save(u);
            return String.format("%s %s has been added", unit, longUnit);
        }
    }

    @GetMapping(path = "/findById")
    public Unit getUnitById(@RequestParam long id) throws NotFoundException {
        if (unitRepository.findById(id).isPresent()) {
            return unitRepository.findById(id).get();
        } else {
            throw new NotFoundException(String.format("Item by that id:%s was not found", id));
        }
    }

    @GetMapping(path = "/deleteById")
    public String deleteProductById(@RequestParam long id) {
        if (unitRepository.findById(id).isPresent()) {
            unitRepository.deleteById(id);
            return String.format("Unit with id:%s has been deleted", id);
        } else return String.format("Unit by that id:%s did not exist and was therefore not deleted", id);
    }

    @GetMapping(path = "/all")
    public Iterable<Unit> getAllUnit() {
        return unitRepository.findAll();
    }

    @PostMapping("/update")
    public String updateUnit(@RequestParam Unit unit) throws NotFoundException {

        //TODO test update!
        if (unit.getId().equals(getUnitById(unit.getId()).getId())) {
            addUnit(unit.getUnit(), unit.getLongUnit());
            return "Created a new Unit!";
        } else {
            Unit updateUnit = getUnitById(unit.getId());
            updateUnit.setUnit(unit.getUnit());
            updateUnit.setLongUnit(unit.getLongUnit());
            return "Unit Updated!";
        }
    }
}