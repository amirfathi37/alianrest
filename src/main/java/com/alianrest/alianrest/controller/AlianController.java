package com.alianrest.alianrest.controller;

import com.alianrest.alianrest.entity.Alian;
import com.alianrest.alianrest.repo.AlianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class AlianController {

    @Autowired
    AlianRepo alianRepo;

    @GetMapping(path = "/home")
    public String home() {
        return "home.jsp";
    }

    @GetMapping(path = "/getAlian")
    public ModelAndView getAlian(@RequestParam("id") int aid) {

        ModelAndView mv = new ModelAndView("alianshow.jsp");
        Optional<Alian> alian = alianRepo.findById(aid);
        AtomicReference<Alian> alian1 = new AtomicReference<>();
        alian.ifPresent(alian2 -> {
            alian1.set(alian2);
        });
        mv.addObject("obj", alian1.toString());
        return mv;
    }

    @GetMapping(path = "/getAllAlians")
    public ModelAndView getAllAlians() {

        ModelAndView mv = new ModelAndView("alianshow.jsp");
        Iterable<Alian> all = alianRepo.findAll();
        StringBuilder builder = new StringBuilder("");
        all.forEach(alian -> {
            builder.append(alian.toString());
        });
        mv.addObject("obj", all);
        return mv;
    }


    @PostMapping("/saveAlian")
    public String getAlian(Alian alian) {
        alianRepo.save(alian);
        return "home.jsp";
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
