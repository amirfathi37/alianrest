package com.alianrest.alianrest.controller;

import com.alianrest.alianrest.entity.Alian;
import com.alianrest.alianrest.repo.AlianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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

    @GetMapping(path = "/getAllAlians" , produces = {"application/json"})
    public List<Alian> getAllAlians() {

        Iterable<Alian> all = alianRepo.findAll();
        List<Alian> alians = new ArrayList<>();
        all.forEach(alian -> {
            alians.add(alian);
        });
        return alians;
    }


    @PostMapping(value = "/saveAlian")
    public Alian getAlian(@RequestBody Alian alian) {
        Alian save = alianRepo.save(alian);
        return save;
    }

    @DeleteMapping("/deleteAlian")
    public String deleteAlian(@RequestParam("id") int deleteId) {
        try {
            alianRepo.delete(alianRepo.findById(deleteId).orElse(new Alian()));

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        System.out.println("deleted.");
        return "home.jsp";
    }

    @GetMapping("/getAliansByTech")
    public ModelAndView getAlianByTech(@RequestParam("tech") String tech) {
        ModelAndView mv = new ModelAndView("/alianshow.jsp");
        List<Alian> byTech = alianRepo.findByTech(tech);
        StringBuilder builder = new StringBuilder("");
        byTech.stream().iterator().forEachRemaining(alian -> {
            builder.append(alian.toString());
        });
        mv.addObject("obj", builder);
        return mv;
    }

    @GetMapping("/getAliansGTId")
    public ModelAndView getAliansGTId(@RequestParam("id") int id) {
        ModelAndView mv = new ModelAndView("/alianshow.jsp");
        List<Alian> byTech = alianRepo.findByAidIsGreaterThan(id);
        StringBuilder builder = new StringBuilder("");
        byTech.stream().iterator().forEachRemaining(alian -> {
            builder.append(alian.toString());
        });
        mv.addObject("obj", builder);
        return mv;
    }

    @GetMapping("/getAliansByTechSorted")
    public ModelAndView getAliansGTId(@RequestParam("tech") String tech) {
        ModelAndView mv = new ModelAndView("/alianshow.jsp");
        List<Alian> byTech = alianRepo.findByTechSorted(tech);
        StringBuilder builder = new StringBuilder("");
        byTech.stream().iterator().forEachRemaining(alian -> {
            builder.append(alian.toString());
        });
        mv.addObject("obj", builder);
        return mv;
    }
}
