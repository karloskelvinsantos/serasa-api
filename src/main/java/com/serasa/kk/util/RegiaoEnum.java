package com.serasa.kk.util;

public enum RegiaoEnum {

    NORTE("norte"),
    NORDESTE("nordeste"),
    SUDESTE("sudeste"),
    CENTRO_OESTE("centro_oeste"),
    SUL("sul");
    private String regiao;
    RegiaoEnum(String regiao) {
        this.regiao = regiao;
    }
}
