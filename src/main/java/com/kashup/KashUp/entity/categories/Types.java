package com.kashup.KashUp.entity.categories;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "Types")
public class Types
{
    @Id
    private Long typeID;

    private Long categoryID;

    private String typeName;

    private BigDecimal createdTime;

    private Long createdBy;
}
