package com.wscq.baseWeiChat.domain.service.security;

import com.wscq.baseWeiChat.domain.model.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zs on 16/9/19.
 */
@Service
public class WscqUserDetailsService implements UserDetailsService {

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = new Account();
        account.setUserId(1l);
        account.setAccountType("SUPPER");
        account.setPassword("$2a$10$oa539vZ96KGTQwJAi18ZyOHAMcSY.H9de3PURWazfPdO0nR9vWj8.");
        account.setUserName("zs");
        return new WscqUserDetails(account);
    }
}
