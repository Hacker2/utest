public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
        MainComponent mainComponent = new MainComponentImpl();
        MainService mainService = new MainService(mainComponent);
        mainService.doJob();
    }
}
