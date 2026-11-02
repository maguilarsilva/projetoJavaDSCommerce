package com.maguilar.projetodscommerce.dto;

import com.maguilar.projetodscommerce.entities.Person;

public class PersonDTO {

    private Long id;
    private String name;
    private Double salary;

    private Long departmentId;


    public PersonDTO(Long id, String name, Double salary, Long departmentId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public PersonDTO(Person person) {
        id = person.getId();
        name = person.getName();
        salary = person.getSalary();
        departmentId = person.getDepartment().getId();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
}
