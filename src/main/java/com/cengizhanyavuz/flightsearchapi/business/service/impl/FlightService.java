package com.cengizhanyavuz.flightsearchapi.business.service.impl;

import com.cengizhanyavuz.flightsearchapi.bean.ModelMapperBean;
import com.cengizhanyavuz.flightsearchapi.business.dto.FlightDTO;
import com.cengizhanyavuz.flightsearchapi.business.dto.FlightSearchResult;
import com.cengizhanyavuz.flightsearchapi.business.dto.api.flight.FlightDetail;
import com.cengizhanyavuz.flightsearchapi.business.service.IFlightService;
import com.cengizhanyavuz.flightsearchapi.data.entity.Airport;
import com.cengizhanyavuz.flightsearchapi.data.entity.Flight;
import com.cengizhanyavuz.flightsearchapi.data.repository.IAirportRepository;
import com.cengizhanyavuz.flightsearchapi.data.repository.IFlightRepository;
import com.cengizhanyavuz.flightsearchapi.exception.CustomException;
import com.cengizhanyavuz.flightsearchapi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2
@Service
public class FlightService implements IFlightService<FlightDTO, Flight> {

    private final ModelMapperBean modelMapperBean;
    private final IFlightRepository flightRepository;
    private final IAirportRepository airportRepository;

    @Override
    public FlightDTO entityToDto(Flight flight) {
        return modelMapperBean.modelMapperMethod().map(flight, FlightDTO.class);
    }

    @Override
    public Flight dtoToEntity(FlightDTO flightDTO) {
        return modelMapperBean.modelMapperMethod().map(flightDTO, Flight.class);
    }

    @Override
    public void flightServiceCreate(FlightDetail[] flightDetails) {
        List<Flight> flightList = new ArrayList<>();
        for (FlightDetail flightDetail : flightDetails) {
            if (flightRepository.findByFlightId(flightDetail.flightId()).isEmpty()) {
                Flight flight = save(flightDetail);
                flightList.add(flight);
            }
        }
        if (flightList.size() > 0) {
            flightRepository.saveAll(flightList);
        }
    }

    @Override
    public void flightServiceUpdate(FlightDetail[] flightDetails) {
        for (FlightDetail flightDetail : flightDetails) {
            FlightDTO flightDTO = flightServiceFindById(flightDetail.flightId());
            if (flightDTO != null) {
                Flight flight = save(flightDetail);
                flightRepository.save(flight);
            }
        }
    }
    @Override
    public void flightServiceDelete(FlightDetail[] flightDetails) {
        for (FlightDetail flightDetail : flightDetails) {
            Optional<Flight> flight = flightRepository.findByFlightId(flightDetail.flightId());
            flight.ifPresent(flightRepository::delete);
        }
    }

    private Flight save(FlightDetail flightDetail) {
        Airport departureAirport = airportRepository.findByAirportId(flightDetail.departureAirportId()).orElseThrow();
        Airport arrivalAirport = airportRepository.findByAirportId(flightDetail.arrivalAirportId()).orElseThrow();
        Flight flight = flightRepository.findByFlightId(flightDetail.flightId()).orElseGet(Flight::new);
        flight.setFlightId(flightDetail.flightId());
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureDateTime(LocalDateTime.parse(flightDetail.departureDateTime()));
        flight.setReturnDateTime(LocalDateTime.parse(flightDetail.returnDateTime()));
        flight.setPrice(flightDetail.price());
        return flight;
    }

    public List<FlightDTO> flightServiceList() {
        List<Flight> flights = (List<Flight>) flightRepository.findAll();
        List<FlightDTO> flightDTOList = new ArrayList<>();
        for (Flight flight : flights) {
            flightDTOList.add(entityToDto(flight));
        }
        return flightDTOList;
    }


    @Override
    public FlightDTO flightServiceFindById(Long flightId) {
        Flight flight = null;
        if (flightId != null) {
            flight = flightRepository.findByFlightId(flightId)
                    .orElseThrow(() -> new ResourceNotFoundException("Unable to find the flight with ID " + flightId));
        } else {
            throw new CustomException("flight id is null");
        }
        return entityToDto(flight);
    }

    @Override
    public FlightDTO flightServiceUpdate(Long id, FlightDTO flightDTO) {
        FlightDTO flightFindDto = flightServiceFindById(id);
        Airport departureAirport = findAirportById(flightDTO.getDepartureAirportId());
        Airport arrivalAirport = findAirportById(flightDTO.getArrivalAirportId());
        if (flightFindDto != null) {
            Flight flight = dtoToEntity(flightFindDto);
            flight.setId(id);
            flight.setDepartureAirport(departureAirport);
            flight.setArrivalAirport(arrivalAirport);
            flight.setDepartureDateTime(flightDTO.getDepartureDateTime());
            flight.setReturnDateTime(flightDTO.getReturnDateTime());
            flight.setPrice(flightDTO.getPrice());
            flightRepository.save(flight);
            flightDTO.setId(flight.getId());
        } else {
            throw new NullPointerException("Unable to find the flight with ID " + id);
        }
        return flightDTO;
    }

    @Override
    public FlightDTO flightServiceDeleteById(Long id) {
        FlightDTO flightFindDto = flightServiceFindById(id);
        if (flightFindDto != null) {
            Flight flight = dtoToEntity(flightFindDto);
            flightRepository.delete(flight);
        } else {
            throw new NullPointerException("Unable to find the flight with ID " + id);
        }
        return flightFindDto;
    }

    @Override
    public FlightSearchResult searchFlights(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDateTime,
                                            Optional<LocalDateTime> returnDateTime) {
        List<FlightDTO> outboundFlights = searchFlights(departureAirportId, arrivalAirportId, departureDateTime);
        List<FlightDTO> returnFlights = null;
        if (returnDateTime.isPresent()) {
            returnFlights = searchFlights(arrivalAirportId, departureAirportId, returnDateTime.get());
        }
        return new FlightSearchResult(outboundFlights, returnFlights);
    }

    private List<FlightDTO> searchFlights(Long departureCity, Long arrivalCity, LocalDateTime departureDate) {
        Iterable<Flight> flights = flightRepository
                .findAllByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTime(departureCity, arrivalCity, departureDate);
        List<FlightDTO> flightDtoList = new ArrayList<>();
        for (Flight flight : flights) {
            flightDtoList.add(entityToDto(flight));
        }
        return flightDtoList;
    }

    private Airport findAirportById(Long id) {
        if (id != null) {
            return airportRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Unable to find the airport with ID " + id));
        } else {
            throw new CustomException("airport id is null");
        }
    }

    private Optional<Flight> findFlightById(List<Flight> flights, Long flightId) {
        return flights.stream()
                .filter(flight -> flight.getFlightId().equals(flightId))
                .findFirst();
    }
}
