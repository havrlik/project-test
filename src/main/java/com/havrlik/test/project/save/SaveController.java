package com.havrlik.test.project.save;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveController {

    private final SaveService saveService;

    public SaveController(
            final SaveService saveService
    ) {
        this.saveService = saveService;
    }

    @PostMapping("/save")
    public void save() {
        saveService.save();
    }
}
