package com.minkisim.kunLunch.repository;

import com.minkisim.kunLunch.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
