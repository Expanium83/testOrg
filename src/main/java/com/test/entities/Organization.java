package com.test.entities;
import javax.persistence.*;


@Entity
@Table(name = "Organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * ИНН организации
     */
    @Column(name = "inn")
    private String inn;

    /**
     * ОГРН организации
     */
    @Column(name = "ogrn")
    private String ogrn;

    /**
     * Название организации
     */
    @Column(name = "name")
    private String name;

    /**
     * Адрес организации
     */
    @Column(name = "address")
    private String address;

    public Organization()
    {
        super();
    }

    public Organization(String inn, String ogrn, String name, String address) {
        super();
        this.inn = inn;
        this.ogrn = ogrn;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
