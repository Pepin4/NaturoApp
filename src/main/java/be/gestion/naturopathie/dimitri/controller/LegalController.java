package be.gestion.naturopathie.dimitri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LegalController {

    @GetMapping("/cgu")
    public String afficherCGU() {
        return "cgu";
    }
}
