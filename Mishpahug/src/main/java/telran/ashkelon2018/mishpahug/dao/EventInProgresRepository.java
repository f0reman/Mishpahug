package telran.ashkelon2018.mishpahug.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.ashkelon2018.mishpahug.domain.EventForWork;

public interface EventInProgresRepository extends JpaRepository<EventForWork, Long>{

@Query(value ="SELECT * from event_for_work e WHERE e.date_time_start <:timeTemp and e.status like 'in_process'", nativeQuery = true )
//	@Query("SELECT e from EventForWork e WHERE e.dateTimeStart <:timeTemp") //and e.status like ':status'")
	Set<EventForWork> findAllBadEvent(@Param("timeTemp") long timeTemp);//,@Param("status") Status status);
//,
//@Query(value ="DELETE e from Event e WHERE e.date_time_start <:timeTemp", nativeQuery = true)
//void deleteAllBad(long timeTemp);

//@Query(value ="DELETE e from Event e WHERE e.date_time_finish <:timeNow", nativeQuery = true)
//void deleteAllDone(long timeTemp);

@Query(value ="SELECT e from event_for_work e WHERE e.date_time_finish <:timeNows and e.status like 'pending'", nativeQuery = true)
HashSet<EventForWork> findAllDoneEvent(long timeNows);
//,@Param("status") Status status
}
