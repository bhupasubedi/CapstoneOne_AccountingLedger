package com.ps.enums;

public enum MainMenuOption {
    ADD_DEPOSIT(1, "Add Deposit (Credit)"),
    MAKE_PAYMENT(2, "Make Payment (Debit)"),
    LEDGER(3, "Ledger..."),
    EXIT(0, "Exit");

    private final int code;
    private final String description;

    MainMenuOption(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MainMenuOption fromCode(int code) {
        for (MainMenuOption option : values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
}
