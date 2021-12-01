package pro.sky.exceptiondemo;

public class Person {
    private boolean block;
    private String name;

    public Person(String name, boolean block) {
        this.block = block;
        this.name = name;
    }

    public boolean isBlock() {
        return block;
    }

    public String getName() {
        return name;
    }
}
