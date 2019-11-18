package com.test.controllers;

import com.test.entities.Organization;
import com.test.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.test.enums.MessageStatus.*;

@Controller
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    /**
     * <p>Метод, осуществляющий переход на страницу добавления организации.</p>
     * @return addOrganization.jsp
     */
    @RequestMapping(value = "/addOrganization", method = RequestMethod.GET)
    public ModelAndView orgIndex() {
        return new ModelAndView("addOrganization", "organization", new Organization());
    }

    /**
     * <p>Вызов метода по добавлению организации, после проверки ИНН и ОГРН на корректность (на случай, если данные отправят вручную).</p>
     * @param inn ИНН организации;
     * @param ogrn ОГРН организации;
     * @param name полное наименование организации;
     * @param address адрес организации;
     * @param model модель для отправки на фронт.
     * @return addOrganization.jsp
     */
    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(@RequestParam("inn") String inn,
                                  @RequestParam("ogrn") String ogrn,
                                  @RequestParam("name") String name,
                                  @RequestParam("address") String address,
                                  Model model) {
        if ((inn.length() != 10 && inn.length() != 12) || (ogrn.length() != 13 && ogrn.length() != 15)) {
            model.addAttribute(INN_OR_OGRN_IS_ERROR.name(), true);
            model.addAttribute("organization", new Organization());
        } else {
            Organization duplicate = organizationService.checkToDuplicate(new Organization(inn, ogrn, name, address));
            if (duplicate != null) {
                model.addAttribute(ORGANIZATION_ALREADY_EXIST.name(), true);
                model.addAttribute(duplicate);
            } else {
                organizationService.addOrganization(inn, ogrn, name, address);
                model.addAttribute("organization", new Organization());
            }
        }
        return "addOrganization";
    }

    /**
     * <p>Получение списка организаций.</p>
     * @param model модель для отправки на фронт.
     * @return listOrganization.jsp
     */
    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    public String listOrganizations(Model model) {
        List<Organization> organizations = organizationService.getAllOrganization();
        if (organizations.isEmpty()) {
            model.addAttribute("organizationList", null);
            model.addAttribute(NO_ORGANIZATON.name(), true);
        } else {
            model.addAttribute("organizationList", organizations);
        }
        model.addAttribute("findOrganization", new Organization());
        return "listOrganizations";
    }

    /**
     * <p>Получение списка найденных организаций.</p>
     * @param inn ИНН организации;
     * @param ogrn ОГРН организации;
     * @param name полное наименование организации;
     * @param address адрес организации;
     * @param model модель для отправки на фронт.
     * @return listOrganization.jsp
     */
    @RequestMapping(value = "/organizations", method = RequestMethod.POST)
    public String findOrganization(@RequestParam(value = "inn", required = false) String inn,
                                   @RequestParam(value = "ogrn", required = false) String ogrn,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "address", required = false) String address,
                                   Model model) {
        List<Organization> organizations = organizationService.findOrganizatons(inn, ogrn, name, address);
        if (organizations == null || organizations.isEmpty()) {
            List<Organization> organizationList = organizationService.getAllOrganization();
            model.addAttribute("getOrganizations", null);
            model.addAttribute(ORGANIZATION_NOT_FOUND.name(), true);
            model.addAttribute("organizationList", organizationList);
        } else {
            model.addAttribute("getOrganizations", organizations);
        }
        model.addAttribute("findOrganization", new Organization());
        return "listOrganizations";
    }
}
