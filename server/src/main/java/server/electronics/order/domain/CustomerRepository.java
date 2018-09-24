package server.electronics.order.domain;

import org.springframework.data.repository.Repository;

interface CustomerRepository extends Repository<Customer, Long> {

    Customer save(Customer customer);
}
