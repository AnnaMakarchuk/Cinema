package org.study.commands;

import org.apache.log4j.Logger;
import org.study.dto.MovieDTO;
import org.study.facade.MovieFacade;
import org.study.factories.FacadeFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MainPageView implements Command {
    private static final Logger LOG = Logger.getLogger(MainPageView.class);

    private MovieFacade movieFacade = FacadeFactory.getInstance().getMovieFacade();

    /**
     * this method create Movie dto list for main page
     *
     * @param request
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<MovieDTO> movieDTOList = movieFacade.getAllMovies();
        LOG.info("List MovieDTO for main page was created");
        LOG.info("movie= " + movieDTOList.get(0).toString());
        request.setAttribute("movies", movieDTOList);
        LOG.info("Attribute was setted");

        return "jsp/homepage.jsp";
    }
}
