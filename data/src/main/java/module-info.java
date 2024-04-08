module data {
  requires spring.data.jpa;
  requires spring.context;
  requires spring.boot.autoconfigure;
  requires spring.boot;
  requires spring.beans;
  requires spring.tx;
  requires jakarta.transaction;
  requires jakarta.persistence;
  requires static lombok;
  requires spring.data.commons;
  requires org.hibernate.orm.core;
  exports proj2.projeto.data.entities;
  exports proj2.projeto.data.entities.users;
  exports proj2.projeto.data.entities.enums;
  exports proj2.projeto.data.repositories;
  exports proj2.projeto.data.repositories.users;
  exports proj2.projeto.data.repositories.documents;
  exports proj2.projeto.data.repositories.enums;
  exports proj2.projeto.data.services;
  exports proj2.projeto.data.services.users;
}