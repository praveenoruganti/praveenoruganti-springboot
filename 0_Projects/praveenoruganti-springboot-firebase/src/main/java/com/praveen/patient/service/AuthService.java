package com.praveen.patient.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.praveen.patient.model.UserRecordDTO;

public interface AuthService {

    UserRecordDTO createUser(UserRecordDTO userRecordDTO) throws FirebaseAuthException;
}
