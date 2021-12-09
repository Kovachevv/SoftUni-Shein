package com.example.shein.web;

import com.example.shein.service.StatsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }


    @GetMapping("/statistics")
    public ModelAndView getStatistics() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stats", statsService.getRequests());
        modelAndView.setViewName("stats");
        return modelAndView;
    }
}
