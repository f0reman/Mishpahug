package telran.ashkelon2018.mishpahug.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.ashkelon2018.mishpahug.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

	@Query(value = "SELECT count(*) from event e INNER JOIN person p ON p.id=:idp WHERE e.date_time_start between :start and :finish OR e.date_time_finish between :start and :finish "
			+ "OR e.date_time_start = :start", nativeQuery = true)
	int checkEvent(@Param("start") long start, @Param("finish") long finish, @Param("idp") String id);

	@Query(value = "SELECT * from event e WHERE  :id MEMBER OF e.subscribes", nativeQuery = true)
	Event findAndRemoveOtherSubscibes(String id);

	
//	@Query("SELECT e from Event e INNER JOIN Person p ON p.id=:idp")
//	Set<Event> checkEvent(@Param("idp") String id);

}
