import java.util.List;

public class MainService {

    MainComponent mainComponent;
    MainDaoImpl mainDao;
//    List mockedList;

    public MainService() {}

    public MainService(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }

    public void doJob() {
        String data = mainComponent.getData("hello ");
        System.out.println(data);
        mainDao.save(data);
//        mainDao.save(data);
//        mockedList.add("one");
    }
}
