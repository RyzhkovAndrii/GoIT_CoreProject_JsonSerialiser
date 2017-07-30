package main.java;

import main.java.annotation.JsonProperty;

public class Testobj {

    @JsonProperty("arr")
    public int i = -453;

    @JsonProperty("arr")
    public transient boolean j = false;

    @JsonProperty("myStr")
    private String str = "efgegr";

    public int[] arr = {1, 4, 3};

    public TestClass2 testClass2 = new TestClass2();

}
