package org.sid.user;

import com.sun.jersey.spi.inject.Inject;
import org.apache.commons.collections.ArrayStack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.sid.entities.Beneficiaire;
import org.sid.entities.User;
import org.sid.repositories.UtilisateurRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MyUserDetailServiceTest {

    @Mock
    UtilisateurRepository userRepo;

    @InjectMocks
    MyUserDetailService myUserDetailService;
    @Test
    public void testUserDetails(){

    List<User> users = new ArrayList();
        Optional<User>  user = Optional.of(new Beneficiaire(1));
        user.get().setPhone_number("0000000");
    Mockito.when(userRepo.findById(1)).thenReturn(user);
        Optional<User>  user1=    myUserDetailService.usergetting();
        System.out.println(    user1.get().getPhone_number());
    }
}