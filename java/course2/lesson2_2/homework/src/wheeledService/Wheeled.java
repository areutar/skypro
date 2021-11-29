package wheeledService;

public abstract class Wheeled {
    private final String modelName;
    private final int wheelsCount;

    protected Wheeled(String modelName, int wheelsCount) {
        this.modelName = modelName;
        this.wheelsCount = wheelsCount;
    }

    public String getModelName() {
        return modelName;
    }


    public void updateTyres() {
        for (int i = 0; i < wheelsCount; i++) {
            System.out.println("Меняем покрышку");
        }
    }

    public abstract void service();
}
