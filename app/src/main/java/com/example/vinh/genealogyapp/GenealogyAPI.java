package com.example.vinh.genealogyapp;

import com.example.vinh.genealogyapp.Model.Member;
import com.example.vinh.genealogyapp.Model.User;

import java.util.List;

public abstract class GenealogyAPI implements IGenealogyAPI{

    @Override
    public void OnLoginListener(User user) {

    }

    @Override
    public void OnGetAllUsersListener(List<User> users) {

    }

    @Override
    public void OnGetUserDetailListener(User user) {

    }

    @Override
    public void OnGetAllMembersListener(List<Member> members) {

    }

    @Override
    public void OnGetMemberDetailListener(Member member) {

    }
}
