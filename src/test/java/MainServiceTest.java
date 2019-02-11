import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainServiceTest {

    @Mock
    private MainComponent mainComponent;

    @InjectMocks
    private MainService mainService;

    @Test
    public void doJob() {
        when(mainComponent.getData()).thenReturn("test data");

        mainService.doJob();

        verify(mainComponent).getData();
    }
}