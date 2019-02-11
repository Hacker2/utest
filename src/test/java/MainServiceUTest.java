import com.max.utest.URunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

@RunWith(URunner.class)
public class MainServiceUTest {

//    @InjectMocks
    private MainService mainService;

    @Test
    public void doJob() {
        when(mainService.mainComponent.getData()).thenReturn("test data");

        mainService.doJob();

        Mockito.verify(mainService.mainComponent).getData();
    }
}