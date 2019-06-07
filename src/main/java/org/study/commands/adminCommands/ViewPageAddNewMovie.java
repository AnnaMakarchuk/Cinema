package org.study.commands.adminCommands;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.study.dto.MovieDto;
import org.study.commands.Command;
import org.study.facade.MovieFacade;
import org.study.factories.FacadeFactory;

public class ViewPageAddNewMovie implements Command {
    private static final Logger LOG = Logger.getLogger(ViewPageAddNewMovie.class);

    private MovieFacade movieFacade;

    public ViewPageAddNewMovie() {
        this.movieFacade = FacadeFactory.getInstance().getMovieFacade();
    }

    /**
     * this method is a command for view on admin add movie page the Movie dto list
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<MovieDto> movieDtoList = movieFacade.getAllMovies(true);
        LOG.info("List MovieDTO for add movie page was selected");
        request.setAttribute("movies", movieDtoList);
        return "pages/admin/admin_add_movie.jsp";
    }
}
