package com.ucb.medicalnow.Controller;

import com.ucb.medicalnow.BL.SecurityBl;
import com.ucb.medicalnow.Model.CredentialModel;
import com.ucb.medicalnow.Model.TokenRefreshModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/security")
public class SecurityController {

    private SecurityBl securityBl;

    @Autowired
    public SecurityController (SecurityBl securityBl) { this.securityBl = securityBl; }

    @RequestMapping(
            value="login",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE,
            consumes =MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Map<String,Object>> authenticate (@RequestBody CredentialModel credentialModel){
        Map <String,String> tokens = securityBl.authenticate(credentialModel.getEmail(), credentialModel.getPassword());
        if(tokens != null){
            Map <String,Object> response = new HashMap();
            response.put("Message","Authentication OK");
            response.put("userId", tokens.get("userId"));
            response.put("authentication",tokens.get("authentication"));
            response.put("refresh",tokens.get("refresh"));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            Map <String,Object> response = new HashMap();
            response.put("Message","User or password invalid");
            return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(
            value = "refresh",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Map<String,Object>> authenticate (@RequestBody TokenRefreshModel tokenRefreshModel){
        Map<String,String> tokens = securityBl.refresh(tokenRefreshModel);
        Map<String,String> response= new HashMap<>();
        response.put("Message","Authentication OK");
        response.put("userId", tokens.get("userId"));
        response.put("authentication", tokens.get("authentication"));
        response.put("refresh", tokens.get("refresh"));
        return new ResponseEntity (response, HttpStatus.OK);
    }
}
