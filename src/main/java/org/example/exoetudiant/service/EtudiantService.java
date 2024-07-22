package org.example.exoetudiant.service;

import org.example.exoetudiant.model.Etudiant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EtudiantService {
    private List<Etudiant> etudiants;

    public EtudiantService() {
        etudiants = new ArrayList<Etudiant>();

        Etudiant etudiant1 = Etudiant
                .builder()
                .id(UUID.randomUUID())
                .nom("Brisette")
                .prenom("Virginie")
                .email("virginie.brisette@mail.fr")
                .age(25)
                .build();

        Etudiant etudiant2 = Etudiant
                .builder()
                .id(UUID.randomUUID())
                .nom("Garcia")
                .prenom("Bellamy")
                .email("garcia.bellamy@outlook.com")
                .age(18)
                .build();

        Etudiant etudiant3 = Etudiant
                .builder()
                .id(UUID.randomUUID())
                .nom("Pirouet")
                .prenom("Claude")
                .email("ClaudePirouet@live.fr")
                .age(29)
                .build();

        Etudiant etudiant4 = Etudiant
                .builder()
                .id(UUID.randomUUID())
                .nom("Lang")
                .prenom("Thiery")
                .email("thiery.lang@gmail.com")
                .age(20)
                .build();

        etudiants.add(etudiant1);
        etudiants.add(etudiant2);
        etudiants.add(etudiant3);
        etudiants.add(etudiant4);
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public Etudiant getEtudiantById(UUID id) {
        Etudiant etudiantSearchId = etudiants
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
        return etudiantSearchId;
    }

    public List<Etudiant> getEtudiantsByName(String nom) {
        List<Etudiant> etudiantSearchNom = etudiants
                .stream()
                .filter(e -> nom.equals(e.getNom()))
                .toList();
        return etudiantSearchNom;
    }

    public Etudiant addEtudiant(Etudiant etudiant) {
        etudiant.setId(UUID.randomUUID());
        etudiants.add(etudiant);
        return etudiant;
    }

    public boolean deleteEtudiant(UUID id) {
        etudiants.removeIf(e -> e.getId().equals(id));
        return true;
    }

    public void updateEtudiant(Etudiant etudiant) {
        Etudiant etudiantToUpdate = getEtudiantById(etudiant.getId());
        etudiantToUpdate.setNom(etudiant.getNom());
        etudiantToUpdate.setPrenom(etudiant.getPrenom());
        etudiantToUpdate.setEmail(etudiant.getEmail());
        etudiantToUpdate.setAge(etudiant.getAge());


    }
}
