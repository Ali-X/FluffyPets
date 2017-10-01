package com.fluffypets.mvc.model.enumes;

public enum Prices {

    DIAP1("less 10$","менш ніж 10$", 0, 10), DIAP2("10$-20$","10$-20$", 10, 20), DIAP3("20$-50$","20$-50$", 20,50),
    DIAP4("50$-100$","50$-100$", 50, 100), DIAP5("more than 100$","більш ніж 100$", 100, Integer.MAX_VALUE)
    ,ALL("all","всі",0,Integer.MAX_VALUE);

    private final String label;
    private final String labelUa;
    private final int min;
    private final int max;

    Prices(String label,String labelUa, int min, int max) {
        this.label = label;
        this.labelUa = labelUa;
        this.min = min;
        this.max = max;
    }

    public String getLabelUa() {
        return labelUa;
    }

    public String getLabel() {
        return label;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
