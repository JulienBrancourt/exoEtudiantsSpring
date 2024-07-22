package org.example.exoetudiant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    private UUID id;
    private String nom;
    private String prenom;
    private String email;
    private int age;
}
