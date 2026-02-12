package org.example.turismoapp.service;

import org.example.turismoapp.dto.HotelRequest;
import org.example.turismoapp.dto.HotelResponse;

import java.util.List;

public interface IHotelService {
    List<HotelResponse> getHotels();
    HotelResponse getHotelById(long id);
    void addHotel(HotelRequest hotelRequest); // tendrá autenticación
    void updateHotelById(long id); // tendrá autenticación

}
