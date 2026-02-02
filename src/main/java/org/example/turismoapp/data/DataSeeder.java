package org.example.turismoapp.data;

import lombok.extern.slf4j.Slf4j;
import org.example.turismoapp.model.Hotel;
import org.example.turismoapp.repository.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final HotelRepository hotelRepository;

    public DataSeeder(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (hotelRepository.count() == 0) {

            log.info("Creando Hoteles...");
            List<Hotel> hoteles = List.of(
                    buildHotel("Posada de Invernalia", "Invernalia", "Acogedora estancia junto al Bosque de Dioses. Calefacción por aguas termales naturales.", 4, "120.50"),
                    buildHotel("Barracones del Castillo Negro", "El Muro", "Experiencia austera para los valientes. Incluye capa de lana negra y guiso caliente. No se admiten Caminantes Blancos.", 1, "10.00"),
                    buildHotel("Hostal Puerto Blanco", "Puerto Blanco", "El mejor pescado del Norte. Vistas al puerto y trato amable de la casa Manderly.", 3, "65.00"),
                    buildHotel("La Última Hoguera", "Último Hogar", "El alojamiento más al norte antes del Muro. Rústico, frío y peligroso.", 2, "30.00"),
                    buildHotel("Fuerte Terror Bed & Breakfast", "Fuerte Terror", "Una experiencia... inolvidable. Nuestro lema: 'Nuestras cuchillas están afiladas'. No incluye desayuno.", 2, "45.00"),

                    buildHotel("Posada de la Encrucijada", "Tierras de los Ríos", "Punto de encuentro legendario. Famoso por su pastel de carne y cerveza negra. Cuidado con las peleas de taberna.", 3, "55.00"),
                    buildHotel("Suites del Nido de Águilas", "El Valle", "Solo accesible en mula. Vistas vertiginosas y celdas abiertas al cielo para huéspedes molestos.", 5, "300.00"),
                    buildHotel("Balneario de Aguasdulces", "Aguasdulces", "Rodeado por el Piedra Caída y el Forca Roja. Ideal para amantes de la pesca y los funerales vikingos.", 4, "110.00"),
                    buildHotel("Ruinas de Harrenhal", "Harrenhal", "El hotel más grande de Poniente. Habitaciones enormes, algo quemadas y posiblemente encantadas. Precios de derribo.", 1, "15.00"),

                    buildHotel("Gran Hotel Fortaleza Roja", "Desembarco del Rey", "Lujo real en el corazón de la capital. Acceso exclusivo a los jardines. Cuidado con los pajaritos de Varys.", 5, "500.00"),
                    buildHotel("Hostal Lecho de Pulgas", "Desembarco del Rey", "Económico, ruidoso y con olor a cuenco de marrón. Ideal para mochileros sin dragones de oro.", 1, "5.00"),
                    buildHotel("La Doncella Tímida", "Puerto Real", "Taberna y posada discreta cerca del puerto. Perfecta para reuniones secretas.", 3, "40.00"),
                    buildHotel("Resort Rocadragón", "Rocadragón", "Arquitectura valyria antigua. Ambiente volcánico y mucha obsidiana. Algo húmedo.", 3, "90.00"),

                    buildHotel("Palacio Roca Casterly", "Occidente", "Tallado en la roca viva. Lujo obsceno, oro por todas partes. Las deudas se pagan por adelantado.", 5, "600.00"),
                    buildHotel("Posada de Lannisport", "Lannisport", "Ajetreo comercial y buen vino. Ideal para mercaderes y viajeros de negocios.", 3, "75.00"),

                    buildHotel("Jardines de Altojardín", "El Dominio", "El lugar más hermoso de Poniente. Flores, caballeros y torneos diarios. Comida exquisita.", 5, "250.00"),
                    buildHotel("La Ciudadela Study Rooms", "Antigua", "Silencio absoluto. Perfecto para estudiantes y maestres. WiFi prohibido, solo cuervos.", 2, "35.00"),
                    buildHotel("Palacio de los Jardines del Agua", "Dorne", "Lujo dorniense, piscinas de mármol y naranjos. El calor no es un problema aquí.", 5, "350.00"),
                    buildHotel("Torre de Lanza del Sol", "Dorne", "Comida picante, vino fuerte y vistas al mar. Pasión y duelos incluidos.", 4, "130.00"),

                    buildHotel("Motel de Pyke", "Islas del Hierro", "Pagamos el precio del hierro. Habitaciones húmedas, sin calefacción. Solo para duros hijos del mar.", 2, "20.00"),

                    buildHotel("La Casa de Blanco y Negro", "Braavos", "Un lugar tranquilo para descansar... eternamente. Servicio muy discreto. Valar Morghulis.", 5, "1000.00"),
                    buildHotel("El Titán de Braavos Luxury", "Braavos", "Vistas espectaculares a la laguna. El mejor marisco de Essos. Banco de Hierro a 5 minutos.", 4, "200.00"),
                    buildHotel("Mansión de Illyrio", "Pentos", "Queso, vino y camas de plumas de cisne. Excesivo en todos los sentidos.", 4, "180.00"),
                    buildHotel("Gran Pirámide de Meereen", "Meereen", "Vistas a la Bahía de los Esclavos. Suites en la cúspide. Cuidado con los dragones en el sótano.", 5, "400.00"),

                    buildHotel("Posada del Viajero", "Camino Real", null, 2, "20.00")

            );

            hotelRepository.saveAll(hoteles);
            log.info("Hoteles creados con éxito");
        }
    }

    private Hotel buildHotel(String nombre, String ubicacion, String descripcion, Integer estrellas, String precio) {
        Hotel hotel = new Hotel();
        hotel.setNombre(nombre);
        hotel.setUbicacion(ubicacion);
        hotel.setDescripcion(descripcion);
        hotel.setEstrellas(estrellas);
        hotel.setPrecioNoche(new BigDecimal(precio));
        return hotel;
    }
}
