package com.test.repositories;

import com.test.entities.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    @Query(value = "SELECT * FROM organization where" +
                   " (:orgName like '' or name like CONCAT('%', :orgName, '%'))" +
                   " and (:orgINN like '' or inn like CONCAT(:orgINN, '%'))" +
                   " and (:orgOGRN like '' or ogrn like CONCAT(:orgOGRN, '%'))" +
                   " and (:orgAddress like '' or address like CONCAT('%', :orgAddress, '%'))", nativeQuery = true)
    List<Organization> findOrganizations(@Param("orgName") String name,
                                         @Param("orgINN") String inn,
                                         @Param("orgOGRN") String ogrn,
                                         @Param("orgAddress") String address);
    Organization findByInn(String inn);
    Organization findByOgrn(String ogrn);
}
