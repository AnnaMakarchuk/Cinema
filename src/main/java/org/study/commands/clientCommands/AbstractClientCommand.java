package org.study.commands.clientCommands;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.commands.Command;
import org.study.dto.RegisteredUserDto;
import org.study.models.enums.UserRole;

public abstract class AbstractClientCommand implements Command {
    private static final Logger LOG = Logger.getLogger(AbstractClientCommand.class);

    abstract public String execute(HttpServletRequest request);

    /**
     * this method check access permission for registered user
     */
    @Override
    public boolean checkPermissions(HttpServletRequest request) {
        RegisteredUserDto registeredUserDto = (RegisteredUserDto) request.getSession().getAttribute("user");
        boolean validationResult =
                !Objects.isNull(registeredUserDto) && registeredUserDto.getUserRole() == UserRole.CLIENT;
        LOG.info("Permission validator return " + validationResult);
        return validationResult;
    }
}
