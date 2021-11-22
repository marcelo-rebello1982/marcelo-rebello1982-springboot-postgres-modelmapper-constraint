package com.marcelo.algafood.domain.exception;

import org.hibernate.JDBCException;

import java.sql.SQLException;

public class CafeConstraintViolationExecption extends JDBCException {

    private String constraintName;

    public CafeConstraintViolationExecption(String message, SQLException root, String constraintName) {
        super(message, root);
        this.constraintName = constraintName;
    }

    public CafeConstraintViolationExecption(String message, SQLException root, String sql, String constraintName) {
        super(message, root, sql);
        this.constraintName = constraintName;
    }

    public String getConstraintName() {
        return constraintName;
    }
}