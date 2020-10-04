package pl.fintech.dragonsinvestments.investmentcalculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Example controller")
@RestController
@RequestMapping("/")
public class ExampleController {

    @GetMapping
    public String example() {
        return "Hello FinTech!";
    }

}
