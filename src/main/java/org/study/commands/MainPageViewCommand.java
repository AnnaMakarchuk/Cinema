package org.study.commands;

import org.apache.log4j.Logger;
import org.study.dto.MovieDto;
import org.study.facade.MovieFacade;
import org.study.factories.FacadeFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.study.utils.ParametersNames;

public class MainPageViewCommand implements Command {
    private static final Logger LOG = Logger.getLogger(MainPageViewCommand.class);

    private MovieFacade movieFacade;

    public MainPageViewCommand() {
        this.movieFacade = FacadeFactory.getInstance().getMovieFacade();
    }

    /**
     * this method is a command for view on pain page the Movie dto list
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<MovieDto> movieDtoList = movieFacade.getAllMovies(true);
        LOG.info("List MovieDTO for main page was obtained");
        request.setAttribute(ParametersNames.MOVIES, movieDtoList);
        return "pages/homepage.jsp";
    }

    /**
     * this method always return true for indicated command. This pages is in access for any unregistered user
     */
    @Override
    public boolean checkPermissions(HttpServletRequest request) {
        return true;
    }
}
