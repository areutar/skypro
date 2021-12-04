package pro.sky.exceptiondemo.data;

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

    public void setBlock(boolean block) {
        this.block = block;
    }

    public void setName(String name) {
        this.name = name;
    }
}
