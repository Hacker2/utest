import com.max.utest.URunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(URunner.class)
public class MainServiceExampleTest {
    private MainService mainService;

    @Test
    public void doJob() {
        mainService.doJob();

        verify(mainService.mainComponent).getData(any(String.class));
    }
}
