package com.maguilar.projetodscommerce.services;

import com.maguilar.projetodscommerce.dto.PersonDTO;
import com.maguilar.projetodscommerce.dto.PersonDepartmentDTO;
import com.maguilar.projetodscommerce.entities.Department;
import com.maguilar.projetodscommerce.entities.Person;
import com.maguilar.projetodscommerce.repositories.DepartmentRepository;
import com.maguilar.projetodscommerce.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public PersonDepartmentDTO insertPersonDepartment(PersonDepartmentDTO personDepartmentDTO){
        Person person = new Person();
        person.setName(personDepartmentDTO.getName());
        person.setSalary(personDepartmentDTO.getSalary());
        Department department = departmentRepository.getReferenceById(personDepartmentDTO.getDepartment().getId());
        /*Department department = new Department();
          department.setId(personDepartmentDTO.getDepartment().getId());
        */
        person.setDepartment(department);
        personRepository.save(person);
        return new PersonDepartmentDTO(person);
    }


    @Transactional
    public PersonDTO insertPerson(PersonDTO personDTO){
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setSalary(personDTO.getSalary());
       // Department department = departmentRepository.getReferenceById(personDTO.getDepartmentId());
        Department department = new Department();
        department.setId(personDTO.getDepartmentId());
        person.setDepartment(department);
        personRepository.save(person);
        return new PersonDTO(person);
    }



}
