package ma.projects.rentcarsproject.Conrollers;

import ma.projects.rentcarsproject.entities.Car;
import ma.projects.rentcarsproject.exceptions.CarServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleCarServiceException(CarServiceException ex, Model model) {
        model.addAttribute("errorMessage", "Car Does Not Exist" );
        return "admin/dashboard";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Une erreur inattendue s'est produite : " + ex.getMessage());
        return "error";
    }
}
