package br.com.serasa.model;

public enum RegiaoEnum {

    NORTE("norte"),
    NORDESTE("nordeste"),
    SUDESTE("sudeste"),
    CENTRO_OESTE("centro_oeste"),
    SUL("sul");
    private final String regiao;
    RegiaoEnum(String regiao) {
        this.regiao = regiao;
    }
    public String getRegiao() {
        return regiao;
    }
}
