package com.example.vinh.genealogyapp;

import com.example.vinh.genealogyapp.Model.Member;
import com.example.vinh.genealogyapp.Model.User;

import java.util.List;

/**
 * Created by VINH on 4/3/2016.
 */
public interface IGenealogyAPI {
    void OnLoginListener(User user);

    void OnGetAllUsersListener(List<User> users);

    void OnGetUserDetailListener(User user);

    void OnGetAllMembersListener(List<Member> members);

    void OnGetMemberDetailListener(Member member);
}

