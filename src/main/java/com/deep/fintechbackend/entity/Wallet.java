package com.deep.fintechbackend.entity;
import jakarta.persistence.*;

@Entity
    @Table(name = "wallets")
    public class Wallet{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double balance=0.0;

    @OneToOne
    @JoinColumn(name = "user_id")
            private User user;
    public long getId(){
        return id;

    }
    public Double getBalance(){
        return balance;
    }
    public void setBalance(Double balance){
        this.balance=balance;
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }

}

