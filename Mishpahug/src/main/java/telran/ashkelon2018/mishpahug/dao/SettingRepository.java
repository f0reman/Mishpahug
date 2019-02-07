package telran.ashkelon2018.mishpahug.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.ashkelon2018.mishpahug.domain.SettingsProject;

public interface SettingRepository extends JpaRepository<SettingsProject, String>{

	

}
