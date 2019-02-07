package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class EventMySubDto {
    long eventId;
    String title;
    LocalDate date;
    LocalTime time;
    int duration;
    String status;

    Set<ParticipantDto> participantsDto; 
}
