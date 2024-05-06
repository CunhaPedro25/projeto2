module org.projeto.data {
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires spring.core;
  requires spring.data.jpa;
  requires spring.beans;
  requires spring.tx;
  requires jakarta.persistence;
  requires static lombok;
  requires spring.data.commons;
  requires org.hibernate.orm.core;
  requires org.apache.commons.codec;
  requires spring.web;
    requires com.fasterxml.jackson.annotation;
    requires spring.webmvc;

    exports org.projeto.data;
  exports org.projeto.data.entities;
  exports org.projeto.data.entities.users;
  exports org.projeto.data.entities.enums;
  exports org.projeto.data.repositories;
  exports org.projeto.data.repositories.users;
  exports org.projeto.data.repositories.enums;
  exports org.projeto.data.repositories.documents;
  exports org.projeto.data.services;
  exports org.projeto.data.services.users;
  exports org.projeto.data.controllers;

  opens org.projeto.data;
  opens org.projeto.data.entities;
  opens org.projeto.data.entities.users;
  opens org.projeto.data.entities.enums;
  opens org.projeto.data.repositories;
  opens org.projeto.data.repositories.users;
  opens org.projeto.data.repositories.enums;
  opens org.projeto.data.repositories.documents;
  opens org.projeto.data.services;
  opens org.projeto.data.services.users;
  opens org.projeto.data.controllers;
}
