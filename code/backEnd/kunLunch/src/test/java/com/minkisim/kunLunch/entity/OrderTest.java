package com.minkisim.kunLunch.entity;

import com.minkisim.kunLunch.repository.MenuRepository;
import com.minkisim.kunLunch.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class OrderTest {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MenuRepository menuRepository;

    @PersistenceContext
    EntityManager em;

    public Menu createMenu(int i){
        Menu menu = new Menu();
        menu.setName("테스트 상품"+i);
        menu.setPrice(10000);
        menu.setMenuDetail("상세설명");
        menu.setMenuWarning("알레르기설명");
        menu.setMenuImgUrl("url");
        menu.setRegTime(LocalDateTime.now());
        menu.setUpdateTime(LocalDateTime.now());
        return menu;
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest(){
        Order order = new Order();

        for(int i=0;i<3;i++){
            Menu menu = this.createMenu(i);
            menuRepository.save(menu);

            OrderItem orderItem = new OrderItem();
            orderItem.setMenu(menu);
            orderItem.setAmount(10);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }

        orderRepository.saveAndFlush(order);
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(3,savedOrder.getOrderItems().size());

    }

}