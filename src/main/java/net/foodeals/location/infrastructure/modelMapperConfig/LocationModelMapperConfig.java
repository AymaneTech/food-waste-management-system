package net.foodeals.location.infrastructure.modelMapperConfig;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.foodeals.location.application.dtos.responses.AddressResponse;
import net.foodeals.location.application.dtos.responses.CityResponse;
import net.foodeals.location.application.dtos.responses.CountryResponse;
import net.foodeals.location.application.dtos.responses.StateResponse;
import net.foodeals.location.domain.entities.Address;
import net.foodeals.location.domain.entities.City;
import net.foodeals.location.domain.entities.Country;
import net.foodeals.location.domain.entities.State;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LocationModelMapperConfig {

    private final ModelMapper mapper;

    @PostConstruct
    public void configure() {
        mapper.addConverter(context -> {
            final Country country = context.getSource();
            return new CountryResponse(country.getId(), country.getName(), country.getCode());
        }, Country.class, CountryResponse.class);

        mapper.addConverter(context -> {
            final State state = context.getSource();
            final CountryResponse countryResponse = mapper.map(state.getCountry(), CountryResponse.class);
            return new StateResponse(state.getId(), state.getName(), state.getCode(), countryResponse);
        }, State.class, StateResponse.class);

        mapper.addConverter(context -> {
            final City city = context.getSource();
            final StateResponse stateResponse = mapper.map(city.getState(), StateResponse.class);
            return new CityResponse(city.getId(), city.getName(), city.getCode(), stateResponse);
        }, City.class, CityResponse.class);

        mapper.addConverter(context -> {
            final Address address = context.getSource();
            final CityResponse cityResponse = mapper.map(address.getCity(), CityResponse.class);
            return new AddressResponse(address.getId(), address.getAddress(), address.getExtraAddress(), address.getZip(), address.getCoordinates(), cityResponse);
        }, Address.class, AddressResponse.class);
    }
}
