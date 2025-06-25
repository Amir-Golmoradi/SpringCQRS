package dev.amirgol.springcqrs.application.dto.mapper;

import dev.amirgol.springcqrs.application.dto.CustomerResponse;
import dev.amirgol.springcqrs.domain.model.Customer;
import dev.amirgol.springcqrs.infrastructure.model.CustomerMongoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    @Mapping(target = "id", source="id")
    CustomerResponse toResponse(Customer customer);

    Customer toDomain(CustomerMongoEntity customerMongoEntity);

    CustomerMongoEntity toMongoEntity(Customer customer);
}


class Employee {
    private String fullName; // SOURCE
    private int age;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


class Person {
    private String name; // TARGET
    private int yearsOld;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }
}