package fr.uga.l3miage.photonum.impression;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping(value = "/api/v1", produces = "application/json")
public class ImpressionController {


}
