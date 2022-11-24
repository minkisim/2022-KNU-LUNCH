package com.minkisim.kunLunch.repository;

import com.minkisim.kunLunch.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
