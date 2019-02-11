public class MainService {

    MainComponent mainComponent;

    public MainService() {}

    public MainService(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }

    public void doJob() {
        String data = mainComponent.getData();
        System.out.println(data);
    }
}
