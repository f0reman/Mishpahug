package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public  class FiltersDto{
  LocalDate dateFrom;
  LocalDate dateTo;
  Set<String> holidays;
  Set<String>confession ;
  Set<String> food;
 }

