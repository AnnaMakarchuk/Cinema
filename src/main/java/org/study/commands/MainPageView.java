package org.study.commands;

import org.apache.log4j.Logger;
import org.study.dto.MovieDto;
import org.study.facade.MovieFacade;
import org.study.factories.FacadeFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MainPageView implements Command {
    private static final Logger LOG = Logger.getLogger(MainPageView.class);

    private MovieFacade movieFacade;

    public MainPageView() {
        this.movieFacade = FacadeFactory.getInstance().getMovieFacade();
    }

    /**
     * this method is a command for view on pain page the Movie dto list
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<MovieDto> movieDtoList = movieFacade.getAllMovies(true);
        LOG.info("List MovieDTO for main page was obtained");
        request.setAttribute("movies", movieDtoList);
        return "pages/homepage.jsp";
    }
}
