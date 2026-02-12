package org.example.turismoapp.service;

import lombok.RequiredArgsConstructor;
import org.example.turismoapp.dto.HotelRequest;
import org.example.turismoapp.dto.HotelResponse;
import org.example.turismoapp.exception.HotelNotFoundException;
import org.example.turismoapp.model.Hotel;
import org.example.turismoapp.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    @Transactional(readOnly = true)
    public List<HotelResponse> findAll() {
        return hotelRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public HotelResponse findById(Long id) {
        return hotelRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new HotelNotFoundException("Hotel con id " + id + " no encontrado"));
    }

    @Transactional
    public HotelResponse create(HotelRequest request) {
        Hotel hotel = new Hotel();
        hotel.setNombre(request.nombre());
        hotel.setUbicacion(request.ubicacion());
        hotel.setDescripcion(request.descripcion());
        hotel.setEstrellas(request.estrellas());
        hotel.setPrecioNoche(request.precioNoche()); // Recuerda: BigDecimal

        Hotel savedHotel = hotelRepository.save(hotel);

        return mapToResponse(savedHotel);
    }

    @Transactional
    public HotelResponse update(Long id, HotelRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel no encontrado"));

        hotel.setNombre(request.nombre());
        hotel.setUbicacion(request.ubicacion());
        hotel.setDescripcion(request.descripcion());
        hotel.setEstrellas(request.estrellas());
        hotel.setPrecioNoche(request.precioNoche());

        Hotel updatedHotel = hotelRepository.save(hotel);

        return mapToResponse(updatedHotel);
    }

    @Transactional
    public void delete(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new HotelNotFoundException("No se puede borrar. Hotel no encontrado.");
        }
        hotelRepository.deleteById(id);
    }

    private HotelResponse mapToResponse(Hotel hotel) {
        return new HotelResponse(
                hotel.getId(),
                hotel.getNombre(),
                hotel.getUbicacion(),
                hotel.getDescripcion(),
                hotel.getEstrellas(),
                hotel.getPrecioNoche()
        );
    }
}