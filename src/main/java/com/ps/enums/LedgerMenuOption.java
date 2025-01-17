package com.ps.enums;

public enum LedgerMenuOption {
    LIST_ALL(1, "View all transactions"),
    LIST_CREDITS(2, "View all credits"),
    LIST_DEBITS(3, "View all debits"),
    SEARCH_BY_ID(4, "View a transaction by ID"),
    MONTH_TO_DATE(5, "View month to date"),
    PREVIOUS_MONTH(6, "View previous month"),
    YEAR_TO_DATE(7, "View year to date"),
    PREVIOUS_YEAR(8, "View previous year"),
    CUSTOM_SEARCH(9, "Custom search..."),

    BACK(0, "Back to main menu");

    private final int code;
    private final String description;

    LedgerMenuOption(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static LedgerMenuOption fromCode(int code) {
        for (LedgerMenuOption option : values()) {
            if (option.code == code) {
                return option;
            }
        }
        return null;
    }
}
