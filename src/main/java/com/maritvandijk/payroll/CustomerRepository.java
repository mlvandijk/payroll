package com.maritvandijk.payroll;

import org.springframework.data.jpa.repository.JpaRepository;

interface CustomerRepository  extends JpaRepository<Customer, Long> {

}
