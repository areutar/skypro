package wheeledService;

public abstract class Motorized extends Wheeled {
    protected Motorized(String modelName, int wheelsCount) {
        super(modelName, wheelsCount);
    }

    private void checkEngine() {
        System.out.println("Проверяем двигатель");
    }

    @Override
    public void service() {
        checkEngine();
    }
}
