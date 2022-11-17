package com.minkisim.kunLunch.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {

    private Long id;
    private String name;
    private int Price;
    private String menuImgUrl;
    private String menuDetail;
    private String menuWarning;

}
