package org.example.turismoapp.service;

import lombok.RequiredArgsConstructor;
import org.example.turismoapp.dto.ClienteRequest;
import org.example.turismoapp.dto.ClienteResponse;
import org.example.turismoapp.model.Cliente;
import org.example.turismoapp.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ClienteResponse> findAll() {
        return clienteRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponse findById(Long id) {
        return clienteRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Cliente con id " + id + " no encontrado"));
    }

    @Transactional
    public ClienteResponse create(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNombre(request.nombre());

        Cliente saved = clienteRepository.save(cliente);
        return mapToResponse(saved);
    }

    @Transactional
    public ClienteResponse update(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(request.nombre());

        return mapToResponse(clienteRepository.save(cliente));
    }

    @Transactional
    public void delete(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Cliente no encontrado.");
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponse mapToResponse(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getNombre());
    }
}