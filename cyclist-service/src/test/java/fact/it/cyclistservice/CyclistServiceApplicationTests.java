package fact.it.cyclistservice;

import fact.it.cyclistservice.dto.CyclistRequest;
import fact.it.cyclistservice.dto.CyclistResponse;
import fact.it.cyclistservice.model.Cyclist;
import fact.it.cyclistservice.model.Gender;
import fact.it.cyclistservice.repository.CyclistRepository;
import fact.it.cyclistservice.service.CyclistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CyclistServiceApplicationTests {
    @InjectMocks
    private CyclistService cyclistService;

    @Mock
    private CyclistRepository cyclistRepository;

    @Test
    public void testWhetherCyclistsWereAdded() {
        when(cyclistRepository.count()).thenReturn(0L);
        cyclistService.addCyclists();
        verify(cyclistRepository, times(11)).save(any(Cyclist.class));
        reset(cyclistRepository);
        when(cyclistRepository.count()).thenReturn(11L);
        cyclistService.addCyclists();
        verify(cyclistRepository, never()).save(any(Cyclist.class));
    }

    @Test
    public void testWhetherCyclistWasRemoved() {
        cyclistService.deleteById(anyString());

        verify(cyclistRepository, times(1)).deleteById(anyString());
    }

    @Test
    public void testWhetherCyclistWasFetched() {
        cyclistService.getCyclist(anyString());

        verify(cyclistRepository, times(1)).findById(anyString());
    }

    @Test
    public void testWhetherAllCyclistsWereFetched() {
        when(cyclistRepository.findAll()).thenReturn(new ArrayList<>());

        List<CyclistResponse> cyclists = cyclistService.getAllCyclists();

        assertEquals(0, (long) cyclists.size());

        verify(cyclistRepository, times(1)).findAll();
    }

    @Test
    public void testWhetherCyclistExists() {
        when(cyclistRepository.existsById("deadbeef")).thenReturn(true);

        assertTrue(cyclistService.exists("deadbeef"));

        verify( cyclistRepository, times(1)).existsById("deadbeef");
    }

    @Test
    public void testDtoMapper() {
        when(cyclistRepository.findById("deadbeef")).thenReturn(Optional.of(new Cyclist("deadbeef", "Yussef", "Dalton", 20, Gender.MALE)));

        Optional<CyclistResponse> response = cyclistService.getCyclist("deadbeef");

        assertTrue(response.isPresent());
        assertEquals("Yussef", response.get().getFirstName());
        assertEquals("Dalton", response.get().getLastName());
        assertEquals(20, response.get().getAge());
        assertEquals("Male", response.get().getGender());
    }


}
