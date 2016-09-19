package com.wscq.baseWeiChat.domain.service.security;

import com.wscq.baseWeiChat.domain.model.Account;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 * Created by zs on 16/9/19.
 */
public class WscqUserDetails extends User {
    private static final long serialVersionUID = 1L;

    private final Account account;

    public WscqUserDetails(Account account) {
        super(account.getUserName(), account.getPassword(), AuthorityUtils.createAuthorityList("ROLE_" + account.getAccountType()));
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
