package com.vikramDB.connecting_springboot.controllers;

import com.vikramDB.connecting_springboot.userdata.Userinformation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vikramDB.connecting_springboot.repository.Usercontroll;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class Commands {

    private final Usercontroll usercontroll ;

    public Commands(Usercontroll usercontroll) {
        this.usercontroll = usercontroll;
    }
    @GetMapping
    public  ResponseEntity<List<Userinformation>> findall(){
        return ResponseEntity.ok(this.usercontroll.findAll());
    }
    @PostMapping(value = "/add")
    public ResponseEntity <Userinformation> creatproduct(@RequestBody Userinformation userinformation ){
           return ResponseEntity.ok (this.usercontroll.save(userinformation));
    }
    @GetMapping(value = "/{Id}")
    public  ResponseEntity  getusingid(@PathVariable ("Id") String Id){
        Optional<Userinformation>databygetuseid=this.usercontroll.findById(Id);
        if (databygetuseid.isPresent()){
            return ResponseEntity.ok(databygetuseid.get());
        }
        else {
            return ResponseEntity.ok("id is not correct");
        }
    }

    @PutMapping(value = ("/put/{ide}"))
    public ResponseEntity<Userinformation> update(@PathVariable("ide")String ide,@RequestBody Userinformation userinformation) {
        Optional<Userinformation> userinfo = this.usercontroll.findById(ide);
        if (userinfo.isPresent()) {
        Userinformation userinforma2=userinfo.get();
            userinforma2.setUsername(userinformation.getUsername());
            userinforma2.setUseremail(userinformation.getUseremail());
            return ResponseEntity.ok( usercontroll.save(userinforma2));
        }
        else {
            return null;

        }


    }
    @DeleteMapping(value = "/{ides}")
    public ResponseEntity deleteproductbyid(@PathVariable String ides){
     Optional<Userinformation> userinformation=this.usercontroll.findById(ides);
     if (userinformation.isPresent()){
         this.usercontroll.deleteById(ides);
         return ResponseEntity.ok("its successfully deleted");
     }
     else {
         return ResponseEntity.ok("its not deleted");
     }
    }
    @DeleteMapping
    public String deleteall(){
        usercontroll.deleteAll();
        return ("deleted successfully");
    }


}
