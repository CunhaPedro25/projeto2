package org.projeto.data.entities.users;

import jakarta.persistence.*;


import lombok.Getter;
import lombok.Setter;
import org.projeto.data.entities.*;
import org.projeto.data.entities.enums.ClientType;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends User {
  @Column(name = "address", length = Integer.MAX_VALUE)
  private String address;

  @Column(name = "door")
  private Integer door;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "zipcode")
  private Zipcode zipcode;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "client_type", nullable = false)
  private ClientType clientType;

  @OneToMany(mappedBy = "client")
  private Set<Budget> budgets = new LinkedHashSet<>();

  @OneToMany(mappedBy = "client")
  private Set<Complaint> complaints = new LinkedHashSet<>();

  @OneToMany(mappedBy = "client")
  private Set<Invoice> invoices = new LinkedHashSet<>();

  @OneToMany(mappedBy = "client")
  private Set<Project> projects = new LinkedHashSet<>();

  public Client() {}

  public Client(String name, String email, String password, String phone, ClientType clientType) {
    super(name, email, password, phone);
    this.clientType = clientType;
  }

  public Client(String name, String email, String password, String phone, String address, Integer door, Zipcode zipcode, ClientType clientType) {
    super(name, email, password, phone);
    this.address = address;
    this.door = door;
    this.zipcode = zipcode;
    this.clientType = clientType;
  }
}