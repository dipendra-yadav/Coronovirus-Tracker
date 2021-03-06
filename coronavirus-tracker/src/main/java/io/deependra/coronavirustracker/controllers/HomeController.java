package io.deependra.coronavirustracker.controllers;

import io.deependra.coronavirustracker.models.LocationStats;
import io.deependra.coronavirustracker.services.CoronaVirusDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
    
    
    @GetMapping("/deaths")
    public String deaths(Model model) {
        List<LocationStats> allStatsDeaths = coronaVirusDataService.getAllStatsDeaths();
        int totalReportedCases = allStatsDeaths.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStatsDeaths.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStatsDeaths);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "deaths";
    }
    
    
    @GetMapping("/recovered")
    public String recovered(Model model) {
        List<LocationStats> allStatsRecovered = coronaVirusDataService.getAllStatsRecovered();
        int totalReportedCases = allStatsRecovered.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStatsRecovered.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStatsRecovered);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "recovered";
    }
}
