package org.example.turismoapp.service;

import org.example.turismoapp.dto.HotelRequest;
import org.example.turismoapp.dto.HotelResponse;
import org.example.turismoapp.exception.HotelNotFoundException;
import org.example.turismoapp.model.Hotel;
import org.example.turismoapp.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HotelResponse> getHotels() {
        return hotelRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public HotelResponse getHotelById(long id) {
        return hotelRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new HotelNotFoundException("Hotel con id "+id+" no encontrado"));
    }

    @Override
    public void addHotel(HotelRequest hotelRequest) {
        hotelRepository.save(mapToHotel(hotelRequest));
    }

    @Override
    public void updateHotelById(long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel con id "+id+" no encontrado"));

        hotelRepository.save(hotel);
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

    private Hotel mapToHotel(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel();

        hotel.setNombre(hotelRequest.nombre());
        hotel.setUbicacion(hotelRequest.ubicacion());
        hotel.setDescripcion(hotelRequest.descripcion());
        hotel.setEstrellas(hotelRequest.estrellas());
        hotel.setPrecioNoche(hotelRequest.precioNoche());

        return hotel;
    }

}
