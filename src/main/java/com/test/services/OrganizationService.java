package com.test.services;

import com.test.entities.Organization;
import com.test.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("organizationService")
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    /**
     * <p>Метод для добавления организации в БД.</p>
     * @param inn ИНН организации;
     * @param ogrn ОГРН организации;
     * @param name полное наименование организации;
     * @param address адрес организации.
     */
    public void addOrganization(String inn, String ogrn, String name, String address) {
        if (inn != null && ogrn != null && !name.isEmpty() && !address.isEmpty()) {
            Organization organization = new Organization();
            organization.setInn(inn);
            organization.setOgrn(ogrn);
            organization.setName(name);
            organization.setAddress(address);
            organizationRepository.save(organization);
        }
    }

    /**
     * <p>Метод для получения списка организаций из БД.</p>
     * @return список организаций.
     */
    public List<Organization> getAllOrganization() {
        List<Organization> organizations = new ArrayList<Organization>();
        Iterable<Organization> organizationIterable = organizationRepository.findAll();
        for (Organization organization : organizationIterable) {
            organizations.add(organization);
        }
        return organizations;
    }

    /**
     * <p>Метод для параметризованного поиска организации в БД.</p>
     * @param inn ИНН организации;
     * @param ogrn ОГРН организации;
     * @param name полное наименование организации;
     * @param address адрес организации.
     * @return список организаций, т.к. если искать по адресу, то организаций может быть больше, чем одна.
     */
    public List<Organization> findOrganizatons(String inn, String ogrn, String name, String address) {
        return organizationRepository.findOrganizations(name, inn, ogrn, address);
    }

    /**
     * <p>Метод, осуществляющий поиск существующей организации, с целью исключить дублирование.</p>
     * @param organization организация, отправленная пользователем;
     * @return организацию, с указанием параметра, для которого происходит дублирование.
     */
    public Organization checkToDuplicate(Organization organization) {
        if (organizationRepository.findByInn(organization.getInn()) != null) {
            organization.setInn(null);
            return organization;
        } else if (organizationRepository.findByOgrn(organization.getOgrn()) != null) {
            organization.setOgrn(null);
            return organization;
        } else {
            return null;
        }
    }
}
