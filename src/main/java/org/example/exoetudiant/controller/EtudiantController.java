package org.example.exoetudiant.controller;

import org.example.exoetudiant.model.Etudiant;
import org.example.exoetudiant.service.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Controller
public class EtudiantController {
    private EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "accueil";
    }

    @GetMapping("/liste")
    public String getAllEtudiants(Model model) {
        List<Etudiant> etudiants = etudiantService.getEtudiants();
        model.addAttribute("etudiants", etudiants);
        return "liste";
    }

    @GetMapping("/detail")
        public String getEtudiantDetail(@RequestParam(value = "id") UUID id, Model model) {
            Etudiant etudiant = etudiantService.getEtudiantById(id);
            model.addAttribute("etudiant", etudiant);
            return "details";
        }

    @GetMapping("/search")
    public String getEtudiantSearch(@RequestParam(value = "nom") String nom, Model model) {
        List<Etudiant> etudiantsNom = etudiantService.getEtudiantsByName(nom);
        model.addAttribute("etudiants", etudiantsNom);
        System.out.println(model);
        return "rsltrecherche";
    }

    @GetMapping("/add")
    public String addEtudiant(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "inscription";
    }

    @PostMapping("/formulaire")
    public String inscrireEtudiant(@ModelAttribute Etudiant etudiant, Model model) {
        Etudiant etudiantToAdd;
        if (etudiant.getId() == null) {
        etudiantToAdd= etudiantService.addEtudiant(etudiant);
        }
        else {
            etudiantService.updateEtudiant(etudiant);
//            etudiantToAdd =etudiantService.getEtudiantById(etudiant.getId());
        }
        return "redirect:/liste";
    }

    @GetMapping("/delete")
    public String deleteEtudiant(@RequestParam(value = "id") UUID id) {
        boolean isDeleted = etudiantService.deleteEtudiant(id);
        return "redirect:/liste";
    }

    @GetMapping("/modif")
    public String editEtudiant(@RequestParam("id") UUID id, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        model.addAttribute("etudiant", etudiant);
        return "inscription";
    }

    @GetMapping("/pb")
    public String pb() {
        if (true) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }
        return "liste";
    }

}
