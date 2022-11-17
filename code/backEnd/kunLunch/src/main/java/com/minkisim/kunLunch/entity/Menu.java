package com.minkisim.kunLunch.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="Menu")
@Getter
@Setter
@ToString
public class Menu {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true,length = 50)
    private String name;

    @Column(name="price",nullable = false)
    private int Price;
    @Column(nullable = false)
    private String menuImgUrl;

    private String menuDetail;
    private String menuWarning;
}
