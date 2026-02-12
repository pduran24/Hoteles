package org.example.turismoapp.service;

import lombok.RequiredArgsConstructor;
import org.example.turismoapp.dto.ReservaRequest;
import org.example.turismoapp.dto.ReservaResponse;
import org.example.turismoapp.exception.ClienteNotFoundException;
import org.example.turismoapp.exception.HotelNotFoundException;
import org.example.turismoapp.exception.ReservaNoEncontradaException;
import org.example.turismoapp.model.Cliente;
import org.example.turismoapp.model.Hotel;
import org.example.turismoapp.model.Reserva;
import org.example.turismoapp.repository.ClienteRepository;
import org.example.turismoapp.repository.HotelRepository;
import org.example.turismoapp.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final HotelRepository hotelRepository;
    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ReservaResponse> findAll() {
        return reservaRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ReservaResponse findById(Long id) {
        return reservaRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ReservaNoEncontradaException("Reserva con id " + id + " no encontrada"));
    }

    @Transactional
    public ReservaResponse create(ReservaRequest request) {
        if (!request.fechaSalida().isAfter(request.fechaEntrada())) {
            throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha de entrada");
        }

        Hotel hotel = hotelRepository.findById(request.hotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel no encontrado con id: " + request.hotelId()));

        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con id: " + request.clienteId()));

        long dias = ChronoUnit.DAYS.between(request.fechaEntrada(), request.fechaSalida());

        BigDecimal precioTotal = hotel.getPrecioNoche().multiply(BigDecimal.valueOf(dias));

        Reserva reserva = new Reserva();
        reserva.setFechaEntrada(request.fechaEntrada());
        reserva.setFechaSalida(request.fechaSalida());
        reserva.setNumeroDias((int) dias);
        reserva.setPrecioTotal(precioTotal);
        reserva.setHotel(hotel);
        reserva.setCliente(cliente);

        Reserva savedReserva = reservaRepository.save(reserva);
        return mapToResponse(savedReserva);
    }


    @Transactional
    public void delete(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new ReservaNoEncontradaException("Reserva no encontrada");
        }
        reservaRepository.deleteById(id);
    }

    private ReservaResponse mapToResponse(Reserva reserva) {
        return new ReservaResponse(
                reserva.getId(),
                reserva.getHotel().getNombre(),
                reserva.getCliente().getNombre(),
                reserva.getFechaEntrada(),
                reserva.getFechaSalida(),
                reserva.getNumeroDias(),
                reserva.getPrecioTotal()
        );
    }
}