package com.example.booklibrary.library.model;

import jakarta.persistence.*;

@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false)
    private String firstName;
    @Column(length = 30, nullable = false)
    private String lastName;
    @Column(length = 15, nullable = false)
    private String phone;
    @Column(length = 80, nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "reader", cascade = CascadeType.ALL)
    private ReaderCard readerCard;


    @OneToOne
    @JoinColumn(name = "appUserId", referencedColumnName = "id")
    private AppUser appUser;

    public Reader(){}

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReaderCard getReaderCard() {
        return readerCard;
    }

    public void setReaderCard(ReaderCard readerCard) {
        this.readerCard = readerCard;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
