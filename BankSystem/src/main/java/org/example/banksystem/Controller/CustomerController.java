package org.example.banksystem.Controller;


import org.example.banksystem.ApiResponse.ApiResponse;
import org.example.banksystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping ("/api/v1/customer")
public class CustomerController {

    ArrayList<Customer> customers=new ArrayList<>();


    @GetMapping("/getAllCustomers")
    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }


    @PostMapping("/addCustomer")
    public ApiResponse addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return new ApiResponse("Customer added successfully");

    }

    @PutMapping("/updateCustomer/{id}")
    public ApiResponse updateCustomer(@PathVariable String id, @RequestBody Customer customer){
        for (int i = 0; i <customers.size() ; i++) {
            if(customers.get(i).getId().equals(id)){
                customers.set(i,customer);
                return new ApiResponse("Customer updated successfully");
            }
        }
        return new ApiResponse("Customer not found");
    }

    @DeleteMapping("/deleteCustomer/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index){
        customers.remove(index);
        return new ApiResponse("Customer deleted successfully");
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public ApiResponse withdraw(@PathVariable String id,@PathVariable int amount){
        if(amount<0)
            return new ApiResponse("amount is negative");
        for(Customer customer : customers){
            if(customer.getId().equals(id)){
                if(amount <= customer.getBalance()){
                    customer.setBalance(customer.getBalance()- amount);
                    return new ApiResponse("Customer withdrawn successfully");
                }
                else
                    return new ApiResponse("Customer withdraw failed,you dont have enough money");
            }
        }
        return new ApiResponse("Customer not found");
    }


    @PutMapping("/deposit/{id}/{amount}")
    public ApiResponse deposit(@PathVariable String id,@PathVariable int amount){
        if(amount<0)
            return new ApiResponse("amount is negative");
        for(Customer customer : customers){
            if(customer.getId().equals(id)){
                customer.setBalance(customer.getBalance()+amount);
                return new ApiResponse("Customer deposited successfully");

            }
        }
        return new ApiResponse("Customer not found");
    }

}
