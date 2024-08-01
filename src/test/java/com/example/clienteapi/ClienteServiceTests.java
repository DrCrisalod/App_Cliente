

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.cliente.model.Cliente;
import com.example.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ClienteServiceTest{

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setEmail("juan.perez@example.com");
    }

    @Test
    void createCliente() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente created = clienteService.createCliente(cliente);
        assertEquals(cliente.getNombre(), created.getNombre());
    }

    @Test
    void getClienteById() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        Optional<Cliente> found = clienteService.getClienteById(1L);
        assertEquals(cliente.getNombre(), found.get().getNombre());
    }

    @Test
    void updateCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente updated = clienteService.updateCliente(1L, cliente);
        assertEquals(cliente.getNombre(), updated.getNombre());
    }

    @Test
    void deleteCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);
        clienteService.deleteCliente(1L);
        verify(clienteRepository, times(1)).delete(cliente);
    }
}
