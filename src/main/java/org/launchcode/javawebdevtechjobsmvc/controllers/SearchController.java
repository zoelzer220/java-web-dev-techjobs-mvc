package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm ) {
        ArrayList<Job> jobs;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.isEmpty()) {
            jobs = JobData.findAll();
            model.addAttribute("title", "Jobs With " + searchType + ": " + searchTerm);
        } else if (searchType.toLowerCase().equals("all")){
                jobs = JobData.findByValue(searchTerm);
                model.addAttribute("title", "Jobs With All: " + searchTerm);
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs With " + searchType + ": " + searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("searchType", searchType);
        model.addAttribute("columns", columnChoices);

        return "search";
    }


    // TODO #3 - Create a handler to process a search request and render the updated search view.

}
