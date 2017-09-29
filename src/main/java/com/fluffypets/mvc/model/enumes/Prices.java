package com.fluffypets.mvc.model.enumes;

public enum Prices {

    DIAP1("less 25$", 0, 25), DIAP2("25$-50$", 25, 50), DIAP3("50$-100$", 50, 100),
    DIAP4("100$-300$", 100, 300), DIAP5("more than 300$", 300, Integer.MAX_VALUE),ALL("all",0,Integer.MAX_VALUE);

    private final String label;
    private final int min;
    private final int max;

    Prices(String label, int min, int max) {
        this.label = label;
        this.min = min;
        this.max = max;
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
