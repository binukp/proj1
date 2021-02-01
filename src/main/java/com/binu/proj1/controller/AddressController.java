package com.binu.proj1.controller;

import com.binu.proj1.entity.Address;
import com.binu.proj1.entity.Person;
import com.binu.proj1.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AddressController {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @GetMapping("/get-addresses/{person_id}/{person_name}")
    public String getAddressList2( @PathVariable("person_id") int person_id, @PathVariable("person_name") String person_name, Model model) {
        Person p = new Person();
        p.setPerson_id(person_id);
        List<Address> addressList = (List<Address>) addressRepository.findAAllAddressByPersonID(p);
        model.addAttribute("addresses", addressList);
        model.addAttribute("person_id", person_id);
        System.out.println("get-addresses for the person selected: " + person_id +":"+ person_name +":"+ addressList.size());
        return "address_list";
    }

   @GetMapping("/signupaddress/{person_id}")
    public String addAddressForm(Address address,@PathVariable("person_id") int person_id, Model model) {
        return "add-address";
    }

    @PostMapping("/addaddress")
    public String addAddressPost(@RequestParam(name = "person_id") Integer person_id , @Valid Address address, BindingResult result, Model model) {
        System.out.println("adding address for " + person_id + ":" + address.getStreet());
        if (result.hasErrors()) {
            return "add-address";
        }
        Person person = new Person();
        person.setPerson_id(person_id);
        address.setPerson(person);
        addressRepository.save(address);
        List<Address> addressList = (List<Address>) addressRepository.findAAllAddressByPersonID(person);
        model.addAttribute("addresses", addressList);
        model.addAttribute("person_id", person_id);
        System.out.println("addaddress for the person selected: " + person_id +":"+ addressList.size());
        return "address_list";
    }

    @GetMapping("/address_edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Address Id:" + id));
        model.addAttribute("address", address);
        return "update-address";
    }

    @PostMapping("/address_update/{id}")
    public String updateAddress(@PathVariable("id") int id, @Valid Address address, BindingResult result, Model model) {

        if (result.hasErrors()) {
            address.setAddress_id(id);
            return "update-address";
        }
        Address address_o = addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Address Id:" + id));
        System.out.println("updating address for " + id + ":" + address.getStreet()+ address.getPerson()+ address_o.getPerson());
        address.setPerson(address_o.getPerson());
        address.setAddress_id(id);
        addressRepository.save(address);
        return "redirect:/index";
    }

    @GetMapping("/address_delete/{id}")
    public String deletePerson(@PathVariable("id") int id, Model model) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Address Id:" + id));
        addressRepository.delete(address);
        return "redirect:/index";
    }

    }
