package wheeledService;

public class ServiceStation {
    public void check(Wheeled vehicle) {
        System.out.println("Обслуживаем " + vehicle.getModelName());
        vehicle.updateTyres();
        vehicle.service();
    }
}
