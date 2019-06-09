package org.study.commands.adminCommands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.commands.Command;
import org.study.dto.AdministratorDto;
import org.study.models.enums.UserRole;

public abstract class AbstractAdminCommand implements Command {
    private static final Logger LOG = Logger.getLogger(AbstractAdminCommand.class);

    abstract public String execute(HttpServletRequest request);

    /**
     * this method check access permission for administrator
     */
    @Override
    public boolean checkPermissions(HttpServletRequest request) {
        AdministratorDto administratorDto = (AdministratorDto) request.getSession().getAttribute("admin");
        boolean validationResult =
                !Objects.isNull(administratorDto) && administratorDto.getUserRole() == UserRole.ADMINISTRATOR;
        LOG.info("Permission validator return " + validationResult);
        return validationResult;
    }
}
