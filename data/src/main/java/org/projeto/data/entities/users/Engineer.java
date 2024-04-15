package org.projeto.data.entities.users;

import jakarta.persistence.*;


import lombok.Getter;
import lombok.Setter;
import org.projeto.data.entities.Budget;
import org.projeto.data.entities.Project;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "engineer")
public class Engineer extends User{
  @OneToMany(mappedBy = "engineer")
  private Set<Budget> budgets = new LinkedHashSet<>();

  @OneToMany(mappedBy = "engineer")
  private Set<Project> projects = new LinkedHashSet<>();

  public Engineer() {}

  public Engineer(String name, String email, String password, String phone) {
    super(name,email,password,phone);
  }
}