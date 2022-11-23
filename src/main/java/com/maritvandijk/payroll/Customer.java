package com.maritvandijk.payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Customer {
    private @Id @GeneratedValue Long customerId;
    private String customerName;
    private String companyName;

    Customer() {}

    Customer(String customerName, String companyName) {
        this.customerName = customerName;
        this.companyName = companyName;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
