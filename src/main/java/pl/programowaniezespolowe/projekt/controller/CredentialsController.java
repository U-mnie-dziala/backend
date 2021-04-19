package pl.programowaniezespolowe.projekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.payload.request.ChangeEmailRequest;
import pl.programowaniezespolowe.projekt.payload.request.ChangePasswordRequest;
import pl.programowaniezespolowe.projekt.payload.request.RemoveAccountRequest;
import pl.programowaniezespolowe.projekt.service.CredentialsService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/credentials")
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;


    @PostMapping("/changepassword")
    public String changePassword(@RequestBody @Valid ChangePasswordRequest passwordRequest){
        if(this.credentialsService.changePassword(passwordRequest.getUserId(), passwordRequest.getOldPassword(), passwordRequest.getNewPassword())){
            return "Hasło zmienione pomyślnie.";
        }
        return "Nie zmieniono hasła.";
    }

    @PostMapping("/changeemail")
    public String changeEmail(@RequestBody @Valid ChangeEmailRequest emailRequest){
        if(this.credentialsService.changeEmail(emailRequest.getUserId(), emailRequest.getOldPassword(), emailRequest.getEmailNew())){
            return "Email zmieniony pomyślnie.";
        }
        return "Nie zmieniono maila.";
    }

    @PostMapping("/deleteaccount")
    public String deleteaccount(@RequestBody RemoveAccountRequest removeAccountRequest){
        if(this.credentialsService.deleteAccount(removeAccountRequest.getUserId(), removeAccountRequest.getOldPassword())){
            return "Konto zostało usunięte.";
        }
        return "Nie usunięto konta.";
    }

}
