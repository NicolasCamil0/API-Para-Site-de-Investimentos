package com.mio.todosimple.Selic.Controller;

import com.mio.todosimple.Selic.Service.SelicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/selic")
public class SelicController {
    private final SelicService selicService;

    public SelicController(SelicService selicService) {
        this.selicService = selicService;
    }

    @GetMapping("/ultima")
    public Double getUltimaSelic() {
        return selicService.getUltimaSelic();
    }
}
