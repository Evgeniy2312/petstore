package com.example.swagger.controller;


import com.example.swagger.entity.Order;
import com.example.swagger.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;



@Transactional
@RestController
@Validated
@RequestMapping("/store")
public class StoreController {

    private final StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    @PostMapping("/addOrder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        if(service.addOrder(order)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @Transactional(readOnly = true)
    @GetMapping("/getByStatus/{status}")
    public ResponseEntity<List<Order>> getByStatus(@PathVariable @NotEmpty @NotBlank @NotNull String status){
        if(!service.getOrderByStatus(status).isEmpty()){
            return new ResponseEntity<>(service.getOrderByStatus(status), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional(readOnly = true)
    @GetMapping("/getById/{id}")
    public ResponseEntity<Order> getById(@PathVariable long id){
        if(service.getById(id).isPresent()){
            return new ResponseEntity<>(service.getById(id).get(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Order> delete(@PathVariable long id){
        if(service.deleteOrder(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional(readOnly = true)
    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getAll(){
        if(!service.getAll().isEmpty()){
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
