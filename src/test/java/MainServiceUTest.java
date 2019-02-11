import com.max.utest.URunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(URunner.class)
public class MainServiceUTest {

    private MainService mainService;

    @Test
    public void doJob() {
        String hi = "hi";
        when(mainService.mainComponent.getData("hello")).thenReturn(hi);

        mainService.doJob();

//        InOrder inOrder = inOrder(mainService.mainComponent, mainService.mainDao);
//        verify(mainService.mainComponent).getData(any(String.class));
//        verify(mainService.mainComponent).getData(anyString());
//        verify(mainService.mainComponent, times(1)).getData(anyString());
        verify(mainService.mainComponent, atLeastOnce()).getData(anyString());
//        verify(mainService.mainDao, atLeastOnce()).save(anyString());
        verify(mainService.mainDao).save(hi);
//        verify(mainService.mockedList).add(any(String.class));
        verifyNoMoreInteractions(mainService.mainComponent, mainService.mainDao);
    }

    @Test
    public void doJobException() throws Exception {
        doThrow(new RuntimeException()).when(mainService.mainComponent).getData(anyString());

        mainService.doJob();
    }
}
