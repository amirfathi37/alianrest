package com.alianrest.alianrest.controller;

import com.alianrest.alianrest.entity.Alian;
import com.alianrest.alianrest.repo.AlianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class AlianController {

    @Autowired
    AlianRepo alianRepo;

    @GetMapping(path = "/version", produces = {"application/json"})
    public String home() {
        return "1.1";
    }

    @GetMapping(path = "/getAlian/{id}", produces = {"application/json"})
    @ResponseBody
    public Alian getAlian(@PathVariable("id") int id) {
        return alianRepo.findById(id).orElse(null);
    }

    @GetMapping(path = "/getAllAlians", produces = {"application/json"})
    public List<Alian> getAllAlians() {

        Iterable<Alian> all = alianRepo.findAll();
        List<Alian> alians = new ArrayList<>();
        all.forEach(alian -> {
            alians.add(alian);
        });
        return alians;
    }


    @PostMapping(value = "/saveAlian", produces = {"application/json"})
    public Alian getAlian(@RequestBody Alian alian) {
        Alian save = alianRepo.save(alian);
        return save;
    }

    @DeleteMapping(value = "/deleteAlian", produces = {"application/json"})
    public String deleteAlian(@PathVariable("id") int deleteId) {
        try {
            alianRepo.delete(alianRepo.findById(deleteId).orElse(new Alian()));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        System.out.println("deleted.");
        return "Deleted";
    }

    @GetMapping("/getAliansByTech")
    public List<Alian> getAlianByTech(@RequestParam("tech") String tech) {
        return alianRepo.findByTech(tech);

    }

    @GetMapping(value = "/getAliansGTId", produces = {"application/json"})
    public List<Alian> getAliansGTId(@RequestParam("id") int id) {
        return alianRepo.findByAidIsGreaterThan(id);

    }

    @GetMapping("/getAliansByTechSorted")
    public List<Alian> getAliansGTId(@RequestParam("tech") String tech) {
        return alianRepo.findByTechSorted(tech);
    }
}
