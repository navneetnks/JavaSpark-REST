package com.sqldexter.organization.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOME on 12-02-2016.
 */
public class Company {
    private String id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String email;
    private String phoneNumber;
    private List<Owner> owners;
    public Company(String id, String name,String address,String city,
                   String country,String email,String phoneNumber,List<Owner> owners){
        this.id=id;
        this.name=name;
        this.address=address;
        this.city=city;
        this.country=country;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.owners=owners;
    }
    public Company(String id, String name,String address,String city,
                   String country){
        this(id,name,address,city,country,null,null,null);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }
    public void add(Owner owner){
        if(owner!=null) {
            if (this.owners != null) {
                this.owners.add(owner);
            } else {
                this.owners = new ArrayList<Owner>();
                this.owners.add(owner);
            }
        }
    }
    public void removeOwner(String name){
        if(name!=null && this.owners!=null) {
            for(Owner owner:this.owners){
                if(name.equalsIgnoreCase(owner.getName()))
                    this.owners.remove(owner);
            }
        }
    }
    public void removeOwner(int index){
        if(this.owners!=null && index>=0 && index<this.owners.size()) {
           this.owners.remove(index);
        }
    }

    @Override
    public String toString() {
        String str="id="+id+"\tname="+name+"\taddress="+address+"\tcity="+city
                +"\tcountry="+country+"\temail="+email+"\tphonenumber="+phoneNumber
                +"owners="+owners;
        return str;
    }
}
