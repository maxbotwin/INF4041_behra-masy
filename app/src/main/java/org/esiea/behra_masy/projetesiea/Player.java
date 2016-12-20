package org.esiea.behra_masy.projetesiea;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class of the player and its characteristics
 */
public class Player {
    private String name;
    private String surname;
    private String age;
    private String number;
    private String nationality;
    private String position;


    /***
     * Constructeur
     *
     * @param json    objet json transformé en Player
     * @param details flag to tell if the details need to be loaded or not
     */
    public Player(JSONObject json, boolean details) {
        try {
            this.setName(json.getString("Nom"));
            this.setSurname(json.getString("Prénom"));
            this.setAge(json.getString("Age"));
            this.setNumber(json.getString("Numéro"));
            this.setPosition(json.getString("Poste"));
            this.setNationality(json.getString("Nationalité"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
