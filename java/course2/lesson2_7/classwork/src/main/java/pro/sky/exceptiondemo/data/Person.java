package pro.sky.exceptiondemo.data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return block == person.block && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, name);
    }
}
