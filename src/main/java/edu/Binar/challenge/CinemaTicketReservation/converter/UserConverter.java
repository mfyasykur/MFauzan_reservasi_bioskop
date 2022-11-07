package edu.Binar.challenge.CinemaTicketReservation.converter;

import edu.Binar.challenge.CinemaTicketReservation.dto.UserDto;
import edu.Binar.challenge.CinemaTicketReservation.model.User;
import org.modelmapper.ModelMapper;

public class UserConverter {

    private UserConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static User convertDtoToEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    public static UserDto convertEntityToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
