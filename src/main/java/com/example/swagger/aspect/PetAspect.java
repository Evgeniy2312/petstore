package com.example.swagger.aspect;


import com.example.swagger.entity.Pet;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PetAspect {

    private final Logger logger = LoggerFactory.getLogger(PetAspect.class);

    @Pointcut("execution(public * com.example.swagger.controller.PetController.addPet(..)) && args(pet)")
    public void addPet(Pet pet) {}

    @Pointcut("execution(public * com.example.swagger.controller.PetController.delete(..)) && args(id)")
    public void delete(long id) {}

    @Pointcut("execution(public * com.example.swagger.controller.PetController.update(..)) && args(pet)")
    public void update(Pet pet) {}

    @Pointcut("execution(public * com.example.swagger.controller.PetController.getById(..)) && args(id)")
    public void getById(long id) {}

    @Pointcut("execution(public * com.example.swagger.controller.PetController.getByStatus(..)) && args(status)")
    public void getByStatus(String status) {}

    @Pointcut("execution(public * com.example.swagger.controller.PetController.getAll())")
    public void getAll() {}


    @After(value = "addPet(pet)", argNames = "pet")
    public void add(Pet pet){
        logger.info("Pet with name - {} was added", pet.getName() );
    }

    @After(value = "delete(id)", argNames = "id")
    public void del(long id){
        logger.info("Pet with id - {} was deleted", id );
    }

    @After(value = "update(pet)", argNames = "pet")
    public void upd(Pet pet){
        logger.info("Pet with name - {} was updated", pet.getName() );
    }

    @After(value = "getById(id)", argNames = "id")
    public void getId(long id){
        logger.info("Pet with id - {} was received", id);
    }

    @After(value = "getByStatus(status)", argNames = "status")
    public void getId(String status){
        logger.info("Pet with status - {} was received", status);
    }

    @After(value = "getAll()")
    public void all(){
        logger.info("All pet were received");
    }
}
