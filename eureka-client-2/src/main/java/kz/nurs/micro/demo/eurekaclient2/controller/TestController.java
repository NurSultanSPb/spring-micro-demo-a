package kz.nurs.micro.demo.eurekaclient2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main_second")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "changed test";
    }
}
