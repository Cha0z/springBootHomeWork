package beans.controller;


import beans.daos.BookingDAO;
import beans.daos.EventDAO;
import beans.daos.UserDAO;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.models.domain.BigTransaction;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.istack.internal.NotNull;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@EnableWebMvc
@Controller
public class BookController {


    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private UserDAO userDAO;

    @Secured("REGISTERED_USER")
    @GetMapping(value = "/count")
    public String countUserTicket(@ModelAttribute ModelMap model, @RequestParam User user) {

        Long count = bookingDAO.countTickets(user);

        model.addAttribute("count", count);

        return "index";
    }

    @Secured("REGISTERED_USER")
    @GetMapping(value = "/bookTicket")
    public String countUserTicket(@ModelAttribute ModelMap model, @RequestParam User user, @RequestParam Ticket inCommTicket) throws Exception {

        Ticket ticket = null;
        try {
            ticket = bookingDAO.create(user, inCommTicket);
        } catch (Exception e) {
            throw new Exception("ticket not found");
        }


        model.addAttribute("ticket", ticket);

        return "index";
    }

    @Secured("REGISTERED_USER")
    @GetMapping(value = "/getAllTicket")
    public void getAllTickets(@ModelAttribute ModelMap model, HttpServletResponse response) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        java.util.List<Ticket> ticketList = null;

        ticketList = bookingDAO.getAllTickets();

        generatePdfFile(out, ticketList);
        streamReport(response, out.toByteArray(), "allTicket.dpf");

    }

    private void generatePdfFile(ByteArrayOutputStream out, java.util.List<Ticket> ticketList) {
        List tickets = new List();
        ticketList.forEach(ticket -> tickets.add(ticket.toString()));

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(tickets);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Secured({"REGISTERED_USER", "BOOKING_MANAGER"})
    @GetMapping(value = "/getAllTicketByEvent")
    public void getAllTickets(@ModelAttribute ModelMap model, HttpServletResponse response, @RequestParam Event event) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        java.util.List<Ticket> ticketList = bookingDAO.getTickets(event);

        generatePdfFile(out, ticketList);
        streamReport(response, out.toByteArray(), "allTicketByEvent.pdf");

    }

    @Secured({"REGISTERED_USER", "BOOKING_MANAGER"})
    @GetMapping(value = "/getAllTicketByUser")
    public void getAllTickets(@ModelAttribute ModelMap model, HttpServletResponse response, @RequestParam User user) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        java.util.List<Ticket> ticketList = bookingDAO.getTickets(user);

        generatePdfFile(out, ticketList);
        streamReport(response, out.toByteArray(), "allTicketByUser.pdf");

    }

    @Secured("REGISTERED_USER")
    @DeleteMapping(value = "/deleteBooking")
    public void deleteTicket(@RequestParam User user, @RequestParam("booking") Ticket ticket) {
        bookingDAO.delete(user, ticket);
    }


    protected void streamReport(HttpServletResponse response, byte[] data, String name) {

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        response.setContentLength(data.length);

        try {
            response.getOutputStream().write(data);
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("Error during sending");
        }

    }

    @ExceptionHandler(Exception.class)
    public String ticketNotFound(Exception ex) {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("Exception", ex);
        return "index";
    }

    @ExceptionHandler(IOException.class)
    public String handleIOException(Exception ex) {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("Exception", ex);
        return "index";
    }

    @Secured("REGISTERED_USER")
    @RequestMapping(value = "/addUsersAndEvents", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    @ResponseBody
    public void addUserAndEvent(
            @RequestPart("properties") ConnectionProperties properties,
            @RequestPart("file") @NotNull MultipartFile file) throws IOException {

        String fileInString = new String(file.getBytes());

        ObjectMapper mapper = new ObjectMapper();

        BigTransaction bigTransaction = mapper.readValue(fileInString, BigTransaction.class);

        bigTransaction.getEvents().forEach(event -> eventDAO.create(event));
        bigTransaction.getUsers().forEach(user -> userDAO.create(user));

    }


}
