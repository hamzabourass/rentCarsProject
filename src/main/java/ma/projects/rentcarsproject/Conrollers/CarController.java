package ma.projects.rentcarsproject.Conrollers;

import lombok.AllArgsConstructor;
import ma.projects.rentcarsproject.entities.Car;
import ma.projects.rentcarsproject.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller @AllArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/home")
    public String index(Model model){
        return "index";
    }
    @GetMapping("/admin/dashboard")
    public String dashboard(Model model){
        return "dashboard";
    }


}
