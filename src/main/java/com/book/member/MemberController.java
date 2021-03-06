package com.book.member;


import com.book.member.dto.MemberResponse;
import com.book.member.dto.MemberSignupRequest;
import com.book.member.service.MemberHelperService;
import com.book.member.service.MemberSignUpService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberSignUpService memberSignUpService;
    private final MemberHelperService memberHelperService;

    @RequestMapping(value = "/members", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity signUpMember(@RequestBody @Valid final MemberSignupRequest request, final BindingResult errors){
        if(errors.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        final MemberResponse member = new MemberResponse(memberSignUpService.signUp(request));
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/members/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity getUser(final long id){
        return new ResponseEntity<>(memberHelperService.findById(id),HttpStatus.OK);
    }


}
