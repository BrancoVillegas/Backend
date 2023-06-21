package com.finanzas.backend.controller;


import com.finanzas.backend.entities.Consultation;
import com.finanzas.backend.entities.User;
import com.finanzas.backend.service.IConsultationService;
import com.finanzas.backend.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/consultations")
@Api(tags = "Consultation", value = "Web Service RESTFul of Consultation")
public class ConsultationController {

    private final IConsultationService consultationService;
    private final IUserService userService;


    public ConsultationController(IConsultationService consultationService, IUserService userService) {
        this.consultationService = consultationService;
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Consultation", notes = "Method to list all Consultation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Consultation founds"),
            @ApiResponse(code = 404, message = "Consultation Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Consultation>> findAllConsultation(){
        try {
            List<Consultation> consultations = consultationService.getAll();
            if(consultations.size()>0)
                return new ResponseEntity<>(consultations, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Consultation by Id", notes = "Method for find a Consultation by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consultation found by Id"),
            @ApiResponse(code = 404, message = "Consultation Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Consultation> findById (@PathVariable ("id") Long id){
        try {
            Optional<Consultation> consultation = consultationService.getById(id);
            if(!consultation.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(consultation.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Consultation by User Id", notes = "Method for find a Consultation by User Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consultation found by User Id"),
            @ApiResponse(code = 404, message = "Consultation Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Consultation>> findByUserId (@PathVariable ("id") Long id){
        try {
            Optional<User> user = userService.getById(id);
            if(!user.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<Consultation> consultation = consultationService.findByUserId(id);
                return new ResponseEntity<>(consultation, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Consultation", notes = "Method to create Consultation")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Consultation created"),
            @ApiResponse(code = 404, message = "Consultation not created"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    public ResponseEntity<Consultation> insertConsultation(@PathVariable("id") Long userId, @RequestBody Consultation consultation){
        try {
            Optional<User> user = userService.getById(userId);
            if(!user.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                consultation.setUser(user.get());
                Consultation consultationNew = consultationService.save(consultation);
                return ResponseEntity.status(HttpStatus.CREATED).body(consultationNew);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Consultation data", notes = "Method to update Consultation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consultation updated"),
            @ApiResponse(code = 404, message = "Consultation not updated"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    public ResponseEntity<Consultation> updateConsultation(@PathVariable("id") Long id, @RequestBody Consultation consultation){
        try {
            Optional<Consultation> consultationOld = consultationService.getById(id);
            if(!consultationOld.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                consultation.setId(id);
                consultationService.save(consultation);
                return new ResponseEntity<>(consultation, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Consultation", notes = "Method to delete Consultation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consultation deleted"),
            @ApiResponse(code = 404, message = "Consultation not deleted"),
            @ApiResponse(code = 500, message = "Consultation not found")
    })
    public ResponseEntity<Consultation> deleteConsultation(@PathVariable("id") Long id) {
        try {
            Optional<Consultation> consultationDelete = consultationService.getById(id);
            if (!consultationDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                consultationService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
