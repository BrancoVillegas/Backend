package com.finanzas.backend.controller;


import com.finanzas.backend.entities.InputInformation;
import com.finanzas.backend.service.IInputInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/input-information")
@Api(tags = "Input Information", value = "Web Service RESTFul of Input Information")
public class InputInformationController {


    private final IInputInformationService inputInformationService;

    public InputInformationController(IInputInformationService inputInformationService) {
        this.inputInformationService = inputInformationService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Input Information", notes = "Method to list all Input Information")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Input Information founds"),
            @ApiResponse(code = 404, message = "Input Information Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<InputInformation>> findAllInputInformation(){
        try {
            List<InputInformation> inputInformations = inputInformationService.getAll();
            if(inputInformations.size()>0)
                return new ResponseEntity<>(inputInformations, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Input Information by Id", notes = "Method for find a Input Information by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Input Information found by Id"),
            @ApiResponse(code = 404, message = "Input Information Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<InputInformation> findById (@PathVariable("id") Long id){
        try {
            Optional<InputInformation> inputInformation = inputInformationService.getById(id);
            if(inputInformation.isPresent())
                return new ResponseEntity<>(inputInformation.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/customer-first-name/{customerFirstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Input Information by Customer First Name", notes = "Method for find a Input Information by Customer First Name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Input Information found by Customer First Name"),
            @ApiResponse(code = 404, message = "Input Information Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<InputInformation>> findByCustomerFirstName (@PathVariable("customerFirstName") String customerFirstName){
        try {
            List<InputInformation> inputInformation = inputInformationService.findByCustomerFirstName(customerFirstName);
            if(inputInformation.size()>0)
                return new ResponseEntity<>(inputInformation, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/customer-last-name/{customerLastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Input Information by Customer Last Name", notes = "Method for find a Input Information by Customer Last Name")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Input Information found by Customer Last Name"),
            @ApiResponse(code = 404, message = "Input Information Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<InputInformation>> findByCustomerLastName (@PathVariable("customerLastName") String customerLastName){
        try {
            List<InputInformation> inputInformation = inputInformationService.findByCustomerLastName(customerLastName);
            if(inputInformation.size()>0)
                return new ResponseEntity<>(inputInformation, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Input Information", notes = "Method to create Input Information")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Input Information created"),
            @ApiResponse(code = 404, message = "Input Information not created"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<InputInformation> insertInputInformation(@Valid @RequestBody InputInformation inputInformation){
        try {
            InputInformation inputInformationNew = inputInformationService.save(inputInformation);
            return ResponseEntity.status(HttpStatus.CREATED).body(inputInformationNew);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Input Information data", notes = "Method to update Input Information")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Input Information updated"),
            @ApiResponse(code = 404, message = "Input Information not updated"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<InputInformation> updateInputInformation(
            @PathVariable("id") Long id, @Valid @RequestBody InputInformation inputInformation){
        try {
            Optional<InputInformation> inputInformationUp = inputInformationService.getById(id);
            if(!inputInformationUp.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else{
                inputInformation.setId(id);
                inputInformationService.save(inputInformation);
                return new ResponseEntity<>(inputInformation, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Input Information", notes = "Method to delete Input Information")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Input Information deleted"),
            @ApiResponse(code = 404, message = "Input Information not deleted"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<InputInformation> deleteInputInformation(@PathVariable("id") Long id){
        try {
            Optional<InputInformation> inputInformationDelete = inputInformationService.getById(id);
            if (!inputInformationDelete.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                inputInformationService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
