package ma.projects.rentcarsproject.Conrollers;

import lombok.AllArgsConstructor;
import ma.projects.rentcarsproject.entities.Car;
import ma.projects.rentcarsproject.exceptions.CarNotFoundException;
import ma.projects.rentcarsproject.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller @AllArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/home")
    public String index(Model model){
        return "index";
    }
    @GetMapping("/admin/dashboard")
    public String dashboard(Model model,
                            @RequestParam(name="page",defaultValue ="0") int page,
                            @RequestParam(name="size",defaultValue ="5")int size,
                            @RequestParam(name="keyword",defaultValue ="") String keyword){
        try {
            Page<Car> pages = carService.search(keyword, PageRequest.of(page, size));
            model.addAttribute("allcars",pages.getContent());
            model.addAttribute("pages",new int[pages.getTotalPages()]);
            model.addAttribute("currentPage",page);
            model.addAttribute("keyword",keyword);
        } catch (CarNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "admin/dashboard";
    }
    @PostMapping("/admin/delete")
    public String delete(Long id,String keyword,int page) {
        carService.deleteCar(id);
        return "redirect:/admin/dashboard?page="+page+"&keyword="+keyword;

    }

    @GetMapping("/admin/editCar")
    public String editCar(Model model, Long id) {
        Optional<Car> carOptional = carService.getCarById(id);
        Car car = carOptional.orElse(null); // ou carOptional.orElseThrow(() -> new RuntimeException("Car not found"));
        List<String> imageUrls = car.getImageUrls();
        model.addAttribute("imageUrls",imageUrls);
        model.addAttribute("car", car);
        return "admin/editCar";
    }
    @PostMapping("/admin/saveCar")
    public String saveCar(@ModelAttribute Car car, @RequestParam("files") MultipartFile[] files) {
        List<String> newImageUrls = new ArrayList<>();

        if (files != null) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    try {
                        byte[] bytes = file.getBytes();
                        String fileName = "Car" + System.currentTimeMillis() + ".jpg";
                        Path path = Paths.get("src/main/resources/static/carPhotos", fileName); // Use src/main/resources/static as the base path

                        Files.write(path, bytes);
                        newImageUrls.add(fileName); // Add the new image URL to the list
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // Remplacer les URL d'images existantes par les nouvelles URL (si des nouvelles ont été ajoutées)
        if (!newImageUrls.isEmpty()) {
            car.setImageUrls(newImageUrls);
        }

        carService.updateCar(car); // Utilisez une méthode de mise à jour appropriée de votre service

        return "redirect:/admin/dashboard?keyword=" + car.getMake();
    }

    @PostMapping("/admin/deleteImage")
    String deleteImage(@RequestParam String imageUrl, @RequestParam("id") Long carId){
        carService.removeImageFromCar(carId, imageUrl);
        return "redirect:/admin/editCar?id="+carId;
    }

    @GetMapping("/sidbar")
    public String sidbar(){
        return "navs/sideBar";
    }


}
