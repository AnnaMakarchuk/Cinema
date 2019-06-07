package org.study.tags;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.log4j.Logger;
import org.study.facade.TicketFacade;


public class OccupiedColorTag extends SimpleTagSupport {
    private static final Logger LOG = Logger.getLogger(OccupiedColorTag.class);

    private int scheduleId;
    private TicketFacade ticketFacade;
    private HttpServletRequest request;
    private String color;

//    public OccupiedColorTag() {
//        this.ticketFacade = FacadeFactory.getInstance().getTicketFacade();
//    }

    private void setColor(String color) {
        this.color = color;
    }

    @Override
    public void doTag() throws JspException, IOException {
//        List<PlaceDto> occupiedPlaces = ticketFacade.getAllOccupiedPlacesBySchedule(scheduleId);
//        LOG.info("Get list of occupiedPlaces");
//        for (PlaceDto place : occupiedPlaces) {
//            setColor(color);
//        }
    }
}
