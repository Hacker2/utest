import com.max.utest.URunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(URunner.class)
public class MainServiceUTest {

    private MainService mainService;

    @Test
    public void doJob() {
        mainService.doJob();

        Mockito.verify(mainService.mainComponent).getData(any(String.class));
    }
}
