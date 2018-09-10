package server.electronics.order.domain;

import org.springframework.data.repository.Repository;

interface CityRepository extends Repository<City, Long> {
    City save(City city);
}
