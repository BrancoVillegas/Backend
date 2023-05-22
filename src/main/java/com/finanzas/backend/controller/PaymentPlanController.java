package com.finanzas.backend.controller;


import com.finanzas.backend.entities.InputInformation;
import com.finanzas.backend.entities.PaymentPlan;
import com.finanzas.backend.entities.User;
import com.finanzas.backend.service.IInputInformationService;
import com.finanzas.backend.service.IPaymentPlanService;
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
@RequestMapping("/api/payment-plan")
@Api(tags = "Payment Plan", value = "Web Service RESTFul of Payment Plan")
public class PaymentPlanController {

    private final IPaymentPlanService paymentPlanService;

    private final IUserService userService;

    private final IInputInformationService inputInformationService;

    public PaymentPlanController(IPaymentPlanService paymentPlanService, IUserService userService, IInputInformationService inputInformationService) {
        this.paymentPlanService = paymentPlanService;
        this.userService = userService;
        this.inputInformationService = inputInformationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Payment Plan", notes = "Method to list all Payment Plan")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Payment Plan founds"),
            @ApiResponse(code = 404, message = "Payment Plan Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<PaymentPlan>> findAllPaymentPlan(){
        try {
            List<PaymentPlan> paymentPlans = paymentPlanService.getAll();
            if(paymentPlans.size()>0)
                return new ResponseEntity<>(paymentPlans, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Payment Plan by Id", notes = "Method for find a Payment Plan by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Payment Plan found by Id"),
            @ApiResponse(code = 404, message = "Payment Plan Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<PaymentPlan> findById (@PathVariable("id") Long id){
        try {
            Optional<PaymentPlan> paymentPlan = paymentPlanService.getById(id);
            if(!paymentPlan.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(paymentPlan.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Payment Plan by User Id", notes = "Method for find a Payment Plan by User Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Payment Plan found by User Id"),
            @ApiResponse(code = 404, message = "Payment Plan Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<PaymentPlan>> findByUserId (@PathVariable("id") Long id){
        try {
            Optional<User> user = userService.getById(id);
            if(!user.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<PaymentPlan> paymentPlan = paymentPlanService.findByUserId(id);
                return new ResponseEntity<>(paymentPlan, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/input-information/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Payment Plan by Input Information Id", notes = "Method for find a Payment Plan by Input Information Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Payment Plan found by Input Information Id"),
            @ApiResponse(code = 404, message = "Payment Plan Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<PaymentPlan>> findByInputInformationId (@PathVariable("id") Long id){
        try {
            Optional<InputInformation> inputInformation = inputInformationService.getById(id);
            if(!inputInformation.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                List<PaymentPlan> paymentPlan = paymentPlanService.findByInputInformationId(id);
                return new ResponseEntity<>(paymentPlan, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Payment Plan", notes = "Method to create a Payment Plan")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Payment Plan created"),
            @ApiResponse(code = 404, message = "Payment Plan not created"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<PaymentPlan> insertPaymentPlan(@PathVariable("id") Long userId, @RequestBody PaymentPlan paymentPlan){
        try {
            Optional<User> user = userService.getById(userId);
            if(!user.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else{
                paymentPlan.setUser(user.get());
                PaymentPlan paymentPlanNew = paymentPlanService.save(paymentPlan);
                return ResponseEntity.status(HttpStatus.CREATED).body(paymentPlanNew);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Payment Plan", notes = "Method to update a Payment Plan")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Payment Plan updated"),
            @ApiResponse(code = 404, message = "Payment Plan not updated"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<PaymentPlan> updatePaymentPlan(@PathVariable("id") Long id, @RequestBody PaymentPlan paymentPlan){
        try {
            Optional<PaymentPlan> paymentPlanUp = paymentPlanService.getById(id);
            if(!paymentPlanUp.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else{
                paymentPlan.setId(id);
                paymentPlanService.save(paymentPlan);
                return new ResponseEntity<>(paymentPlan, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Payment Plan", notes = "Method to delete a Payment Plan")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Payment Plan deleted"),
            @ApiResponse(code = 404, message = "Payment Plan not deleted"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<PaymentPlan> deletePaymentPlan(@PathVariable("id") Long id) {
        try {
            Optional<PaymentPlan> paymentPlanDelete = paymentPlanService.getById(id);
            if (!paymentPlanDelete.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                paymentPlanService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
